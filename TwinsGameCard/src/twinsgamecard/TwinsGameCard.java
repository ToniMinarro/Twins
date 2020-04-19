package twinsgamecard;

import twinsgamecard.Classes.Deck;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class TwinsGameCard {

    public static void main(String[] args) {
        
        Deck twinsDeck = new Deck();
        twinsDeck.createTwinsDeck(40, 2);
    }
}