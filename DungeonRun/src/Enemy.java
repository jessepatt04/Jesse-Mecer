//Jesse A P

import java.util.Random;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Studio20-10
 */
public class Enemy {

    private int x, y;
    private boolean toggle = true;

    public Enemy(int x, int y) {//Enemy pos
        this.x = x;
        this.y = y;
    }

    public char[][] moveHorizontal(char[][] map) {
        if (toggle) {
            if (map[x - 1][y] == 'P') {
                JOptionPane.showMessageDialog(null, "You Lose!");
                System.exit(0);
            }
            if (map[x - 1][y] != '#') {
                map[x][y] = ' ';
                x--;
                map[x][y] = '1';
            } else {
                toggle = false;
                if (map[x + 1][y] != '#') {
                    map[x][y] = ' ';
                    x++;
                    map[x][y] = '1';
                }
            }
        } else {
            if (map[x + 1][y] == 'P') {
                JOptionPane.showMessageDialog(null, "You Lose!");
                System.exit(0);
            }
            if (map[x + 1][y] != '#') {
                map[x][y] = ' ';
                x++;
                map[x][y] = '1';
            } else {
                toggle = true;
                if (map[x - 1][y] != '#') {
                    map[x][y] = ' ';
                    x--;
                    map[x][y] = '1';
                }
            }
        }
        return map;
    }

    public char[][] moveVerticle(char[][] map) {
        if (toggle) {
            if (map[x][y - 1] == 'P') {
                JOptionPane.showMessageDialog(null, "You Lose!");
                System.exit(0);
            }
            if (map[x][y - 1] != '#') {
                map[x][y] = ' ';
                y--;
                map[x][y] = '2';
            } else {
                toggle = false;
                if (map[x][y + 1] != '#') {
                    map[x][y] = ' ';
                    y++;
                    map[x][y] = '2';
                }
            }
        } else {
            if (map[x][y + 1] == 'P') {
                JOptionPane.showMessageDialog(null, "You Lose!");
                System.exit(0);
            }
            if (map[x][y + 1] != '#') {
                map[x][y] = ' ';
                y++;
                map[x][y] = '2';
            } else {
                toggle = true;
                if (map[x][y - 1] != '#') {
                    map[x][y] = ' ';
                    y--;
                    map[x][y] = '2';
                }
            }
        }
        return map;
    }

    public char[][] lava(char[][] map) {

        if (map[x][y] == 'l') {
            map[x][y] = 'L';
            toggle = false;
        } else if (map[x][y] == 'L') {
            map[x][y] = 'l';
            toggle = true;
        } else if (map[x][y] == ' ' && toggle) {
            map[x][y] = 'l';
        } else if (map[x][y] == ' ' && !toggle) {
            map[x][y] = 'L';
        } else if (map[x][y] == 'P' && toggle) {
            JOptionPane.showMessageDialog(null, "You lose!");
            System.exit(0);
        }

        return map;
    }

    public char[][] lavaRandom(char[][] map) {

        if (map[x][y] == 'v') {
            map[x][y] = 'l';
            toggle = true;
        } else if (map[x][y] == 'l') {
            map[x][y] = 'L';
            toggle = false;
        } else if (map[x][y] == 'L') {
            map[x][y] = ' ';
            do {
                Random rngx = new Random();
                Random rngy = new Random();
                int newx = rngx.nextInt(map.length);
                int newy = rngy.nextInt(map[0].length);
                if (map[newx][newy] == ' ') {
                    x = newx;
                    y = newy;
                    map[x][y] = 'v';
                    break;
                }
            } while (true);
        } else if (map[x][y] == ' ' && toggle) {
            map[x][y] = 'l';
        } else if (map[x][y] == ' ' && !toggle) {
            map[x][y] = 'L';
        } else if (map[x][y] == 'P' && toggle) {
            JOptionPane.showMessageDialog(null, "You lose!");
            System.exit(0);
        }
        return map;
    }
}
