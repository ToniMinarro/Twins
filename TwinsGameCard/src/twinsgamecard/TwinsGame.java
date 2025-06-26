package twinsgamecard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import twinsgamecard.Classes.Deck;
import twinsgamecard.Classes.Funciones;
import twinsgamecard.Classes.Tablero;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class TwinsGame extends javax.swing.JFrame {
    
    public TwinsGame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TwinCardGame");
        setMaximumSize(null);
        setMinimumSize(null);
        setName("TwinCardGame"); // NOI18N
        setPreferredSize(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        PrepararInterfaz();
    }//GEN-LAST:event_formWindowOpened

    private void PrepararInterfaz() {
        Deck twinsDeck = new Deck();
        twinsDeck.createTwinsDeck();
        
        ArrayList<Integer> divisores = Funciones.dameDivisoresIntermedios(twinsDeck.getSize());

        this.setLayout(new BorderLayout());

        Tablero tablero = new Tablero(divisores, twinsDeck);
        JLabel scoreLabel = Tablero.getScoreLabel(); // Get the score label from Tablero

        this.add(scoreLabel, BorderLayout.NORTH); // Add score label at the top
        this.add(tablero, BorderLayout.CENTER); // Add game board in the center

        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> { new TwinsGame().setVisible(true); });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}