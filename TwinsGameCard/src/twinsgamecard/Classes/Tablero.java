package twinsgamecard.Classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Tablero extends JPanel
{
    public Tablero(ArrayList<Integer> divisores, Deck twinsDeck)
    {
        int row = divisores.get(0);
        int col = divisores.get(1);
        
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        int cellSize = ((800/col) + (600/row)) / 2;
        for (int r = 0; r < row; r++)
        {
            for (int c = 0; c < col; c++)
            {
                gbc.gridx = c;
                gbc.gridy = r;
                
                int valor = twinsDeck.getDeck().get(0).getValue();
                twinsDeck.getDeck().remove(0);
                
                Celda cellPane = new Celda(cellSize, valor);
                
                JLabel label = new JLabel(String.valueOf(valor));
                label.setVisible(false);
                cellPane.add(label);
                
                Border border = (r < row-1) ? new MatteBorder(1, 1, 0, (c < col-1 ? 0 : 1), Color.GRAY) : new MatteBorder(1, 1, 1, (c < col-1 ? 0 : 1), Color.GRAY);
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }
}