
package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//definicja abstrakcyjnej klasy 
public abstract class GameState {
    public GameState(GamePanel gamePanel, GameStateManager gameStateManager)//konstruktor klasy GameState
    {
        this.gamePanel = gamePanel;
        this.gameStateManager = gameStateManager;

        image = new BufferedImage(
                gamePanel.getWidth(),
                gamePanel.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
    }

    protected GamePanel gamePanel;
    protected BufferedImage image;
    protected boolean updateNeeded = true;

    public GameStateManager gameStateManager;
    public abstract void init();
   
    public abstract void draw();


    public void keyTyped(KeyEvent keyEvent) {
        //nothing to do here
    }

    public void keyPressed(KeyEvent keyEvent) {
        //nothing to do here
    }

    public void keyReleased(KeyEvent keyEvent) {
        //nothing to do here
    }

    public BufferedImage getImage() {
        return image;
    }

    public void addComponents()
    {
        //nothing to do here
    }

    public boolean updateNeeded()
    {
        return updateNeeded;
    }
}
