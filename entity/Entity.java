package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public class Entity{
    public int worldX, worldY, speed, attack;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction, name;

    public int spriteNum=1, spriteCounter=10;

    public Rectangle solidArea;
    public int solidAreaX, solidAreaY;
    public boolean collisionOn = false, alive = true, invincible = false;

    public int maxHP, hp, invinCounter;

    public GamePanel panel;

    public Entity(GamePanel gp){
        panel = gp;
    }
}
