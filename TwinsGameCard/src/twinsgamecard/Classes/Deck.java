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

    public void addCard(Card card) {
        if (this.deck == null) {
            this.deck = new ArrayList<>();
        }
        this.deck.add(card);
    }
    public void removeCard(int index) {
        if (this.deck != null && index >= 0 && index < this.deck.size()) {
            this.deck.remove(index);
        }
    }
    public void removeCard(Card card) {
        if (this.deck != null) {
            this.deck.remove(card);
        }
    }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public ArrayList<Card> getDeck() { return deck; }
    public void setDeck(ArrayList<Card> deck) { this.deck = deck; }


    public void createTwinsDeck() {
        // Default size to 40, difficulty parameter is no longer used directly for twinSize calculation
        this.createTwinsDeck(40, 0); // Difficulty can be used for other things if needed in future
    }

    public void createTwinsDeck(int size, int difficulty) { // difficulty is kept for signature compatibility, not used for twinSize
        // Ensure size is an even number for a pairs game
        if (size % 2 != 0) {
            // Adjust size to the nearest larger even number if it's odd.
            // This ensures the deck can be formed into pairs.
            size++;
            // Optionally, log this adjustment:
            // System.err.println("Deck size must be an even number for a twins game. Adjusted size to " + size);
        }
        this.size = size;

        // For a "Twins" game (matching pairs), twinSize should always be 2.
        // The original difficulty logic was overly complex for a standard memory/pairs game
        // and could result in groups larger than pairs, or an incorrect number of cards.
        int twinSize = 2; // Each "twin" set consists of two identical cards.

        // Calculate the number of unique card values needed.
        // For example, a deck of 40 cards will have 20 unique card values (pairs).
        int totalUniqueCards = this.size / twinSize;

        this.deck = new ArrayList<>(); // Initialize the deck

        // Create the pairs of cards
        for(int i = 1; i <= totalUniqueCards; i++) {
            Card card = new Card(i); // Card value 'i' serves as its unique identifier.
            // Add two identical cards to the deck to form a pair.
            this.deck.add(card);
            this.deck.add(card);
        }

        Collections.shuffle(this.deck); // Randomize the order of cards in the deck.
    }
}
