package item;

import main.GamePanel;
import main.ImageManager;



public class Heart extends Item{
    GamePanel panel;
    

    public Heart(GamePanel gp){
        panel = gp;

        name = "heart";
        image = ImageManager.loadBufferedImage("sprites/objects/heart/0.png");
        img1 = ImageManager.loadBufferedImage("sprites/objects/heart/1.png");
        img2 = ImageManager.loadBufferedImage("sprites/objects/heart/2.png");
        img3 = ImageManager.loadBufferedImage("sprites/objects/heart/3.png");
        img4 = ImageManager.loadBufferedImage("sprites/objects/heart/4.png");
        img5 = ImageManager.loadBufferedImage("sprites/objects/heart/5.png");
        img6 = ImageManager.loadBufferedImage("sprites/objects/heart/6.png");

        
    }
}
