package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Tablero extends JPanel
{
    private Color defaultBackground;
    private int cellSize;

    public Tablero(int cellSize)
    {
        this.cellSize = cellSize;
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                defaultBackground = getBackground();
                setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                setBackground(defaultBackground);
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                String a = "LOL";
                setBackground(defaultBackground);
            }
        });
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(this.cellSize, this.cellSize);
    }
}