package GameState;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LevelCompleteState extends GameState implements ActionListener
{
    private JLabel jlabel;
    private Image bg;

    private JButton backToMenuButton;//powrot do menu
//konstruktor klasy
    public LevelCompleteState(GamePanel gamePanel, GameStateManager gameStateManager)
    {
        super(gamePanel, gameStateManager);
        backToMenuButton = new JButton();

        try {
            Image backToMenuButtonIcon = ImageIO.read(new File("src/Resources/Buttons/menu.png")).getScaledInstance(75,40,java.awt.Image.SCALE_SMOOTH);

            backToMenuButton.setIcon(new ImageIcon(backToMenuButtonIcon));
            backToMenuButton.setBorder(null);

        } catch (IOException ex) {
            System.out.println(ex);
        }

        backToMenuButton.addActionListener(this);


        try {

            bg = ImageIO.read(new File("src/Resources/Backgrounds/levelCompleteBackground.png")).
                    getScaledInstance(
                            gamePanel.getWidth(),
                            gamePanel.getHeight(),
                            java.awt.Image.SCALE_SMOOTH);

        }
        catch(IOException e) {
            System.out.println(e.getCause());
        }


        jlabel = new JLabel("");
        jlabel.setFont(new Font("Verdana",1,20));
    }

    @Override
    public void init()
    {
        //draw title
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

        jlabel.setText("Level Complete with score: " + gameStateManager.getFinalScore());//podsumowanie gry

        gamePanel.setBorder(BorderFactory.createEmptyBorder(100,150,10,10));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        gamePanel.add(jlabel);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        gamePanel.add(backToMenuButton, gridBagLayout);

        image.getGraphics().drawImage(bg, 0, 0, null);
    }

    @Override
    @SuppressWarnings("null")
    public void actionPerformed(ActionEvent actionEvent)
    {
        System.out.println(actionEvent.getActionCommand());
        if (actionEvent.getSource() == backToMenuButton)
        {
            gamePanel.removeAll();

            gameStateManager.setState(states.menuState);
            updateNeeded = true;
        }

    }


}
