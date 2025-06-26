package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.Font; // For score label font

public class Tablero extends JPanel
{
    private static int currentScore = 0;
    private static JLabel scoreLabel; // Static to be accessed by Celda indirectly or via methods

    public Tablero(ArrayList<Integer> divisores, Deck twinsDeck)
    {
        // Initialize score and label for a new game
        currentScore = 0;
        if (scoreLabel == null) { // Create it only once, or update if it exists
            scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        } else {
            updateScoreDisplay(); // Ensure it's reset if Tablero is recreated
        }
        // The scoreLabel will be added to the main frame (TwinsGame) later.
        // This constructor just ensures it's ready.

        int row = divisores.get(0);
        int col = divisores.get(1);

        // Initialize totalPairs in Celda before creating Celda instances
        // Also, reset matchedPairs to 0 for a new game.
        Celda.totalPairs = twinsDeck.getSize() / 2;
        // Celda.matchedPairs = 0; // This should be done in Celda, if a reset mechanism is implemented there.
                               // For now, a new game instance will have matchedPairs as 0 due to static initialization.

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        int cellSize = ((800/col) + (600/row)) / 2; // Basic cell size calculation

        // Ensure the deck is not empty and has enough cards
        if (twinsDeck.getDeck() == null || twinsDeck.getDeck().size() < row * col) {
            // Handle error: not enough cards or deck not initialized
            // This could be showing a message to the user or throwing an exception
            System.err.println("Error: Deck is not properly initialized or doesn't have enough cards.");
            return; // Stop board creation if deck is invalid
        }

        for (int r = 0; r < row; r++)
        {
            for (int c = 0; c < col; c++)
            {
                gbc.gridx = c;
                gbc.gridy = r;

                // Ensure there are cards left in the deck
                if (twinsDeck.getDeck().isEmpty()) {
                    System.err.println("Error: Ran out of cards while creating the board.");
                    return; // Stop if no more cards
                }

                int valor = twinsDeck.getDeck().get(0).getValue();
                twinsDeck.getDeck().remove(0); // Remove card after use

                Celda cellPane = new Celda(cellSize, valor); // valor is the card's ID

                // The JLabel that will hold the image is now created and managed within Celda constructor
                // based on 'valor'. We don't create the JLabel here anymore.

                // Define border for the cell
                Border border = (r < row-1) ?
                                new MatteBorder(1, 1, 0, (c < col-1 ? 0 : 1), Color.GRAY) :
                                new MatteBorder(1, 1, 1, (c < col-1 ? 0 : 1), Color.GRAY);
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }

    public static void addScore(int points) {
        currentScore += points;
        if (currentScore < 0) {
            currentScore = 0; // Score should not go below 0
        }
        updateScoreDisplay();
    }

    public static void resetScore() {
        currentScore = 0;
        updateScoreDisplay();
    }

    private static void updateScoreDisplay() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + currentScore);
        }
    }

    // Method to get the score label to add it to the main frame
    public static JLabel getScoreLabel() {
        if (scoreLabel == null) { // Ensure it's initialized if accessed before constructor
            scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        }
        return scoreLabel;
    }
}
