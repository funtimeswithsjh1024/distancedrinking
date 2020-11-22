import javax.swing.*;
import java.util.ArrayList;

/**
 * A Pyramid of Cards, to be used in the Pyramid game.
 * Maybe it will be applicable to other games, too.
 */


public class Pyramid
{

    /**
     * Constructor for a row of cards.
     *
     * @param cards : Cards to be put in the row.
     */
    ArrayList<Row> PyramidRows = new ArrayList<>();
    public Pyramid(PyramidDeck d, int size) throws Exception{
        int totalCards = 0;
        for(int i = 1; i <= size; i++){
            totalCards += i;

        }
        System.out.println("Total cards: " + totalCards);

        if(totalCards > d.getSize()){
            throw new IllegalArgumentException();
        }
        else{
            d.shuffleDeck();
            PyramidRows = new ArrayList<>();
            //Create a Row of cards, then "stack" them into a pyramid
            //by forming an ArrayList of ArrayLists
            for(int j = 0; j <= size; j++){
                Row r = new Row(d, j);
                PyramidRows.add(j, r);
            }
        }
    }
    /**
     * Getter for the Pyramid itself.
     * @return
     */
    public ArrayList<Row> getPyramid(){
        return PyramidRows;
    }
    /**
     * Prints out current contents of row as a string.
     * @return String: a list of the cards in the deck.
     */
    public String toString(){
        String rows = "";
        for(Row r : PyramidRows){
            rows += r.toString();
            rows += "\n";
        }
        return rows;
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
     * @param xPos: Initial X position of the pyramid (where pyramid's "top" will lie.)
     * @param yPos: Initial Y position of the pyramid (where pyramid's "top" starts.)
     */
    public void paintPyramid(JFrame frame, int xPos, int yPos){
        for(Row r: PyramidRows){

            r.paintRow(frame, xPos, yPos);
            xPos -= 50;
            yPos += 100;
        }
    }
    /**
     * Main method tests the Row class.
     * @param args arguments
     */
    public static void main(String[] args){
        PyramidDeck d = new PyramidDeck();

        d.shuffleDeck();

        JFrame f = new JFrame();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setLayout( null );

        try{
            Pyramid p = new Pyramid(d, 5);
            p.paintPyramid(f, 200, 50);
            System.out.println(p.toString());
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: There are not enough cards left " +
                    "in the deck to make a pyramid of that size.");
        }

        f.setSize( 600, 600 );
        f.setVisible( true );





    }
}