package main;

import javax.swing.*;

import entity.Player;
import entity.Slime;
import item.Item;
import item.Projectile;
import tile.TileManager;

import java.awt.*;
// import java.io.BufferedInputStream;
// import java.awt.Image;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public final int tileSize = 16;
    final int scale = 3;
    public final int scaledTile = tileSize*scale;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int maxMap = 4;
    public int currentMap = 0;
    public final int screenWidth = scaledTile * maxScreenCol;
    public final int screenHeight = scaledTile * maxScreenRow;

    public final int maxWorldCol = 32;
    public final int maxWorldRow = 24;


    Thread gameThread;
    final int FPS=60;
    BufferedImage bf;
    

    SoundManager soundManager  = SoundManager.getInstance();
    public CollisionManager coManager = new CollisionManager(this);
    public AssetManager itemManager = new AssetManager(this);
    TileManager tileManager = new TileManager(this);
    KeyManager keyHandler = new KeyManager(this);
    MouseManager mouseHandler = new MouseManager(this);
    public UI gui = new UI(this);
    public Item items[] = new Item[50];
    public Slime slimeArr[] = new Slime[12];
    public Player Abraham;
    public Projectile stone = new Projectile(this);


    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameoverState = 3;
    public boolean gameComplete = false;

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setFocusable(true);               

    }

    public void setup(){
        Abraham = new Player(this, keyHandler);
        itemManager.setObject();
        itemManager.setSlimes();
        gameState = titleState;
    }
    public void setupLevel(int level){
        currentMap = level;
        Abraham.setMapLocation(currentMap);
        itemManager.setSlimes();
        itemManager.setObject();
        
    }
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            update();
            repaint();
            try {
                double remTime = nextDrawTime - System.nanoTime();
                remTime = remTime/1000000;
                
                if(remTime<0)
                    remTime = 0;

                Thread.sleep((long) remTime);
                nextDrawTime += drawInterval;
                
            } catch (Exception e) {
            }
        }
        
    }
    public void update(){
        if(gameState == playState){
            Abraham.update();
            itemManager.updateMap();
            for(int i=0; i<slimeArr.length; i++){
                if(slimeArr[i] != null){
                    if(slimeArr[i].alive && slimeArr[i].level==currentMap){
                        slimeArr[i].update();
                    }
                    else if(!slimeArr[i].alive){
                        slimeArr[i].checkDrop();
                        slimeArr[i] = null;
                    }
                }
                
            }
            stone.update();
            
        }
        else if(gameState == pauseState){
            
        }
        else if(gameState == gameoverState){
            for(int i=0; i<slimeArr.length; i++){
                if(slimeArr[i] != null){
                    if(slimeArr[i].alive){
                        slimeArr[i].update();
                    }
                    else if(!slimeArr[i].alive){
                        slimeArr[i].checkDrop();
                        slimeArr[i] = null;
                    }
                }
                
            }
        }

        else if(gameState == titleState){
            MainApplication.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            gui.bManager.move();
        }
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == titleState){
            gui.draw(g2);
        }
        else{                
            tileManager.draw(g2);
            for(int i=0; i<items.length; i++){
                if(items[i] != null){
                    items[i].draw(g2, this);
                }
            }
            Abraham.draw(g2);
            for(int i=0; i<slimeArr.length; i++){
                if(slimeArr[i] != null){
                    slimeArr[i].draw(g2);
                }
            }
            stone.draw(g2);
            gui.draw(g2);
        }
        

        g2.dispose();
    }
    public void playBGM(){
        soundManager.playClip("background", true);
    }
    public void playPowerupSFX(){
        soundManager.playClip("powerup", false);
    }
    public void playCollectSFX(){
        soundManager.playClip("coin", false);
    }
    public void playBlipSFX(){
        soundManager.playClip("blip", false);
    }
    public void playPauseSFX(){
        soundManager.playClip("pause", false);
    }
    public void playSlimeDeath(){
        soundManager.playClip("slimeDeath", false);
    }
    public void playTitleSFX() {
        soundManager.playClip("title", false);
    }
    public void playLevelUpSFX() {
        soundManager.playClip("levelUp", false);
    }
    public void playThrowSFX() {
        soundManager.playClip("throw", false);
    }
    public void playHurtSFX(){
        soundManager.playClip("hurt", false);
    }
    
}
