package GameState;

import TileMap.TileMap;
import TileMap.Background;

import com.platformer.GamePanel;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background background;

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

        background = new Background(
          "D:\\david\\JavaPlatformer\\Resources\\Backgrounds\\grassbg1.gif",
          0.1
        );
    }

    public void update(){

    }

    public void draw(Graphics2D g){

        background.draw(g);

        tileMap.draw(g);
    }

    public void keyPressed(int k){

    }

    public void keyReleased(int k){

    }


}
