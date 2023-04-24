package main;
import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import entity.Slime;

public class CollisionManager {
    GamePanel panel;
    


    public CollisionManager(GamePanel gp){
        panel = gp;
    }
    public void checkTile(Entity entity){
        // if(entity instanceof Slime){
        //     System.out.println(entity.name);
        // }
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/panel.scaledTile;
        int entityRightCol = entityRightWorldX/panel.scaledTile;
        int entityTopRow = entityTopWorldY/panel.scaledTile;
        int entityBottomRow = entityBottomWorldY/panel.scaledTile;

        int tileNum1, tileNum2;
        
        switch (entity.direction) {
            case "up": 
                entityTopRow = (entityTopWorldY - entity.speed)/panel.scaledTile;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down": 
                entityBottomRow = (entityBottomWorldY + entity.speed)/panel.scaledTile;
                
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left": 
                entityLeftCol = (entityLeftWorldX - entity.speed)/panel.scaledTile;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "right": 
                entityRightCol = (entityRightWorldX + entity.speed)/panel.scaledTile;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
                if(panel.tileManager.tile[tileNum1].collision == true || panel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        
            default:
                break;
        }
    }
    
    public int checkObject(Entity ent, boolean player){
        int index = 99;

        for(int i=0; i<panel.items.length; i++){
            if(panel.items[i] != null){
                ent.solidArea.x += ent.worldX;
                ent.solidArea.y += ent.worldY;

                panel.items[i].solidArea.x += panel.items[i].worldX;
                panel.items[i].solidArea.y += panel.items[i].worldY;

                switch(ent.direction){
                    case "up":{
                        ent.solidArea.y -= ent.speed;
                        if(ent.solidArea.intersects(panel.items[i].solidArea)){
                            if(panel.items[i].collision == true){
                                ent.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                    }
                        
                    case "down":{
                        ent.solidArea.y += ent.speed;
                        if(ent.solidArea.intersects(panel.items[i].solidArea)){
                            if(panel.items[i].collision == true){
                                ent.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    }
                        
                    case "left":{
                        ent.solidArea.x -= ent.speed;
                        if(ent.solidArea.intersects(panel.items[i].solidArea)){
                            if(panel.items[i].collision == true){
                                ent.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    }
                        
                    case "right":{
                        ent.solidArea.x += ent.speed;
                        if(ent.solidArea.intersects(panel.items[i].solidArea)){
                            if(panel.items[i].collision == true){
                                ent.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    }
                        
                    default: break;
                    
                }
                ent.solidArea.x = ent.solidAreaX;
                ent.solidArea.y = ent.solidAreaY;
                panel.items[i].solidArea.x = panel.items[i].solidAreaX;
                panel.items[i].solidArea.y = panel.items[i].solidAreaY;
            }
        }

        return index;
    }
    public boolean checkPlayer(Entity ent){
        boolean collidePlayer = false;

        ent.solidArea.x += ent.worldX;
        ent.solidArea.y += ent.worldY;

        panel.Abraham.solidArea.x += panel.Abraham.worldX;
        panel.Abraham.solidArea.y += panel.Abraham.worldY;

        switch(ent.direction){
            case "up":{
                ent.solidArea.y -= ent.speed;
                break;
            }
                
            case "down":{
                ent.solidArea.y += ent.speed;
                break;
            }
                
            case "left":{
                ent.solidArea.x -= ent.speed;
                break;
            }
                
            case "right":{
                ent.solidArea.x += ent.speed;
                break;
            }
                
            default: break;
            
        }

        if(ent.solidArea.intersects(panel.Abraham.solidArea)){
            collidePlayer = true;
        }

        ent.solidArea.x = ent.solidAreaX;
        ent.solidArea.y = ent.solidAreaY;
        panel.Abraham.solidArea.x = panel.Abraham.solidAreaX;
        panel.Abraham.solidArea.y = panel.Abraham.solidAreaY;

        return collidePlayer;

    }
    public int checkEntity(Entity ent, Entity target[]){
        int index = 99;

        for(int i=0; i<target.length; i++){
            if(target[i] != null){
                ent.solidArea.x += ent.worldX;
                ent.solidArea.y += ent.worldY;

                target[i].solidArea.x += target[i].worldX;
                target[i].solidArea.y += target[i].worldY;

                switch(ent.direction){
                    case "up":{
                        ent.solidArea.y -= ent.speed;
                        break;
                    }
                        
                    case "down":{
                        ent.solidArea.y += ent.speed;
                        break;
                    }
                        
                    case "left":{
                        ent.solidArea.x -= ent.speed;
                        break;
                    }
                        
                    case "right":{
                        ent.solidArea.x += ent.speed;
                        break;
                    }
                        
                    default: break;
                    
                }

                if(ent.solidArea.intersects(target[i].solidArea)){
                    index = i;
                }

                ent.solidArea.x = ent.solidAreaX;
                ent.solidArea.y = ent.solidAreaY;
                target[i].solidArea.x = target[i].solidAreaX;
                target[i].solidArea.y = target[i].solidAreaY;
            }
        }

        return index;
    } // end checkEntity
}
