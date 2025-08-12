package tictactoe;

import java.util.Scanner;

/**
 *
 * @author Studio20-10
 */
public class Game {

    String[] gameArr = new String[9];
    int num = 0;

    public String[] getGameArr() {
        return gameArr;
    }

    public Game() {
        for (int i = 0; i < 9; i++) {
            gameArr[i] = Integer.toString(i + 1);
        }
    }

    public void changeElement(int pos, String value) {
        if (gameArr[pos].equals("X") || gameArr[pos].equals("O")) {
            System.out.println("Already taken");
            turn(value);
        } else {
            gameArr[pos] = value;
        }
    }

    public void prompt() {
        System.out.println("|" + getGameArr()[0] + "|" + getGameArr()[1] + "|" + getGameArr()[2] + "|");
        System.out.println("-------\n|" + getGameArr()[3] + "|" + getGameArr()[4] + "|" + getGameArr()[5] + "|");
        System.out.println("-------\n|" + getGameArr()[6] + "|" + getGameArr()[7] + "|" + getGameArr()[8] + "|\n");
    }

    public void turn(String choice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Player " + choice + " choose a number");
        int pos = input.nextInt();
        pos--;
        if (pos >= 0 && pos <= 8) {
            changeElement(pos, choice);
        } else {
            System.out.println("Out of Bounds Error try again");
            turn(choice);
        }
        
    }

    public boolean hasWon(String value) {
        String line = "";
        num++;
        for (int i = 1; i <= 8; i++) {
            switch (i) {
                case 1:
                    line = gameArr[0] + gameArr[1] + gameArr[2];
                    break;
                case 2:
                    line = gameArr[3] + gameArr[4] + gameArr[5];
                    break;
                case 3:
                    line = gameArr[6] + gameArr[7] + gameArr[8];
                    break;
                case 4:
                    line = gameArr[0] + gameArr[3] + gameArr[6];
                    break;
                case 5:
                    line = gameArr[1] + gameArr[4] + gameArr[7];
                    break;
                case 6:
                    line = gameArr[2] + gameArr[5] + gameArr[8];
                    break;
                case 7:
                    line = gameArr[0] + gameArr[4] + gameArr[8];
                    break;
                case 8:
                    line = gameArr[2] + gameArr[4] + gameArr[6];
                    break;
            }
            
            if (line.equals("XXX") || line.equals("OOO")) {
                System.out.println(value + " wins!");
                return false;
            }else if(num==9){
            System.out.println("\nDraw");
            return false;
        }
        }

        return true;
    }

}