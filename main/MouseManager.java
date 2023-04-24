package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
    GamePanel panel;

    public MouseManager(GamePanel gp){
        panel = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(panel.gameState == panel.pauseState || panel.gameState == panel.gameoverState || panel.gameComplete){
            if(panel.gui.textHover(e.getX(), e.getY())){
                panel.gui.playTime = 0;
                panel.playPauseSFX();
                panel.gameState = panel.titleState;
                panel.currentMap = 0;
                if(panel.gameComplete){
                    panel.gameComplete = false;
                }
            }
        }
        
        if(panel.gameState == panel.titleState && panel.gui.instructionScreen){
            if(panel.gui.textHover(e.getX(), e.getY())){
                panel.gui.hover = true;
                panel.playPauseSFX();
                panel.gui.instructionScreen = false;
            }
            else{
                panel.gui.hover = false;
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(panel.gameState == panel.pauseState || panel.gameState == panel.gameoverState || panel.gameComplete){
            if(panel.gui.textHover(e.getX(), e.getY())){
                panel.gui.hover = true;
            }
            else{
                panel.gui.hover = false;
            }
        }
        if(panel.gameState == panel.titleState && panel.gui.instructionScreen){
            if(panel.gui.textHover(e.getX(), e.getY())){
                panel.gui.hover = true;
            }
            else{
                panel.gui.hover = false;
            }
        }

    }
    
}
