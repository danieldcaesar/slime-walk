package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean walkNorth, walkSouth, walkEast, walkWest, atk;
    GamePanel panel;

    public KeyManager(GamePanel gp){
        panel = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyChar();
        if(keyCode == 27){
            if(panel.gameState == panel.playState){
                panel.gameState = panel.pauseState;
                panel.playPauseSFX();
            }
            else if(panel.gameState == panel.pauseState){
                panel.gameState = panel.playState;
            }
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(panel.gameState == panel.titleState){
                if(keyCode == KeyEvent.VK_UP){
                    panel.playBlipSFX();
                    panel.gui.commandNum--;
                    if(panel.gui.commandNum<0){
                        panel.gui.commandNum = 2;
                    }
                        
                }
                if(keyCode == KeyEvent.VK_DOWN){
                    panel.playBlipSFX();
                    panel.gui.commandNum++;
                    if(panel.gui.commandNum>2){
                        panel.gui.commandNum = 0;
                    }
                }
                if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER && !panel.gui.instructionScreen){
                    panel.playPauseSFX();
                    if(panel.gui.commandNum == 0){
                        panel.setup();
                        panel.gameState = panel.playState;
                    }
                    if(panel.gui.commandNum == 1){
                        panel.gui.instructionScreen = true;
                    }
                    if(panel.gui.commandNum == 2){
                        System.exit(0);
                    }
                }
            
        }
        else if(panel.gameState == panel.playState){
            if (keyCode == KeyEvent.VK_UP){
                walkNorth = true;
            }
            if (keyCode == KeyEvent.VK_DOWN){
                walkSouth = true;
            }
            if (keyCode == KeyEvent.VK_LEFT){
                walkWest = true;
            }
            if (keyCode == KeyEvent.VK_RIGHT){
                walkEast = true;
            }
            if (keyCode == KeyEvent.VK_SPACE){
                atk = true;
            }
            // if (keyCode == KeyEvent.VK_Z && panel.itemManager.mapComplete && panel.Abraham.interact){
            //     panel.setupLevel();
            // }
        }
        

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP){
            walkNorth = false;
        }            
        if (keyCode == KeyEvent.VK_DOWN){
            walkSouth = false;
        }
            
        if (keyCode == KeyEvent.VK_LEFT){
            walkWest = false;
        }
            
        if (keyCode == KeyEvent.VK_RIGHT){
            walkEast = false;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            atk = false;
        }
            
    }

    public boolean isWalk(){
        return walkNorth == true || walkEast == true || walkSouth == true || walkWest == true;
    }
    public boolean isAttack(){
        return atk;
    }

    
}
