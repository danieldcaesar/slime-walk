package item;

import main.ImageManager;

public class Emerald extends Gem{
    public Emerald(){
        super();
        value = 30;
        name = "Emerald";
        image = ImageManager.loadBufferedImage("sprites/objects/gem-2.png");
    }
}
