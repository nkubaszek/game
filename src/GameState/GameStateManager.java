/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

//stany gry
enum states
{
    menuState,//stan menu
    level1State,//stan poziomu 1
    levelCompleteState,//poziom ukonczony
    levelFailedState//przegrana
};

public class GameStateManager {
    private MenuState menuState;
    private Level1State level1State;
    private LevelCompleteState levelCompleteState;
    private LevelFailedState levelFailedState;

    private GameState currentState;
    private GamePanel gamePanel;

    private int finalScore;//ostateczny wynik

    public GameStateManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        menuState = new MenuState(gamePanel, this);
        level1State = new Level1State(gamePanel,this);
        levelCompleteState = new LevelCompleteState(gamePanel,this);
        levelFailedState = new LevelFailedState(gamePanel, this);

        setState(states.menuState);
    }

    //ustawianie stanu
    public void setState(states state){
        if (state == states.menuState)
            currentState = menuState;
        else if (state == states.level1State)
            currentState = level1State;
        else if (state == states.levelCompleteState)
            currentState = levelCompleteState;
        else if (state == states.levelFailedState)
            currentState = levelFailedState;

        currentState.init();
    }

    public void setFinalScore(int finalScore)
    {
        this.finalScore = finalScore;
    }

    public int getFinalScore()
    {
        return finalScore;
    }

    public void draw()
    {
        currentState.draw();
    }

    public void resetLevel1()
    {
        level1State = new Level1State(gamePanel,this);
        setState(states.level1State);
    }

    public void addComponents()
    {
        currentState.addComponents();
    }

    public boolean updateNeeded()
    {
        return currentState.updateNeeded();
    }

    public Image getCurrentScreen()
    {
        return currentState.getImage();
    }

    public void keyTyped(KeyEvent keyEvent) {
        currentState.keyTyped(keyEvent);
    }

    public void keyPressed(KeyEvent keyEvent) {
        currentState.keyPressed(keyEvent);
    }

    public void keyReleased(KeyEvent keyEvent) {
        currentState.keyReleased(keyEvent);
    }

}
