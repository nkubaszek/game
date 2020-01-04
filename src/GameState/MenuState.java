/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuState extends GameState implements ActionListener {

    private JLabel jlabel;
    private Image bg;//tlo menu

    //deklaracja przyciskow
    private JButton startButton;
    private JButton howToPlayButton;
    private JButton exitButton;

    private JButton scoreButton;

    public MenuState(GamePanel gamePanel, GameStateManager gameStateManager)
    {
        super(gamePanel, gameStateManager);
// implementacja buttonow
        startButton = new JButton();
        howToPlayButton = new JButton();
        exitButton = new JButton();
        scoreButton=new JButton();

        try {

//wczytanie obrazkow buttonow
            Image startButtonIcon = ImageIO.read(new File("src/Resources/Buttons/start.png")).getScaledInstance(88, 40, java.awt.Image.SCALE_SMOOTH);
            Image howToPlayButtonIcon = ImageIO.read(new File("src/Resources/Buttons/jakgrac.png")).getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
               Image scoreButtonIcon=ImageIO.read(new File("src/Resources/Buttons/zapiszwynik.png")).getScaledInstance(116,40,java.awt.Image.SCALE_SMOOTH);
            Image exitButtonIcon = ImageIO.read(new File("src/Resources/Buttons/wyjscie.png")).getScaledInstance(116, 40, java.awt.Image.SCALE_SMOOTH);

            startButton.setIcon(new ImageIcon(startButtonIcon));
            howToPlayButton.setIcon(new ImageIcon(howToPlayButtonIcon));
             scoreButton.setIcon(new ImageIcon(scoreButtonIcon));
            exitButton.setIcon(new ImageIcon(exitButtonIcon));

            howToPlayButton.setBorder(null);
            exitButton.setBorder(null);
            startButton.setBorder(null);
            scoreButton.setBorder(null);


            bg = ImageIO.read(new File("src/Resources/Backgrounds/rec.jpg")).
                    getScaledInstance(
                            gamePanel.getWidth(),
                            gamePanel.getHeight(),
                            java.awt.Image.SCALE_SMOOTH);


        }
        catch(IOException e) {
            System.out.println(e.getCause());
        }

        exitButton.addActionListener(this);
        startButton.addActionListener(this);
        scoreButton.addActionListener(this);
        howToPlayButton.addActionListener(this);
// definicja jLabela 
        jlabel = new JLabel("Recykling");
        jlabel.setFont(new Font("Verdana",1,50));

    }


    @Override
    public void init()
    {


    }

    @Override
    public void draw()
    {
        updateNeeded = false;
    }

    @Override
    public void addComponents()
    {
        GridBagLayout gridBagLayout = new GridBagLayout();

        gamePanel.setBorder(BorderFactory.createEmptyBorder(100,150,10,10));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        gamePanel.add(jlabel);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        gamePanel.add(startButton, gridBagLayout);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        gamePanel.add(howToPlayButton, gridBagLayout);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        gamePanel.add(scoreButton, gridBagLayout);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        gamePanel.add(exitButton, gridBagLayout);

        image.getGraphics().drawImage(bg, 0, 0, null);
    }

    //akcja po nacisnieciu buttona
    @Override
    @SuppressWarnings("null")
    public void actionPerformed(ActionEvent actionEvent)
    {
        System.out.println(actionEvent.getActionCommand());
        if (actionEvent.getSource() == startButton)
        {
            gamePanel.removeAll();

            gameStateManager.setState(states.level1State);
            updateNeeded = true;
        }
        else if (actionEvent.getSource() == exitButton)
        {
            System.exit(0);
        }
// instrukcja
        else if (actionEvent.getSource() == howToPlayButton)
        {
            gamePanel.removeAll();

            File file = new File("src/Resources/instrukcja.txt");

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }

            String st;
            try {
                while ((st = br.readLine()) != null)
                    System.out.println(st);
            } catch (IOException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }
             gamePanel.removeAll();
                  updateNeeded = true;

        }
        else if (actionEvent.getSource() == scoreButton){
         gamePanel.removeAll();

            File file = new File("src/Resources/ranking.txt");
            try {

                FileWriter fileWriter =new FileWriter(file,true);


                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Scanner in = new Scanner(System.in);
                System.out.println("Podaj nick");
                String nick=in.nextLine();

                System.out.println("Podaj wynik");
                String score=in.next();
                bufferedWriter.write("\n"+nick+"    "+score);

                bufferedWriter.close();
            }
            catch(IOException ex) {
                System.out.println(
                        "Error writing to file '"
                                + file + "'");

            }
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }

            String st;
            try {
                while ((st = br.readLine()) != null)
                    System.out.println(st);
            } catch (IOException ex) {
            } 
            
            gamePanel.removeAll();
                  updateNeeded = true;

        }
        


    }}


