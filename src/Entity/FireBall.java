package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FireBall extends MapObject {

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;


    FireBall(
            TileMap tm,
            boolean right
    ){

        super(tm);

        moveSpeed = 3.8;

        if(right) dx = moveSpeed;
        else dx = -moveSpeed;

        width = 30;
        height  = 30;
        cWidth = 14;
        cHeight = 14;

        try{
            BufferedImage spriteSheet =
                    ImageIO.read(
                      new File("D:\\david\\JavaPlatformer\\Resources\\Sprites\\Player\\fireball.gif")
                    );
            sprites = new BufferedImage[4];
            for (int i = 0; i < sprites.length ; i++) {
                sprites[i] = spriteSheet.getSubimage(
                        i * width,
                        0,
                        width,
                        height
                );

            }

            hitSprites = new BufferedImage[3];
            for (int i = 0; i < hitSprites.length ; i++) {
                hitSprites[i] = spriteSheet.getSubimage(
                        i * width,
                        height,
                        width,
                        height
                );
            }

            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(70);








        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }




    }


    public void setHit(){
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }

    public boolean shouldRemove(){
        return remove;
    }

    public void update(){
        checkTileMapCollision();
        setPosition(xTemp, yTemp);

        if(dx == 0 && !hit){
            setHit();
        }

        animation.update();

        if(hit && animation.hasPlayedOnce()){
            remove = true;
        }


    }

    public void draw(Graphics2D g){
        setMapPosition();

        if(facingRight){
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xMap -width/2),
                    (int)(y + yMap - height/2),
                    null
            );
        }else{
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xMap -width/2 + width),
                    (int)(y + yMap - height/2),
                    -width,
                    height,
                    null
            );
        }

    }



}
