package twinsgamecard.Classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class Deck {
    
    private int size;
    private ArrayList<Card> deck;
    
    public Deck() {}
    
    public void addCard(Card card) { this.deck.add(card); }
    public void removeCard(int index) {this.deck.remove(index); }
    public void removeCard(Card card) { this.deck.remove(card); }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public ArrayList<Card> getDeck() { return deck; }
    public void setDeck(ArrayList<Card> deck) { this.deck = deck; }
    
    
    public void createTwinsDeck() {
        this.createTwinsDeck(40, 5);
    }
    
    public void createTwinsDeck(int size, int difficulty) {
        this.size = size;
        
        /*
        Conversor de dificultad entrante entre 1 y 5 a % del total de la baraja saliente
        X=1, Y=0,2000%
        X=2, Y=0,1625%
        X=3, Y=0,1250%
        X=4, Y=0,0875%
        X=5, Y=0,0500%
        
        y = mx - n
        y = -0,0375 * difficulty -0,2375
        */
        
        double percent;
        int twinSize, totalParejas;
        boolean dificultadOK, repartoOK;
        
        do {
            percent = Math.round(-(0.0375 * difficulty - 0.2375)*100000)/100000.0d;
            twinSize = (int)Math.round(this.size * percent);
            totalParejas = this.size/twinSize;
            
            dificultadOK = !(difficulty > 0 && difficulty < 6);
            repartoOK = !(totalParejas*twinSize==this.size);
            
            difficulty = (difficulty < 1 && difficulty <= 4) ? ++difficulty : --difficulty;
        }
        while(dificultadOK || repartoOK);
        
        this.deck = new ArrayList<>();
        for(int i=1; i<=this.size/twinSize; i++)
        {
            Card card = new Card(i);
            for(int j=0; j<twinSize; j++) { deck.add(card); }
        }

        Collections.shuffle(this.deck);
    }
}