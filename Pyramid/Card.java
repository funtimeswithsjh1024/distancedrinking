//---------------------------- Card.java ------------------------
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.jar.*;
import java.util.zip.*;

/**
 * Card -- models playing cards
 */

public class Card extends JLabel
        implements Comparable<Card>, MouseListener
{
    //-------------------------- class variables --------------------
    //--- package access 
    static int width = 71, height = 96;   // card size; package access
    static String  imageSource = "cards_gif.jar";

    //--- private access
    protected static BufferedImage backImage = null;
    protected static JarFile  jarFile = null;  // rep jar file if used

    //---------------------- Suit enum definition --------------------
    /**
     * enum Suit used for the suit of a card.
     */
    public static enum Suit
    {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    //-------------------- Rank enum definition -----------------------
    /**
     * enum Rank used for the "value" of a card.
     */
    public  enum Rank     // symbolic names for the 13 cards
    {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    };

    //-------------------------- instance variables -----------------
    protected Rank rank = null;
    protected Suit suit = null;
    protected BufferedImage faceImage;
    protected boolean _faceUp = false;

    //------------------------ Constructor --------------------------
    /**
     * A card is identified by its rank and suit.
     *
     * @param r Rank   2, 3, ... 10, J, Q, K, A
     * @param s Suit   Hearts, Clubs, etc.
     */
    public Card()
    {

    }
    //---------------------- getAceHiFileName ------------------------
    /**
     * Get filename for the face image from the Rank when Ace is high.
     * The file names for clubs are:
     *     c1.gif == ace
     *     c2.gif == 2
     *      ...
     *     c10.gif == 10
     *     c11.gif == jack
     *     c12.gif == queen
     *     c13.gif == king
     * Other suits are the same except the first letter is d or h or s.
     *
     * @param s Suit   the suit for the card
     * @param r Rank   the rank for the card
     * @return String    file name for the card
     */
    public String getAceHiFileName( Suit s, Rank r )
    {
        String[] suitPrefix = { "c", "d", "h", "s" };
        String[] rankSuffix = { "2", "3", "4", "5", "6", "7", "8",
                "9", "10", "11", "12", "13", "1" };

        return suitPrefix[ s.ordinal() ] + rankSuffix[ r.ordinal() ]
                + ".gif";
    }
    //---------------------- getAceLoFileName ------------------------
    /**
     * Get filename for the face image from the Rank when Ace is high
     * The file names for clubs are:
     *     c1.gif == ace
     *     c2.gif == 2
     *      ...
     *     c10.gif == 10
     *     c11.gif == jack
     *     c12.gif == queen
     *     c13.gif == king
     * Other suits are the same except the first letter is d or h or s.
     *
     * @param s Suit   the suit for the card
     * @param r Rank   the rank for the card
     * @return String     file name for the card
     */
    public String getAceLoFileName( Suit s, Rank r )
    {
        String[] suitPrefix = { "c", "d", "h", "s" };
        String[] rankSuffix = { "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "10", "11", "12", "13" };

        return suitPrefix[ s.ordinal() ] + rankSuffix[ r.ordinal() ]
                + ".gif";
    }
    //---------------------- readImageFromJar -------------------------
    /**
     * Read an image of a card from a jar file.
     *
     * @param source String    the data source
     * @param cardName String  the file name of desired image
     * @return BufferedImage
     */
    protected BufferedImage readImageFromJar( String source, String cardName )
    {
        BufferedImage cardImage = null;  // initialize return to null

        try
        {
            // 1. create JarFile if not already done
            jarFile = new JarFile( source );
            // 2. get ZipEntry for "cardName" 
            ZipEntry cardEntry = jarFile.getEntry( cardName );;
            // 3. get an InputStream object from the jarFile for the
            //    ZipEntry in step 2.
            InputStream jarIn = jarFile.getInputStream( cardEntry );
            // 4. Use the static read method of ImageIO to read the 
            //    input stream as a BufferedImage object:
            cardImage =  ImageIO.read( jarIn );
        }
        catch ( IOException ex )
        {
            System.err.println( "Card image error: " + ex );
        }

        return cardImage;
    }
    //---------------------- readBackImage ----------------------------
    /**
     * Get back image for all cards; you can choose blue or red backs!
     * @param source String    the data source
     */
    public void readBackImage( String source )
    {
        String backImageName = "b1fv.gif";  // blue back
        //String backImageName = "b2fv.gif";  // red back

        backImage = readImageFromJar( source, backImageName );
    }
    //--------------------- setFaceUp( boolean ) --------------------
    /**
     * set the face up status of the card.
     *
     * @param up boolean  if true set face of card facing up 
     */
    public void setFaceUp( boolean up )
    {
        _faceUp = up;
    }
    //--------------------- getFaceUp( boolean ) --------------------
    /**
     * get the face up status of the card.
     *
     */
    public boolean getFaceUp()
    {
        return _faceUp;
    }
    //----------------------- getSuit() -------------------------
    /**
     * return the suit of this card.
     *
     * @return Suit of the card
     */
    public Suit getSuit()
    {
        return this.suit;
    }
    //--------------------- getRank ------------------------------
    /**
     * return the rank of this card.
     * @return Rank  of the card
     */
    public Rank getRank()
    {
        return rank;
    }
    //----------------- paintComponent ------------------------
    /**
     * paintComponent draws the card as either face up or face down.
     *
     * @param brush java.awt.Graphics 
     */
    public void paintComponent( java.awt.Graphics brush )
    {
        super.paintComponent( brush );
        Graphics2D brush2 = (Graphics2D) brush;
        if ( _faceUp )
            brush2.drawImage( faceImage, 0, 0, null );
        else
            brush2.drawImage( backImage, 0, 0, null );
    }

    //--------------------- mouseClicked --------------------------
    /**
     * mouseClicked -- unused.
     * @param e MouseEvent
     */
    public void mouseClicked( MouseEvent e )
    { System.out.println("Card clicked: " + this.toString());}

    //----------------------------------------------------------
    /** Unimplemented. @param e MouseEvent */
    public void mousePressed( MouseEvent e )
    { }
    /** Unimplemented. @param e MouseEvent */
    public void mouseReleased( MouseEvent e )
    { }
    /** Unimplemented. @param e MouseEvent */
    public void mouseEntered( MouseEvent e )
    { }
    /** Unimplemented. @param e MouseEvent */
    public void mouseExited( MouseEvent e )
    { }

    //--------------------- compareTo ------------------------------
    /**
     * compareTo uses the Rank as the major comparison, the Suit
     * as a secondary one.
     * @param o Card  Card to be compared with.
     * @return int   <0 , ==, >0
     */
    public int compareTo( Card o )
    {
        if ( this.rank == o.rank )
            return this.suit.ordinal() - o.suit.ordinal();
        else
            return this.rank.ordinal() - o.rank.ordinal();
    }

    //----------------------- equals ---------------------------
    /**
     * equals uses the Rank and Suit components..
     * @param o Card  object to be compared to this
     * @return boolean  true if equals
     */
    public boolean equals( Card o )
    {
        return compareTo( o ) == 0;
    }

    //-------------------------- toLongString -------------------------
    /**
     * A more verbose version of toString.
     *
     * @return String  representation of the object
     */
    public String toLongString()
    {
        return this.rank + " of " + this.suit;
    }
    //-------------------------- toString ---------------------
    /**
     * Generate a concise 2-3 character string representation of the Card.
     * @return String  representation of the object
     */
    public String toString()
    {
        String rankString = "";
        String suitString = this.suit.toString();
        suitString = suitString.substring( 0, 1 );
        switch ( this.rank )
        {
            case TWO:
                rankString = "2"; break;
            case THREE:
                rankString = "3"; break;
            case FOUR:
                rankString = "4"; break;
            case FIVE:
                rankString = "5"; break;
            case SIX:
                rankString = "6"; break;
            case SEVEN:
                rankString = "7"; break;
            case EIGHT:
                rankString = "8"; break;
            case NINE:
                rankString = "9"; break;
            case TEN:
                rankString = "10"; break;
            case JACK:
                rankString = "J"; break;
            case QUEEN:
                rankString = "Q"; break;
            case KING:
                rankString = "K"; break;
            case ACE:
                rankString = "A"; break;
        }
        return rankString + suitString;
    }
    //------------------- main unit test ---------------------------
    /**
     * Unit test.
     * @param args String[]  command line arguments
     */
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
        /*
        Card c1 = new Card( rank, suit );
        System.out.println( rank + ", " + suit +  " --- " + c1 );
        c1.setFaceUp( true );

        // Create a Space Card with rank 12,
        //     11 is either a Queen or King
        //rank = 11;
        rank = Rank.KING;
        suit = Suit.SPADES;

        Card c2 = new Card( rank, suit );
        System.out.println( rank + ", " + suit +  " --- " + c2 );
        c2.setFaceUp( true );
        /***

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
        */
    }
}
