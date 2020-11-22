import java.util.ArrayList;
import java.util.Collections;

/**
 * A Deck of cards, which can be used to play a game.
 * Decks are standard 52 card decks, and they can be shuffled.
 * @author sjh1024 2020
 */
public abstract class Deck
{
    protected final Card.Rank[] RANKS = { Card.Rank.TWO,
            Card.Rank.THREE, Card.Rank.FOUR, Card.Rank.FIVE,
            Card.Rank.SIX, Card.Rank.SEVEN, Card.Rank.EIGHT,
            Card.Rank.NINE, Card.Rank.TEN, Card.Rank.JACK,
            Card.Rank.QUEEN, Card.Rank.KING, Card.Rank.ACE};
    protected final Card.Suit[] SUITS = {Card.Suit.HEARTS, Card.Suit.DIAMONDS,
            Card.Suit.SPADES, Card.Suit.CLUBS,};
    protected ArrayList<Card> CardDeck = new ArrayList<>();

    /**
     * Constructor populates the CardDeck with 52 cards,
     * one of each rank, in each suit, as standard.
     * It does NOT shuffle the deck after creation.
     */
    public Deck()
    {

    }
    public ArrayList<Card> getDeck(){return CardDeck;};

    /**
     * Draw a card from the Deck.
     * @return the Card that you drew
     */
    public Card drawCard(){
        //get card from the beginning of the list/top of the deck
        Card drawn = CardDeck.get(0);
        //remove the card from the deck
        CardDeck.remove(0);
        return drawn;
    }
    /**
     * Getter for size of deck.
     * @return int
     */
    public int getSize(){
        return CardDeck.size();
    }
    /**
     * Shuffles the deck.
     * You can do this operation in a full or less than full deck.
     */
    public void shuffleDeck(){
        Collections.shuffle(CardDeck);
    }

    /**
     * Prints out current contents of deck from top to bottom.
     * Should only be used for debugging purposes.
     * @return String: a list of the cards in the deck.
     */
    public String toString(){
        String cards = "";
        for(Card c : CardDeck){
            cards += c.toString();
            cards += ", ";
        }
        return cards;
    }

}