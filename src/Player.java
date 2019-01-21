import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author MeLaComo S.A.
 */

public class Player extends JPanel {

    private int posX;
    private int posY;
    private int disX;
    private int disY;
    private final int WIDTH = 10;
    private final int HEIGHT= 10;
    private Game game;

    public Player (Game game){
        this.game = game;
        this.posX = 195;
        this.posY = 195;
    }

    public void move() {
        if(posX + disX > game.getWidth() - this.WIDTH ){
            disX = 0;
        }
        if(posX + disX < 0){
            disX = 0;
        }
        if(posY + disY < 0){
            disY = 0;
        }
        if(posY + disY > game.getHeight() - this.HEIGHT){
            disY = 0;
        }
        // comprobamos si existe un choque
        if(collision()){
            game.gameOver();
        }
        posX = posX + disX;
        posY = posY + disY;
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(posX, posY, WIDTH, HEIGHT);
    }

    private boolean collision() {
        for(Enemy enemy:game.getEnemies()){
            if(enemy.getBounds().intersects(this.getBounds())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(posX, posY, WIDTH, HEIGHT);
    }

    void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            disX = -1;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            disX = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            disY = -1;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            disY = 1;
        }
    }
}
