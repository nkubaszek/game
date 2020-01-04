package GameState;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
// klasa zajmująca się obiektami do zebrania
public class Trash
{
    private int xPosition;//wspolrzedna x polozenia
    private int yPosition;
    private Image trashIcon;
    int pointsForCollecting;// punkty do zdobycia

    private static final int MAXDISTANCETOCOLLECT = 40;
    private static final int ICONWIDTH = 59;
    private static final int ICONHEIGHT = 57;

    public enum TrashType
    {
        bio,
        paper,
        glass,
        plastic
    }
//konstruktor klasy trash
    public Trash(int xPosition, int yPosition, TrashType trashType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        switch (trashType)
        {

            case bio:
                //wczytanie obrazkow obiektow do zdobycia
                try {
                    trashIcon = ImageIO.read(new File("src/Resources/Objects/bio/bio_icon.jpg")).getScaledInstance(ICONWIDTH,ICONHEIGHT,java.awt.Image.SCALE_SMOOTH);
                    pointsForCollecting = 10;//ilosc punktow do zdobycia za odpad
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case paper:
                try {
                    trashIcon = ImageIO.read(new File("src/Resources/Objects/papier/papier_icon.jpg")).getScaledInstance(ICONWIDTH,ICONHEIGHT,java.awt.Image.SCALE_SMOOTH);;;
                    pointsForCollecting = 15;//ilosc punktow do zdobycia za odpad
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case glass:
                try {
                    trashIcon = ImageIO.read(new File("src/Resources/Objects/szklo/szklo_icon.jpg")).getScaledInstance(ICONWIDTH,ICONHEIGHT,java.awt.Image.SCALE_SMOOTH);;;
                    pointsForCollecting = 20;//ilosc punktow do zdobycia za odpad
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case plastic:
                try {
                    trashIcon = ImageIO.read(new File("src/Resources/Objects/plastik/plastik_icon.jpg")).getScaledInstance(ICONWIDTH,ICONHEIGHT,java.awt.Image.SCALE_SMOOTH);;;
                    pointsForCollecting = 25;//ilosc punktow do zdobycia za odpad
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    public int getPointsForCollecting() {
        return pointsForCollecting;
    }

    public boolean checkIfPlayerIsPicking(Player player)
    {
        return player.calculateDistanceToPoint(xPosition + ICONWIDTH / 2, yPosition + ICONHEIGHT / 2) < MAXDISTANCETOCOLLECT;
    }

    public void drawTrash(Graphics2D graphics2D)
    {
        graphics2D.drawImage(trashIcon, xPosition, yPosition,null);
    }
}


