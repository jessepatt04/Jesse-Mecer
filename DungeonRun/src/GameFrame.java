//Jesse A P

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Studio20-10
 */
public final class GameFrame extends JFrame implements KeyListener {

    private int xPos, yPos, points;//Start pos and Start points
    private boolean lVisualBool = false;//Temp boolean for l enemy for better visuals
    private static int level = 0;//Static levels saves between levels since each level is a object
    private final GameFloor floor = new GameFloor();
    private final GamePanel panel = new GamePanel();
    private final char[][] map;
    private final ArrayList<Enemy> xEnemyList = new ArrayList<>();//Left to Right
    private final ArrayList<Enemy> yEnemyList = new ArrayList<>();//Up to Down
    private final ArrayList<Enemy> lEnemyList = new ArrayList<>();//Lava
    private final ArrayList<Enemy> vEnemyList = new ArrayList<>();//Random Lava
    //Game Tick 400ms new Thread
    private final ScheduledExecutorService scheduler400 = Executors.newSingleThreadScheduledExecutor();
    //Game Tick 800ms new Thread
    private final ScheduledExecutorService scheduler800 = Executors.newSingleThreadScheduledExecutor();

    public GameFrame() {
        map = floor.getMap(level);//Gets level
        level++;//Preps next level
        setTitle("Floor " + level);
        setSize(map.length * 40 + 16, map[0].length * 40 + 39);
        add(panel);
        addKeyListener(this);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setPos();//Gets Player pos and counts points on  board
        setEnemyList();//Sets all enemies 
        setLocationRelativeTo(null);
        scheduler400.scheduleAtFixedRate(() -> {//Starts 400ms Thread
            gameTick400();
        }, 0, 400, TimeUnit.MILLISECONDS);
        scheduler800.scheduleAtFixedRate(() -> {//Starts 800ms Thread
            gameTick800();
        }, 0, 800, TimeUnit.MILLISECONDS);
    }

    public void setEnemyList() {//Sets enemies
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '1') {
                    xEnemyList.add(new Enemy(i, j));
                }
                if (map[i][j] == '2') {
                    yEnemyList.add(new Enemy(i, j));
                }
                if (map[i][j] == 'l' || map[i][j] == 'L') {
                    lEnemyList.add(new Enemy(i, j));
                }
                if (map[i][j] == 'v') {
                    vEnemyList.add(new Enemy(i, j));
                }
            }
        }
    }

    public void setPos() {//Sets pos and point count
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'P') {
                    xPos = i;
                    yPos = j;
                }
                if (map[i][j] == 'c') {
                    points++;
                }
            }
        }
    }

    class GamePanel extends JPanel {//Inner class panel

        private final int TILE = 40;

        @Override
        protected void paintComponent(Graphics g) {//Draws visuals
            super.paintComponent(g);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    char space = map[i][j];
                    switch (space) {//Checks map pos and draws map
                        case ' ' -> {
                            g.setColor(Color.WHITE);
                        }
                        case '#' -> {
                            g.setColor(Color.GRAY);
                        }
                        case 'P' -> {
                            g.setColor(new Color(200, 150, 100));
                        }
                        case '0' -> {
                            g.setColor(Color.green);
                        }
                        case '1' -> {
                            g.setColor(Color.red);
                        }
                        case '2' -> {
                            g.setColor(Color.blue);
                        }
                        case 'l' -> {
                            g.setColor(Color.LIGHT_GRAY);
                        }
                        case 'L' -> {
                            g.setColor(Color.orange);
                        }
                        case 'c' -> {
                            g.setColor(Color.yellow);
                        }
                        case 'v' -> {
                            g.setColor(Color.WHITE);
                        }
                        default -> {
                            g.setColor(Color.GRAY);
                        }
                    }
                    g.fillRect(i * TILE, j * TILE, TILE, TILE);
                    g.setColor(Color.BLACK);
                    g.drawRect(i * TILE, j * TILE, TILE, TILE);
                }
            }
        }
    }

    public int movePlayer(int x, int y) {
        //gameTick();
        int newx = xPos;
        int newy = yPos;
        newx += x;//Checks spot that user wants to go
        newy += y;

        if (map[newx][newy] == '0') {//If win spot
            if (points != 0) {//Has not collected all points will not move since return ends method
                return 0;
            }
            this.dispose();
            JOptionPane.showMessageDialog(null, "You Win!");//If collected all points win
            SwingUtilities.invokeLater(GameFrame::new);
        }

        if (map[newx][newy] == '1' || map[newx][newy] == '2' || map[newx][newy] == 'L') {//if you move on bad tile
            JOptionPane.showMessageDialog(null, "You Lose!");
            System.exit(0);
        }

        if (map[newx][newy] == 'c') {//Point count decrease when you collect points so when it gets to 0 you can win
            points--;
        }

        if (map[newx][newy] != '#' && lVisualBool) {//no wall and you are on top of lava
            if (map[newx][newy] != 'l') {//if the next spot is not lava
                lVisualBool = false;//save it to no lava under player
            }
            map[xPos][yPos] = 'l';
            xPos = newx;
            yPos = newy;
            map[xPos][yPos] = 'P';
        } else if (map[newx][newy] != '#' && map[newx][newy] == 'l') {//no wall and going on top of lava
            lVisualBool = true;//lava under player
            map[xPos][yPos] = ' ';
            xPos = newx;
            yPos = newy;
            map[xPos][yPos] = 'P';
        } else if (map[newx][newy] != '#') {//no wall
            map[xPos][yPos] = ' ';
            xPos = newx;
            yPos = newy;
            map[xPos][yPos] = 'P';
        }

        panel.repaint();//repaint screen
        return 0;
    }

    public void gameTick400() {//Enemy x and y tick
        for (Enemy xenemy : xEnemyList) {
            xenemy.moveHorizontal(map);
        }
        for (Enemy yenemy : yEnemyList) {
            yenemy.moveVerticle(map);
        }

        panel.repaint();
    }

    public void gameTick800() {// Enemy l and v tick
        for (Enemy lenemy : lEnemyList) {
            lenemy.lava(map);
        }
        for (Enemy venemy : vEnemyList) {
            venemy.lavaRandom(map);
        }
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {//USer input
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            movePlayer(0, -1);
        }
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            movePlayer(0, 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            movePlayer(-1, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movePlayer(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
