package GameState;

import TileMap.TileMap;
import com.platformer.GamePanel;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;

    Level1State(
            GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        init();
    }

    public void init(){
        tileMap = new TileMap(30);
        tileMap.loadTiles("D:\\david\\JavaPlatformer\\Resources\\Tilesets\\grasstileset.gif");
        tileMap.loadMap("D:\\david\\JavaPlatformer\\Resources\\Maps\\level1-1.map");
        tileMap.setPosition(0, 0);


    }

    public void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect(
                0,
                0,
                GamePanel.WIDTH,
                GamePanel.HEIGHT);
        tileMap.draw(g);
    }

    public void keyPressed(int k){

    }

    public void keyReleased(int k){

    }


}
