package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon; // Added import
import javax.swing.JLabel;    // Added import
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Celda extends JPanel
{
    private Color defaultBackground;
    private final int cardId; // Renamed 'value' to 'cardId' for clarity, it's the ID for matching
    private final int cellSize;
    private boolean isFlipped = false;
    private JLabel imageLabel; // JLabel to hold the image

    private static ArrayList<Celda> flippedCells = new ArrayList<>();
    private static int matchedPairs = 0;
    public static int totalPairs; // This will be set by Tablero.java

    public Celda(int cellSize, int cardId)
    {
        this.cellSize = cellSize;
        this.cardId = cardId; // Store the card's unique ID (which maps to an image)

        // Setup the JLabel for the image
        this.imageLabel = new JLabel();
        ImageIcon icon = ImageLoader.getImage(this.cardId);
        if (icon != null) {
            // Potentially scale icon here if too large for cellSize
            // Example: Image img = icon.getImage().getScaledInstance(cellSize - 10, cellSize - 10, Image.SCALE_SMOOTH);
            // this.imageLabel.setIcon(new ImageIcon(img));
            this.imageLabel.setIcon(icon);
        } else {
            // Fallback if image fails to load: show card ID as text
            this.imageLabel.setText(String.valueOf(this.cardId));
        }
        this.imageLabel.setVisible(false); // Initially hide the image/text
        this.add(imageLabel); // Add label to the cell panel

        // Store initial background color. UIManager provides the default panel background.
        this.defaultBackground = UIManager.getColor("Panel.background");
        setBackground(this.defaultBackground); // Explicitly set it

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                // Only highlight if not flipped and less than 2 cells are currently flipped
                if (!isFlipped && flippedCells.size() < 2) {
                    // No need to store defaultBackground again here, it's set on initialization or after a flip logic
                    setBackground(Color.BLUE); // Hover color
                }
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                // Only revert if not flipped and less than 2 cells are currently flipped
                if (!isFlipped && flippedCells.size() < 2) {
                    setBackground(defaultBackground); // Revert to the stored default background
                }
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                // Ignore click if cell is already flipped or two cells are already selected
                if (isFlipped || flippedCells.size() >= 2) {
                    return;
                }

                isFlipped = true;
                imageLabel.setVisible(true); // Show the image
                SoundPlayer.playFlipSound(); // Play flip sound
                setBackground(Color.LIGHT_GRAY); // Indicate cell is selected/flipped
                // defaultBackground should be the one before this click (UIManager's default or DARK_GRAY if it was part of a match)
                // We don't update defaultBackground here to LIGHT_GRAY immediately,
                // because if it's not a match, it should revert to the actual default, not LIGHT_GRAY.

                flippedCells.add(Celda.this);

                if (flippedCells.size() == 2) {
                    Celda cell1 = flippedCells.get(0);
                    Celda cell2 = flippedCells.get(1);

                    if (cell1.cardId == cell2.cardId) {
                        matchedPairs++;
                        // Make cells non-interactive after a match by removing their mouse listeners
                        // Check if listeners exist before trying to remove
                        if (cell1.getMouseListeners().length > 0) {
                            cell1.removeMouseListener(cell1.getMouseListeners()[0]);
                        }
                        if (cell2.getMouseListeners().length > 0) {
                            cell2.removeMouseListener(cell2.getMouseListeners()[0]);
                        }

                        // Change background to indicate a permanent match
                        Color matchedColor = Color.DARK_GRAY;
                        cell1.setBackground(matchedColor);
                        cell2.setBackground(matchedColor);
                        // Update their defaultBackground so if mouse exits, it remains DARK_GRAY
                        cell1.defaultBackground = matchedColor;
                        cell2.defaultBackground = matchedColor;

                        Tablero.addScore(10); // Add 10 points for a match
                        SoundPlayer.playMatchSound(); // Play match sound
                        flippedCells.clear(); // Ready for the next pair
                        if (matchedPairs == totalPairs) {
                            SoundPlayer.playGameWinSound(); // Play win sound
                            // Reset matchedPairs for a potential new game, though the current structure doesn't support it directly
                            matchedPairs = 0;
                            // Tablero.resetScore(); // Reset score for a new game if TwinsGame is re-initialized. Current setup does this.
                            JOptionPane.showMessageDialog(getRootPane(), "You Win! Final Score: " + Tablero.getScoreLabel().getText().split(":")[1].trim());
                        }
                    } else {
                        Tablero.addScore(-2); // Subtract 2 points for a mismatch
                        SoundPlayer.playMismatchSound(); // Play mismatch sound
                        // Not a match, flip them back after a delay
                        Timer timer = new Timer(1000, event -> {
                            Color panelDefault = UIManager.getColor("Panel.background");
                            // Reset cell1
                            cell1.isFlipped = false;
                            cell1.imageLabel.setVisible(false);
                            cell1.setBackground(panelDefault);
                            cell1.defaultBackground = panelDefault; // Update default for future hovers
                            // Reset cell2
                            cell2.isFlipped = false;
                            cell2.imageLabel.setVisible(false);
                            cell2.setBackground(panelDefault);
                            cell2.defaultBackground = panelDefault; // Update default for future hovers

                            flippedCells.clear(); // Ready for the next attempt
                        });
                        timer.setRepeats(false); // Ensure the timer only runs once
                        timer.start();
                    }
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(this.cellSize, this.cellSize);
    }
}
