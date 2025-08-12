//Jesse Araujo Pattison
package tictactoe;

/**
 *
 * @author Studio20-10
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean gameState = true;
        Game ttt = new Game();
        System.out.println("Welcome to TicTacToe!\n");
        while(gameState == true){
            
        ttt.prompt();
        
        ttt.turn("X");
        gameState = ttt.hasWon("X");
        if(gameState == false){break;}
        
        ttt.prompt();
        
        ttt.turn("O");
        gameState = ttt.hasWon("O");
        }
        
        System.out.println("\nFinal Board:");
        ttt.prompt();
        
    }
    
}
