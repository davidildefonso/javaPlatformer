package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Slugger extends Enemy {

    private BufferedImage[] sprites;

    public Slugger(TileMap tm){
        super(tm);
        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 0.2;

        width = 30;
        height = 30;
        cWidth = 20;
        cHeight = 20;

        health = maxHealth = 2;
        damage = 1;

        try{
            BufferedImage spriteSheet = ImageIO.read(
                    new File("D:\\david\\JavaPlatformer\\Resources\\Sprites\\Enemies\\slugger.gif")
            );

            sprites = new BufferedImage[3];

            for (int i = 0; i < sprites.length ; i++) {
                sprites[i] = spriteSheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = true;










    }

    private void getNextPosition(){
        if(left){
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else if(right){
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }

        if(falling){
            dy += fallSpeed;

        }

    }

    public void update(){

        getNextPosition();
        checkTileMapCollision();
        setPosition(xTemp, yTemp);

        if(flinching){
            long elapsed = (System.nanoTime() -
                    flinchTimer)/1000000;
            if(elapsed > 400){
                flinching = false;
            }
        }

        if(right && dx == 0){
            right = false;
            left = true;
            facingRight = false;
        }else if(left && dx == 0){
            right = true;
            left = false;
            facingRight = true;
        }

        animation.update();



    }


    public void draw(Graphics2D g){
     //   if(notOnScreen()) return;

        setMapPosition();

        super.draw(g);
    }













}
