package twinsgamecard.Classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Celda extends JPanel
{
    private Color defaultBackground;
    private final int value;
    private final int cellSize;

    public Celda(int cellSize, int value)
    {
        this.cellSize = cellSize;
        this.value = value;
        
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
                Component a = getComponent(0);
                a.setVisible(true);
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