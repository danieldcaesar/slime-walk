package main;

import java.awt.Cursor;

import javax.swing.JFrame;


/**
 * MainApplication
 */
public class MainApplication {
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Slime Walk || 816023385");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setup();
        gamePanel.playBGM();
        gamePanel.startThread();
    }
    
    
}