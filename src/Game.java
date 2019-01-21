import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @author MeLaComo S.A.
 */

public class Game extends JPanel {
    private Player player;
    private ArrayList<Enemy>enemies;

    public Game(){
        // inicializar los objetos del juego
        player = new Player(this);
        setEnemies(new ArrayList());
        // añande un enemigo
        addEnemies();
        // añade keyListener
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    public static void main(String[] args) throws InterruptedException {
        //System.out.println("Hello World!");
        Game game = new Game();

        JFrame frame = new JFrame("(¬‿¬) Me La Como");
        frame.add(game);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        int iterations = 0;
        while(true){
            game.move();
            game.repaint();
            Thread.sleep(10);
            // Se añaden enemigos cada 3 Segundos
            iterations++;
            if(iterations % 500 == 0){
                game.addEnemies();
            }
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g); // Necesario para borrar la pantalla antes de volver a pintar
        for(Enemy enemy: getEnemies()){
            enemy.paint(g);
        }
        player.paint(g);
    }

    /**
     * Se encargará de mover los elementos del juego
     */

    private void move() {
        player.move();
        for(Enemy enemy: getEnemies()){
            enemy.move();
        }
    }

    // crea un nuevo enemigo
    private void addEnemies() {
        Enemy enemy = new Enemy(this);
        getEnemies().add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game over", "Game over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}
