
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own
    public int face; // 0-ace to 12-King 
    public int suit; //Clubs, Diamonds, Hearts, Spades
    public int state; //Which hand is it a part of?

    /**
     * Constructor for objects of class Card
     */
    public Card(int face, int suit)
    {
        this.face = face;
        this.suit = suit;
    }
}
