package GameState;

import entity.Player;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Map {

    private boolean[][] map;

    private static final int PLAYERFRAMES = 15;

    private Vector<Trash> trashes;

    private int width;
    private int height;

    private Player.PlayerMapPosition playerMapPosition;


    private Player player;


    Map(int width, int height)
    {
        this.width = width;
        this.height = height;
//polozenie gdzie gracz moze sie znajdowac, bez przegania gry, czyli implementacja wspolrzednych wysepek
        map = new boolean[this.width][this.height];
        for (int i = 0; i < 640; ++i)
        {
            if (i < 90)
                map[i][360] = true;

            if (i >= 180 && i < 270)
                map[i][360] = true;

            if (i >= 360 && i < 450)
                map[i][360] = true;

            if (i >= 540 && i < width)
                map[i][360] = true;
        }

        player = new Player();

        playerMapPosition = checkPlayerPositionOnMap();
        trashes = new Vector<Trash>();

        addTrashes();
    }
    public Player.PlayerMapPosition checkPlayerPositionOnMap()//sprawdzenie pozycji gracza
    {
        if (player.getyPosition() + Player.getPLAYERHEIGHT() >= this.height)
        {
            playerMapPosition = Player.PlayerMapPosition.fail;
            return playerMapPosition;
        }

        playerMapPosition = Player.PlayerMapPosition.inAir;//gracz w powietrzu

        for (int i = PLAYERFRAMES; i < Player.getPLAYERWIDTH() - PLAYERFRAMES; ++i)
        {
            if (player.getxPosition() + i < width && player.getyPosition() + Player.getPLAYERHEIGHT() < height)
            {
                if (map[player.getxPosition() + i][player.getyPosition() + Player.getPLAYERHEIGHT()])
                {
                    playerMapPosition = Player.PlayerMapPosition.onGround;//pozycja gracza na ziemi
                    break;
                }
            }
            else
            {
                playerMapPosition = Player.PlayerMapPosition.endLevel;
                break;
            }

        }

        return playerMapPosition;
    }
//ustawienie obiektow na planszy
    private void addTrashes()
    {
        trashes.add(new Trash(100, 150, Trash.TrashType.bio));
        trashes.add(new Trash(190, 150, Trash.TrashType.glass));
        trashes.add(new Trash(380, 300, Trash.TrashType.paper));
        trashes.add(new Trash(450, 150, Trash.TrashType.plastic));
    }
//rysowanie obiektow
    public void drawTrashes(Graphics2D graphics2D)
    {
        for (Trash trash : trashes)
            trash.drawTrash(graphics2D);
    }
//zbieranie obiektow
    public void collectTrashes(Player player)
    {
//definicja iteratora
        Iterator<Trash> iterator = trashes.iterator();

        while (iterator.hasNext()) {
            Trash trash = iterator.next();

            if (trash.checkIfPlayerIsPicking(player))
            {
                player.setPoints(player.getPoints() + trash.getPointsForCollecting());
                iterator.remove();
            }
        }

    }

    void draw(Graphics2D graphics2D, Set<Integer> pressed)
    {
        player.updatePosition(pressed, playerMapPosition);//aktualizacja polozenia gracza
        player.drawPlayer(graphics2D);//narysowanie gracza

        collectTrashes(player);
        drawTrashes(graphics2D);
    }
//koncowy wynik
    int getFinalScore()
    {
        return player.getPoints();
    }

}
