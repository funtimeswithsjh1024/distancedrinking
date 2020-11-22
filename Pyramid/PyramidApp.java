import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

/**
 * Application for the Pyramid drinking game.
 * This game runs under the assumption of the following rules:
 *
 * Try to climb the pyramid by flipping one card of your choice
 * on each row.
 *
 * If you flip over a face card, drink once if you're on the bottom row,
 * twice for the next, etc, then start again from the bottom.
 *
 * You keep playing until you run out of cards or you make it to the top.
 * If you make it to the top, pass it along to the next player.
 *
 * If you run out of cards you have to finish your drink!
 *
 *
 * @author Sarah J. Hall 2020
 * @version 1.0
 */
public class PyramidApp extends App implements MouseListener{
    private JFrame PyramidPlayArea;
    private Pyramid GamePyramid;
    private int CurrentRow = 5;
    private PyramidCard MostRecentlySelected;
    private PyramidDeck GameDeck;

    public PyramidApp(){
        //read in player names
        this.readInPlayerNames();

        // Set up the JFrame for the Pyramid
        PyramidPlayArea = new JFrame();
        PyramidPlayArea.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        PyramidPlayArea.setLayout( null );

        //Get the "cards" ready for the Pyramid
        GameDeck = new PyramidDeck();
        GamePyramid = new Pyramid(GameDeck, 5);

        //Draw and display the pyramid game.
        GamePyramid.paintPyramid(PyramidPlayArea, 300, -50);
        PyramidPlayArea.setSize( 600, 600 );
        PyramidPlayArea.setVisible( true );
    }


    /**
     * Play the pyramid game.
     */
    public void playGame(){
        while(CurrentRow > 0){
            for(Card c: GamePyramid.getPyramid().get(CurrentRow).getRow()){
                if(c.getFaceUp()){
                    if(c.isFaceCard()){
                        // Picked face card; restart player at bottom
                        System.out.println("You picked a face card!\n" +
                                "Drink " + CurrentRow + " times.\n" +
                                "Start again from the bottom!");
                        // Go through the whole pyramid and replace cards
                        // that are face up with fresh ones from the deck.
                        // If there are 0 cards left in the deck,
                        // the player needs to finish their drink.
                        for(Row r: GamePyramid.getPyramid()){
                            //needs to be index because you need the index
                            //of card to replace
                            for(int i = 0; i < r.getRow().size(); i++){
                                //if card at specified index is face up,
                                //replace it with a new card.
                                if(r.getRow().get(i).getFaceUp()){
                                    //if replacement fails,
                                    //throw a game over
                                    if(!r.replaceCard(GameDeck, i)){
                                        System.out.println("You have run out of cards!" +
                                                "\n Finish your drink!");
                                    }

                                }

                            }
                            //r.paintRow(PyramidPlayArea, r.getXLocation(), r.getYLocation());
                        }
                        CurrentRow = 5;
                        c.setFaceUp(false);

                    }
                    else{
                        CurrentRow--;
                    }
                    //PyramidPlayArea.repaint();
                }
            }
        }
        System.out.println("Congrats! You won!");
    }

    public static void main(String[] args){
        PyramidApp p = new PyramidApp();
        p.playGame();




    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}