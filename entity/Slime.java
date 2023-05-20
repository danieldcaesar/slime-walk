package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
import animation.SlimeAnimation;
import main.GamePanel;
import item.*;

public class Slime extends Entity{
    SlimeAnimation slAnimation;
    // public int worldX, worldY;
    public int screenX, screenY;
    public int timer;
    public int type;
    public int level;
    public boolean collidePlayer;

    public Slime(GamePanel gp, int x, int y, int level){
        super(gp);
        panel = gp;
        slAnimation = new SlimeAnimation();
        name = "slime";
        speed = 1;
        maxHP = 3;
        hp = maxHP;
        type = 0;
        this.level = level;

        worldX = (panel.scaledTile * x);
        worldY = (panel.scaledTile * y);

        screenX = worldX - panel.Abraham.worldX + panel.Abraham.screenX;
        screenY = worldY - panel.Abraham.worldY + panel.Abraham.screenY;

        solidArea = new Rectangle(32, 40, 15, 20);
        // solidArea.x = worldX+36;
        // solidArea.y = worldY+36;
        // solidArea.width = 2;
        // solidArea.height = 1;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        // slAnimation.setLocation(worldX, worldY);
        direction = "down";
    }
    public void drawBossHP(Graphics2D g2){}
    public void setLocation(int x, int y){
        worldX = panel.scaledTile * x;
        worldY = panel.scaledTile * y;
        
    }
    public void update(){
        slAnimation.updateAnim();

        if(hp<=0){
            timer++;
            slAnimation.die();
            if(timer>=60){
                panel.playSlimeDeath();
                alive = false;
            }
        }
        else if(hp<maxHP){
            if(panel.coManager.checkPlayer(this)){
                if(panel.Abraham.invincible == false){
                    panel.Abraham.collideSlime(type);
                }
            }
            move();
            
        }
    }
    public void move(){
       
        if(worldX-20 > panel.Abraham.worldX){
            direction = "left";
        }
        else if(worldX-20 < panel.Abraham.worldX){
            direction = "right";
        }
        else if(worldY-20 > panel.Abraham.worldY){
            direction = "up";
        }
        else if(worldY-20 < panel.Abraham.worldY){
            direction = "down";
        }
        collisionOn = false;
        panel.coManager.checkPlayer(this);
        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
                default:                       break;
            }
        }

        
    }
    public void checkDrop(){
        int i = new Random().nextInt(4)+1;

        if(i==1){
            dropItem(new Emerald());
        }
        if(i==2){
            dropItem(new Ruby());
        }
        if(i==3){
            dropItem(new Topaz());
        }
        if(i==4){
            dropItem(new Diamond());
        }
    }
    public void dropItem(Item drop){
        for(int i=0; i<panel.items.length; i++){
            if(panel.items[i] == null){
                panel.items[i] = drop;
                panel.items[i].worldX = worldX;
                panel.items[i].worldY = worldY;
                if(panel.currentMap == 2){
                    panel.items[i].worldX = panel.Abraham.worldX;
                    panel.items[i].worldY = panel.Abraham.worldY-75;
                }
                break;
            }
        }
    }
    public void draw(Graphics2D g2){
        // BufferedImage image = null;
        screenX = worldX - panel.Abraham.worldX + panel.Abraham.screenX;
        screenY = worldY - panel.Abraham.worldY + panel.Abraham.screenY;
        slAnimation.draw(g2, screenX, screenY);

        if(type == 0){
            double hpScale = (double)(panel.scaledTile/maxHP);
            double hpBar = hpScale*hp;

            if(hp<maxHP){
                g2.setColor(new Color(19, 9, 9));
                g2.fillRect(screenX+12, screenY-14, panel.scaledTile+8, 14);
                g2.setColor(new Color(76, 40, 40));
                g2.fillRect(screenX+14, screenY-12, panel.scaledTile+4, 8);
                g2.setColor(new Color(168, 20, 20));
                g2.fillRect(screenX+16, screenY-10, (int)hpBar, 4);
            } else {
                g2.setColor(new Color(19, 9, 9));
                g2.fillRect(screenX+12, screenY-14, panel.scaledTile+8, 14);
                g2.setColor(new Color(49, 103, 42));
                g2.fillRect(screenX+14, screenY-12, panel.scaledTile+4, 8);
                g2.setColor(new Color(101, 167, 56));
                g2.fillRect(screenX+16, screenY-10, (int)hpBar, 4);
            }
            
        }
    }
    
}
