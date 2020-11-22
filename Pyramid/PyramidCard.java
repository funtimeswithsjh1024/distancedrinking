import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Cards for the Pyramid Game.
 * They have slightly different behavior when clicked, compared to other games.
 */
public class PyramidCard extends Card{

    public PyramidCard(Rank r, Suit s){
        rank = r;
        suit = s;
        this.setSize( width, height );

        addMouseListener( this );
        if ( backImage == null ) // only read the backImage once
            readBackImage( imageSource );

        //---- Ace high
        String cardFileName = getAceHiFileName( s, r );
        //---- Ace low
        //String cardFileName = getAceLoFileName( s, r );
        faceImage = readImageFromJar( imageSource, cardFileName );

    }

    /**
     * When a Pyramid card is clicked, the following sequence of events should occur:
     * >The card is flipped face-up.
     * >If the card's value is a face value (J, Q, K),
     *      >Prompt the player to drink depending on what row you are on
     *      >Replace any cards on the Pyramid that are face up with new cards from the Deck
     *          >If you run out of cards, game over: player needs to finish their drink
     *      >Start back at the bottom of the pyramid
     * > If not,
     *      >If the player is at the top of the pyramid, they "win".
     *      >The game starts back again from the bottom, all cards replaced, and the next player goes.
     *          >If you run out of cards then the next player needs to finish their drink!
     *      > Else, move up to the next level of the pyramid.
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked( MouseEvent e )
    {
        if(this.getFaceUp()){
            /**
             * TODO: Make a JSON file of snarky dialogue for different situations
             */
            System.out.println("Wrong row, shitbreath, try again");
        }
        else{
            this.setFaceUp(true);
        }
        repaint();

    }

    public static void main( String[] args )
    {
        //////////////////////////////////////////////////////
        // Edit test constructors to use Rank enum constants
        //////////////////////////////////////////////////////
        Rank rank;
        Suit suit;
        // Create Heart Card with rank, 8:
        //     8 is a 10 if Ace is High
        //     8 is a 9 if Ace is Low
        //rank = 8;
        rank = Rank.TEN;
        suit = Suit.HEARTS;

        PyramidCard c1 = new PyramidCard( rank, suit );
        System.out.println( rank + ", " + suit +  " --- " + c1 );


        // Create a Space Card with rank 12,
        //     11 is either a Queen or King
        //rank = 11;
        rank = Rank.KING;
        suit = Suit.SPADES;

        PyramidCard c2 = new PyramidCard( rank, suit );
        System.out.println( rank + ", " + suit +  " --- " + c2 );

        /****/

        //------ graphical test -------------------
        JFrame f = new JFrame();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLayout( null );

        c1.setLocation( 50, 50 );
        f.add( c1 );
        c2.setLocation( 200, 50 );
        f.add( c2 );

        f.setSize( 600, 200 );
        f.setVisible( true );
    }
}
