package TileMap;

import com.platformer.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileMap {
    private double x;
    private double y;

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;

    private double tween;

    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private  int height;

    private BufferedImage tileSet;
    private int numTilesAcross;
    private Tile[][] tiles;

    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize){
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT
                /tileSize + 2;
        numColsToDraw = GamePanel.WIDTH
                /tileSize + 2;
        tween = 0.07;
    }

    public void loadTiles(String s){
        try {

            tileSet = ImageIO.read(
                    new File(s)
            );


            numTilesAcross =
                    tileSet.getWidth()
                    /tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subImage;
            for (int col = 0;
                 col < numTilesAcross ;
                 col++) {
                subImage = tileSet
                        .getSubimage(
                                col * tileSize,
                                0,
                                tileSize,
                                tileSize
                        );
                tiles[0][col] =
                        new Tile(
                                subImage,
                                Tile.NORMAL
                        );
                subImage = tileSet.
                        getSubimage(
                                col * tileSize,
                                tileSize,
                                tileSize,
                                tileSize
                        );
                tiles[1][col] =
                        new Tile(
                                subImage,
                                Tile.BLOCKED
                        );

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadMap(String s){
        try{
            /*
            InputStream in =
                    getClass()
                    .getResourceAsStream(s);
                    */

            InputStream in = new FileInputStream(s);
            BufferedReader br = new
                    BufferedReader(
                       new InputStreamReader(in)
            );
            numCols = Integer.parseInt(
                    br.readLine()
            );
            numRows = Integer.parseInt(
                    br.readLine()
            );
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xMin = GamePanel.WIDTH - width;
            xMax = 0;
            yMin = GamePanel.HEIGHT - height;
            yMax = 0;

            String delims = "\\s+";

            for (int row = 0;
                 row < numRows ; row++) {
                String line = br.readLine();
                String[] tokens =
                        line.split(delims);
                for (int col = 0;
                     col < numCols ;
                     col++) {
                    map[row][col] =
                            Integer
                            .parseInt(
                                    tokens[col]
                            );
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getTileSize(){
        return tileSize;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getType(int row, int col){
        int rowColumn =
                map[row][col];
        int r = rowColumn
                /numTilesAcross;
        int c = rowColumn
                % numTilesAcross;
        return tiles[r][c].getType();
    }

    public void setPosition(
            double x,
            double y
    ){
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) -this.x/tileSize;
        rowOffset = (int) -this.y/tileSize;



    }

    private void fixBounds(){
        if(x < xMin) x = xMin;
        if(y < yMin) y = yMin;
        if(x > xMax) x = xMax;
        if(y > yMax) y =  yMax;
    }

    public void draw(Graphics2D g){
        for (int row = rowOffset;
             row < rowOffset + numRowsToDraw;
             row++) {

            if(row >= numRows) break;

            for (int col = colOffset;
                 col < colOffset + numColsToDraw;
                 col++) {

                if(col >=numCols) break;

                if(map[row][col] == 0){
                    continue;
                }
                int rowColumn =
                        map[row][col];
                int r = rowColumn
                        /numTilesAcross;
                int c = rowColumn
                        % numTilesAcross;

                g.drawImage(
                        tiles[r][c].getImage(),
                        (int) x
                                + col * tileSize,
                        (int)y
                            + row * tileSize,
                        null

                );
            }
        }
    }

    public void setTween(double t){
        tween = t;
    }



}
