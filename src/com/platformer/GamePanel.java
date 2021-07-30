package com.platformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel
implements Runnable, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCALE = 1;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;


    GamePanel(){
        super();
        setPreferredSize(
                new Dimension(
                        WIDTH * SCALE,
                        HEIGHT * SCALE
                )
        );
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void init(){
        image = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );
        g = (Graphics2D) g;
        running = true;

    }

    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        while(running){
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime()
                    - start;

            wait = targetTime
                    - elapsed /1000000;
            try{
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public void update() {

    }

    public void draw(){

    }

    public void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(
                image,
                0,
                0,
                null);
        g2.dispose();
    }


}
