package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import item.ChestOpen;
import main.GamePanel;

public class SlimeBoss extends Slime{
    int scale = 4;
    int actionCounter = 0;
    int moveInterval = 120;
    
    public SlimeBoss(GamePanel gp, int x, int y, int level){        
        super(gp, x, y, level);
        slAnimation.size *= scale;
        type = 1;
        name = "slime boss";
        maxHP *= scale;
        hp = maxHP;
        solidArea = new Rectangle();
        solidArea.x = 80;
        solidArea.y = 112;
        solidArea.width = 124;
        solidArea.height = 144;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
    }
    public void checkDrop(){
        dropItem(new ChestOpen());
    }

    public void draw(Graphics2D g2){
        screenX = worldX - panel.Abraham.worldX + panel.Abraham.screenX;
        screenY = worldY - panel.Abraham.worldY + panel.Abraham.screenY;
        slAnimation.draw(g2, screenX, screenY);

        double hpScale = (double)(panel.scaledTile*8/maxHP);
        double hpBar = hpScale*hp;

        int x = panel.screenWidth/2 - panel.scaledTile*4;
        int y = panel.scaledTile*11;

        g2.setFont(new Font("ThaleahFat", 1, 30));
        g2.setColor(Color.white);
        g2.drawString(name, x+4, y-10);

        if(hp>((int)maxHP*0.5)){
            g2.setColor(new Color(19, 9, 9));
            g2.fillRect(x-5, y-5, panel.scaledTile*8+10, 30);
            g2.setColor(new Color(49, 103, 42));
            g2.fillRect(x-2, y-2, panel.scaledTile*8+4, 24);
            g2.setColor(new Color(101, 167, 56));
            g2.fillRect(x, y, (int)hpBar, 20);
        } else {
            g2.setColor(new Color(19, 9, 9));
            g2.fillRect(x-5, y-5, panel.scaledTile*8+10, 30);
            g2.setColor(new Color(76, 40, 40));
            g2.fillRect(x-2, y-2, panel.scaledTile*8+4, 24);
            g2.setColor(new Color(168, 20, 20));
            g2.fillRect(x, y, (int)hpBar, 20);
        }
            
    }
    public void move(){
        actionCounter++;
        Random random = new Random();
        int i = random.nextInt(100)+1;

        if (actionCounter >= moveInterval) {
            if(hp>((int)maxHP*0.5)){
                
                if(i <= 25){
                    direction = "up";
                }
                if(i>25 && i <=50){
                    direction = "down";
                }
                if(i>50 && i <=75){
                    direction = "left";
                }
                if(i>75 && i <=100){
                    direction = "right";
                }
            }
            else{
                moveInterval = 60;
                speed = 2;
                if(i <= 25){
                    direction = "up";
                }
                if(i>25 && i <=50){
                    direction = "down";
                }
                if(i>50 && i <=75){
                    direction = "left";
                }
                if(i>75 && i <=100){
                    direction = "right";
                }
                
            }
            actionCounter = 0;
        }
        collisionOn = false;
            panel.coManager.checkTile(this);
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
    public void chase(){
       
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
        // panel.coManager.checkTile(this);
        // panel.coManager.checkEntity(this);
        // panel.coManager.checkObject(this, false);
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
}
