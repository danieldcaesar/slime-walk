package item;

import main.ImageManager;

public class Topaz extends Gem{
    public Topaz(){
        super();
        name = "Topaz";
        image = ImageManager.loadBufferedImage("sprites/objects/gem-1.png");
    }
}
