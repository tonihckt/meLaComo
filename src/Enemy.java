import javax.swing.*;
import java.awt.*;

/**
 * @author MeLaComo S.A.
 */

public class Enemy extends JPanel {
    private int posX;
    private int posY;
    private int disX = 1;
    private int disY = 1;
    private final int WIDTH = 20;
    private final int HEIGHT= 20;
    private Game game;

    public Enemy(Game game){
        this.game = game;
        this.posX = (int) (Math.random() * 390);
        this.posY = (int) (Math.random() * 390);
    }

    public void move() {
        if(posX + disX > game.getWidth() - this.WIDTH){
            disX = -1;
        }
        if(posX + disX < 0){
            disX = 1;
        }
        if(posY + disY < 0){
            disY = 1;
        }
        if(posY + disY > game.getHeight() - this.HEIGHT){
            disY = -1;
        }

        posX = posX + disX;
        posY = posY + disY;
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(posX, posY, WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g){
        g.fillRect(posX, posY, WIDTH, HEIGHT);
    }
}
