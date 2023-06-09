package item;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;


import main.GamePanel;


public class Item {
    public BufferedImage image;
    public BufferedImage img1, img2, img3, img4, img5, img6;
    public String name; 
    public boolean collision = true;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaX = 0;
    public int solidAreaY = 0;
    public int value = 20;

    public void draw(Graphics2D g2, GamePanel panel){
        int screenX = worldX - panel.Abraham.worldX + panel.Abraham.screenX;
        int screenY = worldY - panel.Abraham.worldY + panel.Abraham.screenY;

        if(worldX + panel.scaledTile > panel.Abraham.worldX - panel.Abraham.screenX && worldX - panel.scaledTile < panel.Abraham.worldX + panel.Abraham.screenX && worldY + panel.scaledTile > panel.Abraham.worldY - panel.Abraham.screenY && worldY  - panel.scaledTile< panel.Abraham.worldY + panel.Abraham.screenY){
            // ensures only items around player within screen radius are drawn
            g2.drawImage(image, screenX, screenY, panel.scaledTile, panel.scaledTile, null);
        }
    }
}
