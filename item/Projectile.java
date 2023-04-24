package item;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.ImageManager;

public class Projectile extends Entity{

    BufferedImage activImage;

    public Projectile(GamePanel gp) {
        super(gp);
        panel = gp;
        maxHP = 30;
        hp = maxHP;
        speed = 8;
        attack = 1;
        alive = false;
        getImage();

        solidArea = new Rectangle(0, 0, 32, 32);
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

    }

    public void getImage(){
        up1 = ImageManager.loadBufferedImage("sprites/objects/rock.png");
        down1 = ImageManager.loadBufferedImage("sprites/objects/rock.png");
        left1 = ImageManager.loadBufferedImage("sprites/objects/rock.png");
        right1 = ImageManager.loadBufferedImage("sprites/objects/rock.png");
    }
    
    public void set(int x, int y, String direction, boolean alive){
        worldX = x + 25;
        worldY = y + 52;
        this.direction = direction;
        this.alive = alive;
        this.hp = this.maxHP;
        panel.playThrowSFX();
    }

    public void update(){
        if(alive){
            
            switch (direction) {
                case "up":
                    worldY -= speed;
                    activImage = up1;
                    break;
                case "down":
                    worldY += speed;
                    activImage = down1;
                    break;
                case "left":
                    worldX -= speed;
                    activImage = left1;
                    break;
                case "right":
                    worldX += speed;
                    activImage = right1;
                    break;
                default:                       break;
            }

            int slimeIndex = panel.coManager.checkEntity(this, panel.slimeArr);
            if(slimeIndex != 99){
                panel.Abraham.damageSlime(slimeIndex, attack);
                alive = false;
            }
            hp--;
            if(hp<=0){
                alive = false;
            }
        }
        else activImage = null;
        
        
    }
    public void draw(Graphics2D g2){
        int screenX = worldX - panel.Abraham.worldX + panel.Abraham.screenX;
        int screenY = worldY - panel.Abraham.worldY + panel.Abraham.screenY;
        
        g2.drawImage(activImage, screenX, screenY, 32, 32, null);
    }
}
