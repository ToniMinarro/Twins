package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class TestPane extends JPanel {

    public TestPane(int row, int col) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                gbc.gridx = c;
                gbc.gridy = r;

                int cellSize = ((800/col) + (600/row)) / 2;
                Tablero cellPane = new Tablero(cellSize);
                Border border = null;
                if (r < row-1) {
                    if (c < col-1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (c < col-1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }
}