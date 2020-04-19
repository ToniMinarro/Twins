package twinsgamecard.Classes;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class Card {
    
    private int value;
    
    public Card() {}
    
    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void setRandomValue() {
        this.value = ((int)(10.0 * Math.random()));
    }
}