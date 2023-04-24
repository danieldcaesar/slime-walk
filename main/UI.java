package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import animation.BackgroundManager;
import item.Heart;
import item.Item;

public class UI {
    GamePanel panel;
    Graphics2D g2;
    Font mainFont;
    Image bgImage;
    int commandNum, messageCounter = 0;
    BufferedImage hp0, hp1, hp2, hp3, hp4, hp5, hp6;
    Rectangle textRect;
    public boolean hover = false, messageOn = false, instructionScreen = false;
    public double playTime;
    String messageText = "";
    BackgroundManager bManager; 

    public UI(GamePanel gp){
        panel = gp;
        mainFont = new Font("ThaleahFat", 1, 85);
        bgImage = ImageManager.loadImage("sprites/titlescreen/Mountain-Dusk.png");
        bManager = new BackgroundManager(gp);
        commandNum = 0;

        Item heart = new Heart(gp);
        hp0 = heart.image;
        hp1 = heart.img1;
        hp2 = heart.img2;
        hp3 = heart.img3;
        hp4 = heart.img4;
        hp5 = heart.img5;
        hp6 = heart.img6;

        textRect = new Rectangle();
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(mainFont);
        g2.setColor(Color.white);  

        if(panel.gameState == panel.playState){
            if(messageOn){
                messageCounter++;
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
                g2.drawString(messageText, textCentredX(messageText)+24, panel.scaledTile*5);
                
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
            drawHP();
            drawTime();
            if(panel.gameComplete){
                drawOverlay("GAME COMPLETE");
            }else{
                playTime+=(double)1/60;
            }
            
            
        }
        else if(panel.gameState == panel.pauseState){
            drawHP();
            // drawTime();
            drawOverlay("PAUSED");
        }
        else if(panel.gameState == panel.titleState){
            bManager.draw(g2);
            if(!instructionScreen){
                drawTitleScreen();
            } else {
                // instructionScreen = false;
                drawInstructions();
            }


        }
        else if(panel.gameState == panel.gameoverState){
            drawHP();
            drawTime();
            drawOverlay("GAME OVER");
        }

    }
    
    private void drawInstructions() {
        g2.setColor(new Color(0, 0, 0, 0.25f));
        g2.fill(new Rectangle(0, 0, panel.screenWidth, panel.screenHeight));
        ImageManager.changeAlpha(g2, 1f);


        g2.setFont(mainFont);
        String text = "INSTRUCTIONS";
        int x = textCentredX(text);
        int y = panel.scaledTile*3;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

 
        x = panel.scaledTile*2;
        y = panel.scaledTile*5;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
        g2.drawString("You find yourself at the entrance of a cave, surrounded by Slimes.", x, y);
        g2.drawString("Because you love adventure, you decide to explore!", x, y+24);

        g2.drawString("Use the arrow keys to move around.", x, y+60);
        g2.drawString("Use spacebar to throw stones at slimes.", x, y+80);



        text = "BACK TO TITLE";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        textRect.width = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        textRect.height = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        textRect.x = textCentredX(text);
        textRect.y = (panel.screenHeight/2) + panel.scaledTile*4;
        g2.drawString(text, textRect.x, textRect.y+24);

        if(hover){            
            g2.drawString(">", textRect.x-panel.scaledTile, textRect.y+24);
            g2.drawString("<", textRect.x+panel.scaledTile+textRect.width, textRect.y+24);
        }
    }

    public void drawMessage(String text) {
        messageText = text;
        messageOn = true;
    }

    private void drawTime() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.drawString("Time: "+String.format("%.2f",playTime), panel.scaledTile*16, panel.scaledTile);
    }

    public void drawHP(){
        int x = panel.scaledTile/2;
        int y = panel.scaledTile/2;
        switch (panel.Abraham.hp) {
            case 0:
                g2.drawImage(hp0, x, y, 108, 34, null);
                break;
            case 1:
                g2.drawImage(hp1, x, y, 108, 34, null);
                break;
            case 2:
                g2.drawImage(hp2, x, y, 108, 34, null);
                break;
            case 3:
                g2.drawImage(hp3, x, y, 108, 34, null);
                break;
            case 4:
                g2.drawImage(hp4, x, y, 108, 34, null);
                break;
            case 5:
                g2.drawImage(hp5, x, y, 108, 34, null);
                break;
            case 6:
                g2.drawImage(hp6, x, y, 108, 34, null);
                break;
        
            default:
                g2.drawImage(hp6, x, y, 108, 34, null);
                break;
        }
    }
    public void drawOverlay(String text){
        int x, y;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.drawString("Time: "+String.format("%.2f",playTime), panel.scaledTile*16, panel.scaledTile);


        g2.setColor(new Color(0, 0, 0, 0.4f));
        g2.fill(new Rectangle(0, 0, panel.screenWidth, panel.screenHeight));
        ImageManager.changeAlpha(g2, 1f);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        g2.setColor(Color.white);
        x = textCentredX(text);
        y = panel.screenHeight/2;
        g2.drawString(text, x, y);
        
        if(panel.gameComplete || panel.gameState == panel.gameoverState){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));

            text = "score: "+panel.Abraham.score;
            x = textCentredX(text);
            y += panel.scaledTile*2;
            g2.drawString(text, x, y);

            
            text = "time: "+String.format("%.2f",playTime);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
            g2.drawString(text, textCentredX(text), (panel.screenHeight/2-24)+(panel.scaledTile*3));


        }

        
        text = "BACK TO TITLE";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        textRect.width = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        textRect.height = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        textRect.x = textCentredX(text);
        textRect.y = (panel.screenHeight/2) + panel.scaledTile*4;
        g2.drawString(text, textRect.x, textRect.y+24);

        if(hover){            
            g2.drawString(">", textRect.x-panel.scaledTile, textRect.y+24);
            g2.drawString("<", textRect.x+panel.scaledTile+textRect.width, textRect.y+24);
        }
    }

    public boolean textHover(int mouseX, int mouseY){
        return textRect.contains(mouseX, mouseY);
    }
    public void drawTitleScreen(){
        g2.setFont(mainFont);
        String text = "SLIME WALK";
        int x = textCentredX(text);
        int y = panel.scaledTile*3;

        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = panel.screenWidth/2;
        y += panel.scaledTile*3;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
        text = "START";
        x = textCentredX(text);
        y += panel.scaledTile*3;
        textRect.width = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, x, y);
        if(commandNum ==0){
            g2.drawString(">", x-panel.scaledTile, y);
            g2.drawString("<", x+panel.scaledTile+textRect.width, y);
        }

        text = "INSTRUCTIONS";
        x = textCentredX(text);
        y += panel.scaledTile*0.75;
        textRect.width = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, x, y);
        if(commandNum ==1){
            g2.drawString(">", x-panel.scaledTile, y);
            g2.drawString("<", x+panel.scaledTile+textRect.width, y);
        }

        text = "QUIT";
        x = textCentredX(text);
        y += panel.scaledTile*0.75;
        textRect.width = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, x, y);
        if(commandNum ==2){
            g2.drawString(">", x-panel.scaledTile, y);
            g2.drawString("<", x+panel.scaledTile+textRect.width, y);
        }

    }
    public int textCentredX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return panel.screenWidth/2 - length/2;
    }
}
