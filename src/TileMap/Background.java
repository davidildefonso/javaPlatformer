package TileMap;

import com.platformer.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Background {
    private BufferedImage image;
    private double x;
    private  double y;
    private  double dx;
    private  double dy;

    private double   moveScale;

    public Background(String path, double ms){
        try {
         /*   image = ImageIO.read(
                    getClass().
                    getResourceAsStream("/Backgrounds/menubg.gif")
            );*/
            image = ImageIO.read(
                    new File(path)
            );
            System.out.println(image);
            moveScale = ms;
        }catch (IllegalArgumentException e){
            System.out.println("illegal !!");
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y){
        this.x = (x * moveScale)
                % GamePanel.WIDTH;
        this.y = (y * moveScale)
                % GamePanel.HEIGHT;
        this.y = y;
    }

    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g){

        g.drawImage(
                image,
                (int) x,
                (int) y,
                null
        );
        if(x < 0){
            g.drawImage(
               image,
              (int) x + GamePanel.WIDTH,
               (int) y,
             null
            );
        }
        if(x > 0){
            g.drawImage(
                    image,
                    (int) x - GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }


    }

}
