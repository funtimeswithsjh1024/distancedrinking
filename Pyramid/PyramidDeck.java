import javax.swing.*;
import java.util.ArrayList;

/**
 * It is literally just a Deck but with PyramidCards.
 */


public class PyramidDeck extends Deck{
    /**
     * Constructor populates the CardDeck with 52 cards,
     * one of each rank, in each suit, as standard.
     * It does NOT shuffle the deck after creation.
     */

    public PyramidDeck()
    {
        for(Card.Suit s : SUITS) {
            for (Card.Rank r : RANKS) {
                PyramidCard c = new PyramidCard(r, s);
                this.CardDeck.add(c);
            }
        }

    }


    public static void main( String[] args )
    {

        //------ graphical test -------------------
        JFrame f = new JFrame();

        PyramidDeck d = new PyramidDeck();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLayout( null );
        int x = 0;
        int y = 50;
        for( Card c : d.getDeck()){
            c.setLocation(x, y);
            f.add(c);
            x+=71;
        }

        f.setSize( 1000, 200 );
        f.setVisible( true );
    }
}
