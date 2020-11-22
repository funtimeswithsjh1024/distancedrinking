import javax.swing.*;
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
public class PyramidApp extends App{
    private JFrame PyramidPlayArea = new JFrame();


    public static void main(String[] args){
        PyramidApp p = new PyramidApp();
        p.readInPlayerNames();
    }
}