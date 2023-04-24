package item;

import main.ImageManager;

public class Ruby extends Gem{
    public Ruby(){
        super();
        value = 50;
        name = "Ruby";
        image = ImageManager.loadBufferedImage("sprites/objects/gem-3.png");
    }
    
}
