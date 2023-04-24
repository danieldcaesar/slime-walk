package item;

import main.ImageManager;

public class Diamond extends Gem{
    public Diamond(){
        super();
        name = "Diamond";
        image = ImageManager.loadBufferedImage("sprites/objects/gem-4.png");
    }
}
