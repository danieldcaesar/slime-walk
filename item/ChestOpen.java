package item;

import main.ImageManager;

public class ChestOpen extends Item{
    public ChestOpen(){
        name = "Chest";
        image = ImageManager.loadBufferedImage("sprites/objects/chest-4.png");
        collision = true;
        value = 100;
    }
}
