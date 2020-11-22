import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Generic App class for the Drinking Game suite.
 *
 *
 */


public abstract class App {
    protected ArrayList<String> Players;
    /**
     * Use a Scanner to
     * read in the names of all players in the game.
     *
     */
    public void readInPlayerNames(){
        Players = new ArrayList<>();
        Scanner playerInput = new Scanner(System.in);
        int numOfPlayers = -1;

        while(numOfPlayers < 1){
            System.out.println("How many players?");
            try {
                numOfPlayers = playerInput.nextInt();
                if(numOfPlayers <= 0  || numOfPlayers > 10){
                    System.out.println("Please enter a numerical value between 1 and 10.");
                    numOfPlayers = -1;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a numerical value between 1 and 10.");
                playerInput.nextLine();
            }

        }
        for(int i = 1; i <= numOfPlayers; i++){
            String name = "";
            while(name.equals("")){
                System.out.println("Enter player " + i + "'s name");
                name = playerInput.next();
            }
            Players.add(name);
            System.out.println("Player " + name + " has been added.");
        }





    }

    /**
     * Getter for the list of Players.
     * @return ArrayList of players.
     */
    public ArrayList<String> getPlayers(){return Players;}
}
