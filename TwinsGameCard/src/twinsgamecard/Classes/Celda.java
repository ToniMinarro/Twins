package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Celda extends JPanel
{
    private Color defaultBackground;
    private final int value;
    private final int cellSize;
    private boolean isFlipped = false;

    private static ArrayList<Celda> flippedCells = new ArrayList<>();
    private static int matchedPairs = 0;
    public static int totalPairs; // This will be set by Tablero.java

    public Celda(int cellSize, int value)
    {
        this.cellSize = cellSize;
        this.value = value;
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
                getComponent(0).setVisible(true); // Show the number/image in the cell
                setBackground(Color.LIGHT_GRAY); // Indicate cell is selected/flipped
                // defaultBackground should be the one before this click (UIManager's default or DARK_GRAY if it was part of a match)
                // We don't update defaultBackground here to LIGHT_GRAY immediately,
                // because if it's not a match, it should revert to the actual default, not LIGHT_GRAY.

                flippedCells.add(Celda.this);

                if (flippedCells.size() == 2) {
                    Celda cell1 = flippedCells.get(0);
                    Celda cell2 = flippedCells.get(1);

                    if (cell1.value == cell2.value) {
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

                        flippedCells.clear(); // Ready for the next pair
                        if (matchedPairs == totalPairs) {
                            // Reset matchedPairs for a potential new game, though the current structure doesn't support it directly
                            matchedPairs = 0;
                            JOptionPane.showMessageDialog(getRootPane(), "You Win!");
                        }
                    } else {
                        // Not a match, flip them back after a delay
                        Timer timer = new Timer(1000, event -> {
                            Color panelDefault = UIManager.getColor("Panel.background");
                            // Reset cell1
                            cell1.isFlipped = false;
                            cell1.getComponent(0).setVisible(false);
                            cell1.setBackground(panelDefault);
                            cell1.defaultBackground = panelDefault; // Update default for future hovers
                            // Reset cell2
                            cell2.isFlipped = false;
                            cell2.getComponent(0).setVisible(false);
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
