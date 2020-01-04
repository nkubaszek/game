/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import GameState.GameStateManager;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    // zdefiniowanie rozmiarow okna
    public static final int WIDTH=640;
    public static final int HEIGHT=480;

    //game thread
    private Thread thread;
    private boolean running;
    private int FPS=60;//frames per second
    private long targetTime=1000/FPS;

    //game state manager
    private GameStateManager gsm;

    private Image image;

    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(WIDTH , HEIGHT ));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

    }

    @Override
    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread= new Thread(this);
            thread.start();

        }

    }


    @Override
    public void run(){
        gsm = new GameStateManager(this);
        running = true;

        long start;
        long elapsed;
        long wait;

        // game loop
        while(running)
        {
            start=System.nanoTime();//start gry

            if (gsm.updateNeeded())
            {
                gsm.draw();
                image = gsm.getCurrentScreen();
                gsm.addComponents();
                getGraphics().drawImage(gsm.getCurrentScreen(), 0, 0, null);
            }

            revalidate();
            elapsed=System.nanoTime()-start;
            wait=targetTime-elapsed/1000000;
            if(wait < 0) wait = 5;
            try{
                Thread.sleep(wait);

            }
            catch(Exception e){
                e.printStackTrace();

            }
        }
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
        gsm.keyTyped(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gsm.keyPressed(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gsm.keyReleased(keyEvent);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
    }
}
        