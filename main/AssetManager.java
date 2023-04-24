package main;

import item.*;
import entity.*;

public class AssetManager {
    GamePanel panel;
    public boolean mapComplete = false;

    public AssetManager(GamePanel gp){
        panel = gp;
    }
    public void setObject(){
        if(panel.currentMap == 0){
            panel.items[0] = new Chest();
            panel.items[0].worldX = 28 * panel.scaledTile;
            panel.items[0].worldY = 17 * panel.scaledTile;
        }
        else if(panel.currentMap == 1){
            panel.items[0] = new Chest();
            panel.items[0].worldX = 28 * panel.scaledTile;
            panel.items[0].worldY = 20 * panel.scaledTile;
        }
        
    }

    public void setSlimes(){
        mapComplete = false;
        if(panel.currentMap == 0){
            panel.slimeArr[0] = new Slime(panel, 6, 10, 0);
            panel.slimeArr[1] = new Slime(panel, 22, 17, 0);
        }
        else if(panel.currentMap == 1){
            panel.slimeArr[2] = new Slime(panel, 9, 13, 1);
            panel.slimeArr[3] = new Slime(panel, 11, 11, 1);
            panel.slimeArr[4] = new Slime(panel, 16, 5, 1);
            panel.slimeArr[5] = new Slime(panel, 20, 3, 1);
            panel.slimeArr[6] = new Slime(panel, 23, 4,1);
            panel.slimeArr[7] = new Slime(panel, 28, 15, 1);
        }
        else if(panel.currentMap == 2){
            panel.slimeArr[8] = new SlimeBoss(panel, 12, 8, 2);
        }
        

        
        
        // panel.slimeArr[9] = new Slime(panel, 15, 4);
        // panel.slimeArr[10] = new Slime(panel, 13, 2);
        // panel.slimeArr[11] = new Slime(panel, 14, 3);
        // panel.slimeArr[12] = new Slime(panel, 29, 18);


    }
    public void updateMap(){
        if(panel.currentMap == 0){
            if(panel.slimeArr[0] == null && panel.slimeArr[1] == null){
                mapComplete = true;
            }
        }
        else if(panel.currentMap == 1){
            if(panel.slimeArr[2] == null && panel.slimeArr[3] == null && panel.slimeArr[4] == null && panel.slimeArr[5] == null && panel.slimeArr[6] == null && panel.slimeArr[7] == null){
                mapComplete = true;
            }

        }
        else if(panel.currentMap == 2){
            if(panel.slimeArr[8] == null){

            }
        }
    }
}
