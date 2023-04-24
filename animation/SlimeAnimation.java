package animation;

import java.awt.Image;
import java.awt.Graphics2D;
import main.ImageManager;

public class SlimeAnimation {
    Animation move;
    Animation death;
    Animation activAnim;

    private int x;
    private int y;
    Image animImages[];

    public int size=80;

    public SlimeAnimation(){
        move = new Animation();
        death = new Animation();

        animImages = new Image[35];
        
        // move sprites
        animImages[0] = ImageManager.loadImage("sprites/characters/slime/tile000.png");
        animImages[1] = ImageManager.loadImage("sprites/characters/slime/tile001.png");
        animImages[2] = ImageManager.loadImage("sprites/characters/slime/tile002.png");
        animImages[3] = ImageManager.loadImage("sprites/characters/slime/tile003.png");
        animImages[4] = ImageManager.loadImage("sprites/characters/slime/tile007.png");
        animImages[5] = ImageManager.loadImage("sprites/characters/slime/tile008.png");
        animImages[6] = ImageManager.loadImage("sprites/characters/slime/tile009.png");
        animImages[7] = ImageManager.loadImage("sprites/characters/slime/tile010.png");
        animImages[8] = ImageManager.loadImage("sprites/characters/slime/tile011.png");
        animImages[9] = ImageManager.loadImage("sprites/characters/slime/tile012.png");
        animImages[10] = ImageManager.loadImage("sprites/characters/slime/tile014.png");
        
        // death sprites
        animImages[11] = ImageManager.loadImage("sprites/characters/slime/tile028.png");
        animImages[12] = ImageManager.loadImage("sprites/characters/slime/tile029.png");
        animImages[13] = ImageManager.loadImage("sprites/characters/slime/tile030.png");
        animImages[14] = ImageManager.loadImage("sprites/characters/slime/tile031.png");
        animImages[15] = ImageManager.loadImage("sprites/characters/slime/tile032.png");
        animImages[16] = ImageManager.loadImage("sprites/characters/slime/tile033.png");

        for(int i=0; i<=10; i++){
            move.addFrame(animImages[i], 150);
        }
        for(int i=11; i<=16; i++){
            death.addFrame(animImages[i], 180);
        }

        activAnim = move;
        activAnim.active = true;
    }
    public void start(){
        activAnim.start();
    }
    public void updateAnim(){
        activAnim.update();
    }
    public void play(){
        activAnim.play();
    }
    public void pause(){
		activAnim.pause();
	}

    public void draw(Graphics2D g2, int x, int y){
        g2.drawImage(activAnim.getImage(), x, y, size, size, null);
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void die(){
        activAnim = death;
        activAnim.play();
    }
    public void move(){
        activAnim = move;
        activAnim.play();
    }
    public boolean isActive(){
        return activAnim.isActive();
    }
}
