package animation;

import main.GamePanel;
import main.ImageManager;

import java.awt.Graphics2D;
import java.awt.Image;

public class Background {
  	private Image bgImage;
  	private int bgImageWidth;      		// width of the background (>= panel Width)

	private GamePanel panel;

 	private int bgX;			// X-coordinate of "actual" position

	private int bg1X;			// X-coordinate of first background
	private int bg2X;			// X-coordinate of second background
	private int bgDX;			// size of the background move (in pixels)


  public Background(GamePanel panel, String imageFile, int bgDX) {
    
	this.panel = panel;
	this.bgImage = ImageManager.loadImage(imageFile);
	bgImageWidth = bgImage.getWidth(null);	// get width of the background

    this.bgDX = bgDX;

	bgX = 0;
	bg1X = 0;
	bg2X = bgImageWidth;

  }


  public void move () {

	bgX = bgX - bgDX;

	bg1X = bg1X - bgDX;
	bg2X = bg2X - bgDX;

	if (bg1X < (bgImageWidth * -1)) {
		bg1X = 0;
		bg2X = bgImageWidth;
	}
  }

 

  public void draw (Graphics2D g2) {
	g2.drawImage(bgImage, bg1X, 0, panel.screenWidth, panel.screenHeight, null);
	g2.drawImage(bgImage, bg2X, 0, panel.screenWidth, panel.screenHeight, null);
  }

}