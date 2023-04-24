package animation;

import java.awt.Graphics2D;

import main.GamePanel;

public class BackgroundManager {

	private String bgImages[] = {"sprites/titlescreen/sky.png",
			       	     "sprites/titlescreen/far-clouds.png",
				     "sprites/titlescreen/near-clouds.png",
					 "sprites/titlescreen/mountains.png",
					 "sprites/titlescreen/trees.png"
				    };


  	private int moveAmount[] = {0, 2, 4, 8, 10};  // applied to moveSize
     		// a move amount of 0 would make a background stationary

  	private Background[] backgrounds;
  	private int numBackgrounds;

  	private GamePanel panel;

  	public BackgroundManager(GamePanel panel) {
    		this.panel = panel;

    		numBackgrounds = bgImages.length;
    		backgrounds = new Background[numBackgrounds];

    		for (int i = 0; i < numBackgrounds; i++) {
       			backgrounds[i] = new Background(panel, bgImages[i], moveAmount[i]);
    		}
  	} 




	public void move () {
		for (int i=0; i<numBackgrounds; i++)
      			backgrounds[i].move();
		System.out.println();
	}


  	// The draw method draws the backgrounds on the screen. The
  	// backgrounds are drawn from the back to the front.

  	public void draw (Graphics2D g2) { 
		for (int i=0; i<numBackgrounds; i++)
      			backgrounds[i].draw(g2);
  	}

}


