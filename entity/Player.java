package entity;

import animation.PlayerAnimation;
import main.GamePanel;
import main.ImageManager;
import main.KeyManager;
import java.awt.Graphics2D;
import java.awt.Rectangle;




public class Player extends Entity {
    KeyManager keyHandler;
    PlayerAnimation plyrAnim;
    public int score=0;

    public final int screenX;
    public final int screenY;

    public int stoneCounter;
    public boolean interact;
    public double scoreTime;

    public Player(GamePanel gp, KeyManager km){
        super(gp);
        panel = gp;
        name = "player";
        keyHandler = km;
        plyrAnim = new PlayerAnimation();

        screenX = panel.screenWidth/2 - (panel.scaledTile/2);
        screenY = panel.screenHeight/2 - (panel.scaledTile/2);

        solidArea = new Rectangle(24, 32, 64, 64);
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        setDefaultValues();

        plyrAnim.start();
        plyrAnim.setLocation(screenX, screenY);
    }
    public void setDefaultValues(){
        setMapLocation(0);
        speed = 5;
        direction = "down";
        maxHP = 6;
        hp = maxHP;
    }
    public void setMapLocation(int level){
        if(panel.currentMap == 0){
            worldX = panel.scaledTile * 15;
            worldY = panel.scaledTile * 2;
        }
        else if(panel.currentMap == 1){
            worldX = panel.scaledTile * 2;
            worldY = panel.scaledTile * 16;
        }
        else if(panel.currentMap == 2){
            worldX = panel.scaledTile * 16;
            worldY = panel.scaledTile * 5;
        }
    }
    public void update(){
        plyrAnim.updateAnim();
        if(hp<=0){
            panel.gameState = panel.gameoverState;
        }
        if(keyHandler.isAttack() && panel.stone.alive == false && stoneCounter == 30){
            panel.stone.set(worldX, worldY, direction, true);
            stoneCounter = 0;
        }
        if(stoneCounter < 30){
            stoneCounter++;
        }
        if(keyHandler.isWalk()){
            if (keyHandler.walkNorth == true) {
                direction = "up";
                plyrAnim.walkNorth();
            }
            else if (keyHandler.walkSouth == true) {
                direction = "down";
                plyrAnim.walkSouth();
            }
            else if (keyHandler.walkWest == true) {
                direction = "left";
                plyrAnim.walkWest();
            }
            else if (keyHandler.walkEast == true) {
                direction = "right";
                plyrAnim.walkEast();
            }
            


            collisionOn = false;
            panel.coManager.checkTile(this);
            int itemIndex = panel.coManager.checkObject(this, true);
            collectItem(itemIndex);
            int slimeIndex = panel.coManager.checkEntity(this, panel.slimeArr);
                collideSlime(slimeIndex);
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
        else plyrAnim.pause();

        if (invincible){
            invinCounter++;
            if(invinCounter > 60){
                invincible = false;
                invinCounter = 0;
            }
        }
                
    }

    public void collideSlime(int index){
        if(index != 99){
            if(invincible == false){
                hp--;
                panel.playHurtSFX();
                invincible = true;
            }
        }
        
    }

    public void damageSlime(int index, int attack){
        if(index != 99){
            panel.slimeArr[index].hp -= attack;
        }
    }

    public void collectItem(int index){
        if(index != 99){
            String itemName = panel.items[index].name;
            switch (itemName) {
                case "Emerald":
                    panel.gui.drawMessage("+"+panel.items[index].value);
                    score += panel.items[index].value;
                    panel.items[index] = null;
                    panel.playCollectSFX();
                    break;
                case "Ruby":
                if(hp >= maxHP){
                    panel.gui.drawMessage("+"+panel.items[index].value);
                    score += panel.items[index].value;
                    panel.playCollectSFX();
                    panel.items[index] = null;
                    } else{
                        panel.gui.drawMessage("+2HP");
                        hp += 2;
                        panel.playPowerupSFX();
                        panel.items[index] = null;
                    }
                    
                    
                    break;
                case "Topaz":
                    panel.gui.drawMessage("+"+panel.items[index].value);
                    score += panel.items[index].value;
                    panel.items[index] = null;
                    panel.playCollectSFX();
                    break;
                case "Diamond":
                    panel.gui.drawMessage("+"+panel.items[index].value);
                    score += panel.items[index].value;
                    panel.items[index] = null;
                    panel.playCollectSFX();
                    break;
                case "Chest":
                    if(panel.currentMap == 2){
                        scoreTime = panel.gui.playTime;
                        panel.gui.drawMessage("+"+panel.items[index].value);
                        panel.playLevelUpSFX();
                        score += panel.items[index].value;
                        panel.items[index] = null;
                        panel.gameComplete = true;
                    } else if(panel.itemManager.mapComplete){
                        panel.playLevelUpSFX();
                        panel.items[index] = null;
                        panel.setupLevel(panel.currentMap+1);
                    } else {
                        invincible = true;
                    }

                    break;
                default:
                    break;
            }
            
        }
    }

    public void draw(Graphics2D g2){

        if(invincible){
            ImageManager.changeAlpha(g2, 0.3f);
        }
        plyrAnim.draw(g2);
        ImageManager.changeAlpha(g2, 1f);
    }
}
