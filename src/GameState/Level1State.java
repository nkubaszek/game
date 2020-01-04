/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Main.GamePanel;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class Level1State extends GameState{

    private Image bg;//tlo menu

    private final Set<Integer> pressed = new HashSet<Integer>();

    private Map map;
//konstruktor klasy Level1State
    public Level1State(GamePanel gamePanel, GameStateManager gameStateManager)
    {
        super(gamePanel, gameStateManager);

        map = new Map(gamePanel.getWidth(), gamePanel.getHeight());
        File muzyka = new File("/Resources/music.mp3");
        PlaySound(muzyka);
//wczytanie tla poziomu 1
        try
        {
            bg = ImageIO.read(new File("src/Resources/Backgrounds/level1background.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    private void drawBackground()
    {
        image.getGraphics().drawImage(bg, 0,0, null);

    }
    @Override
    public void init()
    {
    }

    @Override
    public void draw()
    {
        drawBackground();

        switch (map.checkPlayerPositionOnMap())
        {
            case fail://przegrana
                gameStateManager.resetLevel1();
                gameStateManager.setState(states.levelFailedState);
                break;

            case endLevel://koniec poziomu
                gameStateManager.resetLevel1();
                gameStateManager.setFinalScore(map.getFinalScore());
                gameStateManager.setState(states.levelCompleteState);
                break;

            case inAir://gracz w powietrzu
            case onGround://gracz na ziemi
                map.draw((Graphics2D) image.getGraphics(), pressed);
                break;
        }
    }
  private void PlaySound(File muzyka) {
         try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(muzyka));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e)
        {

    }
}
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        pressed.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        {
            pressed.remove(keyEvent.getKeyCode());
        }

    }

 public void resetLevel1()
    {
        gameStateManager.resetLevel1();
    }


}
