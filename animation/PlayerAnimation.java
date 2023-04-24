package animation;

import java.awt.Image;

import main.ImageManager;
import java.awt.Graphics2D;

public class PlayerAnimation {
    Animation walkSouth;
    Animation walkNorth;
    Animation walkEast;
    Animation walkWest;
    Animation atkSouth;
    Animation atkNorth;
    Animation atkEast;
    Animation atkWest;
    Animation plyrDeathEast;
    Animation plyrDeathWest;
    Animation activAnim;
    
    private int x;
    private int y;
    Image animImages[];

    private int size=96;


    public PlayerAnimation(){
        atkSouth = new Animation();
        atkEast = new Animation();
        atkNorth = new Animation();
        atkWest = new Animation();
        walkSouth = new Animation();
        walkEast = new Animation();
        walkNorth = new Animation();
        walkWest = new Animation();
        plyrDeathEast = new Animation();
        plyrDeathWest = new Animation();

        animImages = new Image[60];

        // atkSouth sprites
        animImages[0] = ImageManager.loadImage("sprites/characters/player/tile036.png");
        animImages[1] = ImageManager.loadImage("sprites/characters/player/tile037.png");
        animImages[2] = ImageManager.loadImage("sprites/characters/player/tile037.png");
        animImages[3] = ImageManager.loadImage("sprites/characters/player/tile038.png");
        animImages[4] = ImageManager.loadImage("sprites/characters/player/tile039.png");

        // atkEast sprites
        animImages[5] = ImageManager.loadImage("sprites/characters/player/tile042.png");
        animImages[6] = ImageManager.loadImage("sprites/characters/player/tile043.png");
        animImages[7] = ImageManager.loadImage("sprites/characters/player/tile043.png");
        animImages[8] = ImageManager.loadImage("sprites/characters/player/tile044.png");
        animImages[9] = ImageManager.loadImage("sprites/characters/player/tile045.png");

        // atkNorth sprites
        animImages[10] = ImageManager.loadImage("sprites/characters/player/tile048.png");
        animImages[11] = ImageManager.loadImage("sprites/characters/player/tile049.png");
        animImages[12] = ImageManager.loadImage("sprites/characters/player/tile049.png");
        animImages[13] = ImageManager.loadImage("sprites/characters/player/tile050.png");
        animImages[14] = ImageManager.loadImage("sprites/characters/player/tile051.png");

        // atkWest sprites
        animImages[43] = ImageManager.loadImage("sprites/characters/player/tile006.png");
        animImages[44] = ImageManager.loadImage("sprites/characters/player/tile007.png");
        animImages[45] = ImageManager.loadImage("sprites/characters/player/tile007.png");
        animImages[46] = ImageManager.loadImage("sprites/characters/player/tile008.png");
        animImages[47] = ImageManager.loadImage("sprites/characters/player/tile009.png");


        // plyrDeathEast sprites
        animImages[15] = ImageManager.loadImage("sprites/characters/player/tile054.png");
        animImages[16] = ImageManager.loadImage("sprites/characters/player/tile054.png");
        animImages[17] = ImageManager.loadImage("sprites/characters/player/tile055.png");
        animImages[18] = ImageManager.loadImage("sprites/characters/player/tile056.png");

        // walkSouth sprites
        animImages[19] = ImageManager.loadImage("sprites/characters/player/tile018.png");
        animImages[20] = ImageManager.loadImage("sprites/characters/player/tile019.png");
        animImages[21] = ImageManager.loadImage("sprites/characters/player/tile020.png");
        animImages[22] = ImageManager.loadImage("sprites/characters/player/tile021.png");
        animImages[23] = ImageManager.loadImage("sprites/characters/player/tile022.png");
        animImages[24] = ImageManager.loadImage("sprites/characters/player/tile023.png");

        // walkEast sprites
        animImages[25] = ImageManager.loadImage("sprites/characters/player/tile024.png");
        animImages[26] = ImageManager.loadImage("sprites/characters/player/tile025.png");
        animImages[27] = ImageManager.loadImage("sprites/characters/player/tile026.png");
        animImages[28] = ImageManager.loadImage("sprites/characters/player/tile027.png");
        animImages[29] = ImageManager.loadImage("sprites/characters/player/tile028.png");
        animImages[30] = ImageManager.loadImage("sprites/characters/player/tile029.png");

        // walkNorth sprites
        animImages[31] = ImageManager.loadImage("sprites/characters/player/tile030.png");
        animImages[32] = ImageManager.loadImage("sprites/characters/player/tile031.png");
        animImages[33] = ImageManager.loadImage("sprites/characters/player/tile032.png");
        animImages[34] = ImageManager.loadImage("sprites/characters/player/tile033.png");
        animImages[35] = ImageManager.loadImage("sprites/characters/player/tile034.png");
        animImages[36] = ImageManager.loadImage("sprites/characters/player/tile035.png");

        // walkWest sprites
        animImages[37] = ImageManager.loadImage("sprites/characters/player/tile000.png");
        animImages[38] = ImageManager.loadImage("sprites/characters/player/tile001.png");
        animImages[39] = ImageManager.loadImage("sprites/characters/player/tile002.png");
        animImages[40] = ImageManager.loadImage("sprites/characters/player/tile003.png");
        animImages[41] = ImageManager.loadImage("sprites/characters/player/tile004.png");
        animImages[42] = ImageManager.loadImage("sprites/characters/player/tile005.png");

        // plyrDeathWest sprites
        animImages[43] = ImageManager.loadImage("sprites/characters/player/tile057.png");
        animImages[44] = ImageManager.loadImage("sprites/characters/player/tile057.png");
        animImages[45] = ImageManager.loadImage("sprites/characters/player/tile058.png");
        animImages[46] = ImageManager.loadImage("sprites/characters/player/tile059.png");


        for(int i=0; i < 5; i++){
            atkSouth.addFrame(animImages[i], 170);
        }
        for(int i=5; i<10; i++){
            atkEast.addFrame(animImages[i], 170);
        }
        for(int i=10; i<15; i++){
            atkNorth.addFrame(animImages[i], 170);
        }
        for(int i=43; i<48; i++){
            atkWest.addFrame(animImages[i], 120);
        }

        for(int i=15; i<19; i++){
            plyrDeathEast.addFrame(animImages[i], 250);
        }
        for(int i=43; i<47; i++){
            plyrDeathWest.addFrame(animImages[i], 250);
        }

        for(int i=19; i<25; i++){
            walkSouth.addFrame(animImages[i], 120);
        }
        for(int i=25; i<31; i++){
            walkEast.addFrame(animImages[i], 120);
        }
        for(int i=31; i<37; i++){
            walkNorth.addFrame(animImages[i], 120);
        }
        for(int i=37; i<43; i++){
            walkWest.addFrame(animImages[i], 120);
        }


        activAnim = walkSouth;
        // activAnim.active = true;
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

    public void draw(Graphics2D g2){
        g2.drawImage(activAnim.getImage(), x, y, size, size, null);
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String walkNorth(){
        activAnim = walkNorth;
        activAnim.play();
        return "up";
    }
    public String walkSouth(){
        activAnim = walkSouth;
        activAnim.play();
        return "down";
    }
    public String walkWest(){
        activAnim = walkWest;
        activAnim.play();
        return "left";
    }
    public String walkEast(){
        activAnim = walkEast;
        activAnim.play();
        return "right";
    }

    public String atkNorth(){
        activAnim = atkNorth;
        activAnim.play();
        return "up";
    }

    public String die(){
        activAnim = plyrDeathEast;
        activAnim.play();
        return "";
    }
    public boolean isActive(){
        return activAnim.isActive();
    }
}
