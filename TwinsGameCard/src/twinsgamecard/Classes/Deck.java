package twinsgamecard.Classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class Deck {
    
    private int size;
    private int twinSize;
    private ArrayList<Card> deck;
    
    public Deck() {}
    
    public void addCard(Card card) {
        this.deck.add(card);
    }
    
    public void removeCard(int index) {
        this.deck.remove(index);
    }
    
    public void removeCard(Card card) {
        this.deck.remove(card);
    }
    
    public void createTwinsDeck(int size, int twinSize) {
        this.size = size;
        this.twinSize = twinSize;
        
        if(size >= 10 && twinSize >= 2 && size%twinSize == 0)
        {
            this.deck = new ArrayList<>();

            for(int i=1; i<=this.size/this.twinSize;i++)
            {
                Card card = new Card(i);

                for(int j=0; j<twinSize; j++)
                {
                    deck.add(card);
                }
            }

            Collections.shuffle(this.deck);
        }
        else
        {
            System.out.println("No se puede crear una baraja de parejas con " + size + " cartas y parejas de " + twinSize);
        }
    }
}