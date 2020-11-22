import javax.swing.*;
import java.util.ArrayList;

/**
 * A Row of Cards, to be used in the Pyramid game.
 * Maybe it will be applicable to other games, too.
 */


public class Row
{

    /**
     * Constructor for a row of cards.
     *
     * @param deck : Deck to be used to put Cards in the row.
     * @param size : size of the Row.
     */
    private ArrayList<Card> RowCards;

    public Row(Deck d, int size){
        d.shuffleDeck();
        RowCards = new ArrayList<>();
        //draw the indicated number of cards from the deck,
        //then add them to the row.
        for(int i = 0; i < size; i++){
            Card addCard = d.drawCard();
            RowCards.add(addCard);

        }
    }
    /**
     * Getter for cards in the row.
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getRow(){
        return RowCards;
    }
    /**
     * Prints out current contents of row as a string.
     * @return String: a list of the cards in the deck.
     */
    public String toString(){
        String cards = "";
        for(Card c : RowCards){
            cards += c.toString();
            cards += ", ";
        }
        return cards;
    }

    /**
     * Method used to paint the cards in the window.
     * It will calculate the X positions of all cards
     * based on an initial X, and it will
     * add the positioned to the JFrame supplied.
     *
     * Cards will all be put consistently at the Y value supplied.
     *
     * @param frame: JFrame to add cards to.
     * @param xPos: Initial X position of the row (where row starts)
     * @param yPos: Y position of the row.
     */
    public void paintRow(JFrame frame, int xPos, int yPos){
        for(Card c: RowCards){
            c.setLocation(xPos, yPos);
            xPos += 100;
            frame.add(c);
        }
    }


    /**
     * Main method tests the Row class.
     * @param args arguments
     */
    public static void main(String[] args){
        PyramidDeck d = new PyramidDeck();
        Row one = new Row(d, 1);
        System.out.println(one.toString());
        Row two = new Row(d, 2);
        System.out.println(two.toString());
        Row three = new Row(d, 3);
        System.out.println(three.toString());
        Row four = new Row(d, 4);
        System.out.println(four.toString());
        Row five = new Row(d, 5);
        System.out.println(five.toString());
        //------ graphical test -------------------
        JFrame f = new JFrame();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLayout( null );

        three.paintRow(f, 50, 50);

        f.setSize( 600, 200 );
        f.setVisible( true );
    }
}

