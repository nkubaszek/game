package GameState;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//przegrana
public class LevelFailedState extends GameState implements ActionListener
{
    private JLabel jlabel;
    private Image bg;

    private JButton backToMenuButton;

    public LevelFailedState(GamePanel gamePanel, GameStateManager gameStateManager)
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

            bg = ImageIO.read(new File("src/Resources/Backgrounds/levelFailedBackground.png")).
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

        jlabel.setText("Level failed ");

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
        //powrot do menu
        if (actionEvent.getSource() == backToMenuButton)
        {
            gamePanel.removeAll();//czyszczenie ekranu

            gameStateManager.setState(states.menuState);
            updateNeeded = true;
        }

    }


}
