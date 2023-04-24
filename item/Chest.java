package item;

import main.ImageManager;

public class Chest extends Item{
    public Chest(){
        name = "Chest";
        image = ImageManager.loadBufferedImage("sprites/objects/chest-1.png");
        collision = true;
    }
}
