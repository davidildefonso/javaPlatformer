package GameState;

import Entity.Enemies.Slugger;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import TileMap.TileMap;
import TileMap.Background;

import com.platformer.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background background;

    private Player player;

    private ArrayList<Enemy> enemies;

    private ArrayList<Explosion> explosions;

    private HUD hud;



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
        tileMap.setTween(1);

        background = new Background(
          "D:\\david\\JavaPlatformer\\Resources\\Backgrounds\\grassbg1.gif",
          0.1
        );

        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();

        explosions = new ArrayList<Explosion>();


        hud = new HUD(player);
    }

    public void populateEnemies(){
        enemies = new ArrayList<Enemy>();
        Slugger s;

        Point[] points = new Point[]{
                new Point(200,100),
                new Point(860,200),
                new Point(1525,200),
                new Point(1680,200),
                new Point(1800,200)
        };

        for (int i = 0; i < points.length ; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x,
                    points[i].y);
            enemies.add(s);
        }



    }

    public void update(){
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH /2
                        - player.getX(),
                GamePanel.HEIGHT/2 -
                        player.getY()
        );

        background.setPosition(
                tileMap.getX(),
                tileMap.getY()
        );


        player.checkAttack(enemies);


        for (int i = 0; i < enemies.size() ; i++) {
           Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()){
                enemies.remove(i);
                i--;
                explosions.add(new Explosion(
                    e.getX(),
                    e.getY()

                ));
            }
        }

        for (int i = 0; i < explosions.size() ; i++) {
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()){
                explosions.remove(i);
                i--;
            }
        }

    }

    public void draw(Graphics2D g){

        background.draw(g);

        tileMap.draw(g);

        player.draw(g);

        for (int i = 0; i < enemies.size() ; i++) {
            enemies.get(i).draw(g);
        }

        for (int i = 0; i < explosions.size() ; i++) {
            explosions.get(i).
                setMapPosition(
                        (int) tileMap.getX(),
                        (int) tileMap.getY()
                );
            explosions.get(i).draw(g);
        }

        hud.draw(g);
    }

    public void keyPressed(int k){
        if(k == KeyEvent.VK_LEFT){
            player.setLeft(true);
        }
        if(k == KeyEvent.VK_RIGHT){
            player.setRight(true);
        }
        if(k == KeyEvent.VK_UP){
            player.setUp(true);
        }
        if(k == KeyEvent.VK_DOWN){
            player.setDown(true);
        }
        if(k == KeyEvent.VK_SPACE){
            player.setJumping(true);
        }
        if(k == KeyEvent.VK_Z){
            player.setScratching();
        }
        if(k == KeyEvent.VK_C){
            player.setFiring();
        }
        if(k == KeyEvent.VK_CONTROL){
            player.setGliding(true);
        }
    }

    public void keyReleased(int k){
        if(k == KeyEvent.VK_LEFT){
            player.setLeft(false);
        }
        if(k == KeyEvent.VK_RIGHT){
            player.setRight(false);
        }
        if(k == KeyEvent.VK_UP){
            player.setUp(false);
        }
        if(k == KeyEvent.VK_DOWN){
            player.setDown(false);
        }
        if(k == KeyEvent.VK_SPACE){
            player.setJumping(false);
        }
        if(k == KeyEvent.VK_CONTROL){
            player.setGliding(false);
        }
    }


}
