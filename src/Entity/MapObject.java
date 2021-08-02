package Entity;

import TileMap.TileMap;

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


    public void checkTileMapCollision(){
        currentCol = (int)x / tileSize;
        currentRow = (int)y / tileSize;

        xDestination = x + dx;
        yDestination = y + dy;

        xTemp = x;
        yTemp = y;

    }

}
