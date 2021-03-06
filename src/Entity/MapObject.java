package Entity;

import TileMap.TileMap;
import TileMap.Tile;
import com.platformer.GamePanel;

import javax.security.sasl.RealmCallback;
import java.awt.*;

public abstract class MapObject {
    protected TileMap tileMap;
    protected int tileSize;
    protected double xMap;
    protected double yMap;

    protected double x;
    protected  double y;
    protected  double dx;
    protected  double dy;

    protected  int width;
    protected  int height;

    protected int cWidth;
    protected  int cHeight;

    protected  int currentRow;
    protected  int currentCol;
    protected double xDestination;
    protected  double yDestination;
    protected  double xTemp;
    protected  double yTemp;
    protected  boolean topLeft;
    protected boolean topRight;
    protected  boolean bottomRight;
    protected  boolean bottomLeft;

    protected Animation animation;
    protected int currentAction;
    protected  int previousAction;
    protected  boolean facingRight;

    protected  boolean left;
    protected boolean right;
    protected  boolean up;
    protected  boolean down;
    protected  boolean jumping;
    protected  boolean falling;

    protected double moveSpeed;
    protected  double maxSpeed;
    protected  double stopSpeed;
    protected double fallSpeed;
    protected  double maxFallSpeed;
    protected double jumpStart;
    protected  double stopJumpSpeed;


    MapObject(TileMap tm){
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(MapObject obj){
        Rectangle rectangle1 =
                getRectangle();
        Rectangle rectangle2 =
                obj.getRectangle();

        return rectangle1.intersects(rectangle2);
    }

    public Rectangle getRectangle(){
        return new Rectangle(
                (int)x - cWidth,
                (int)y - cHeight,
                cWidth,
                cHeight
        );
    }

    public void calculateCorners(
            double x,
            double y
    ){
        int leftTile =
                (int)(x - cWidth/2)/tileSize;
        int rightTile =
                (int)(x + cWidth/2 - 1)/tileSize;
        int topTile =
                (int)(y - cHeight/2)/tileSize;
        int bottomTile =
                (int)(y + cHeight/2 - 1)/tileSize;

        int tl = tileMap.getType(
                topTile, leftTile
        );
        int tr = tileMap.getType(
                topTile, rightTile
        );
        int bl = tileMap.getType(
                bottomTile, leftTile
        );
        int br = tileMap.getType(
                bottomTile, rightTile
        );

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    }

    public void checkTileMapCollision(){
        currentCol = (int)x / tileSize;
        currentRow = (int)y / tileSize;

        xDestination = x + dx;
        yDestination = y + dy;

        xTemp = x;
        yTemp = y;

        calculateCorners(x, yDestination);

        if(dy < 0){
            if(topLeft || topRight){
                dy = 0;
                yTemp =
                  currentRow * tileSize
                          + cHeight/2;


            }else{
                yTemp += dy;
            }
        }

        if(dy > 0){
            if(bottomLeft || bottomRight ){
                dy = 0;
                falling = false;
                yTemp =
                        (currentRow + 1)
                        * tileSize
                        -cHeight/2;
            }else{
                yTemp +=dy;
            }
        }

        calculateCorners(xDestination, y);


        if(dx < 0){
            if(topLeft || bottomLeft){
                dx = 0;
                xTemp =
                        currentCol * tileSize
                                + cWidth/2;


            }else{
                xTemp += dx;
            }
        }

        if(dx > 0){
            if( topRight || bottomRight){
                dx = 0;
                xTemp =
                        (currentCol + 1)
                                * tileSize
                                -cWidth/2;
            }else{
                xTemp +=dx;
            }
        }

        if(!falling){
            calculateCorners(
                    x,
                    yDestination + 1);
            if(!bottomLeft && !bottomRight){
                falling = true;
            }

        }

    }


    public int getX() { return (int) x ;}
    public int getY() { return (int) y ;}
    public int getWidth() { return width ;}
    public int getHeight() { return height ;}
    public int getCWidth() { return cWidth ;}
    public int getCHeight() { return cHeight ;}

    public void setPosition(
            double x,
            double y
    ){
        this.x = x;
        this.y = y;
    }

    public void setVector(
            double dx,
            double dy
    ){
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition(){
        xMap = tileMap.getX();
        yMap  = tileMap.getY();
    }

    public void setLeft(boolean b){ left = b; }
    public void setRight(boolean b){ right = b; }
    public void setUp(boolean b){ up = b; }
    public void setDown(boolean b){ down = b; }
    public void setJumping(boolean b){ jumping = b; }


    public  boolean notOnScreen(){
        return  x + xMap + width < 0 ||
                x+ xMap - width > GamePanel.WIDTH ||
                y + yMap + height < 0 ||
                y + yMap - height > GamePanel.HEIGHT;
    }


    public void draw(Graphics2D g){
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
