/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import GameState.GameState;
import Main.GamePanel;


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Player
{
	private int xPosition;//wspolrzedna x pozycji gracza
	private int yPosition;//wspolrzedna y pozycji gracza
	private Map<PlayerState, List<Image>> playerPose;
	private int points;

	private final static int PLAYERHEIGHT = 110;//wysokosc gracza
	private final static int PLAYERWIDTH = 80;//szerokosc gracza

	private final static int INITIALXPOSITION = 0;//poczatkowa pozycja x
	private final static int INITIALYPOSITION = 90;//poczatkowa pozycja y
	private final static int JUMPTIME = 15;//czas skoku

	private final static int LEFTMOVE = 5;//ruch w lewo
	private final static int RIGHTMOVE = 5;//ruch w prawo
	private final static int FALLINGMOVE = 10;//spadanie


	private Iterator iterator;
	private Image currentPlayerPose;
	private PlayerState playerState;

	int jumpTime = 0;

	public enum PlayerState
	{
		walk,
		stand,
		jump
	}

	public enum PlayerMapPosition
	{
		fail,//przegrana
		endLevel,//konie poziomu
		inAir,//w powietrzu
		onGround//na ziemii
	}
//konstruktor klasy Player
	public Player() {
		this.xPosition = INITIALXPOSITION;
		this.yPosition = INITIALYPOSITION;

		playerPose = new HashMap<PlayerState, List<Image>>();

		try {
			List<Image> standPoses = new ArrayList<>();
			List<Image> jumpPoses = new ArrayList<>();
			List<Image> walkPoses = new ArrayList<>();
//wczytanie obrazow do odpowiednich pozycji gracza
			standPoses.add(ImageIO.read(new File("src/Resources/Player/female_stand.png")));//gracz stoi

			jumpPoses.add(ImageIO.read(new File("src/Resources/Player/female_jump.png")));//gracz skacze
// gracz sie porusza
			walkPoses.add(ImageIO.read(new File("src/Resources/Player/female_walk1.png")));
			walkPoses.add(ImageIO.read(new File("src/Resources/Player/female_walk1.png")));
			walkPoses.add(ImageIO.read(new File("src/Resources/Player/female_walk2.png")));
			walkPoses.add(ImageIO.read(new File("src/Resources/Player/female_walk2.png")));

			playerPose.put(PlayerState.stand, standPoses);
			playerPose.put(PlayerState.jump, jumpPoses);
			playerPose.put(PlayerState.walk, walkPoses);

		} catch (IOException e) {
			e.printStackTrace();
		}

		playerState = PlayerState.stand;
		iterator = playerPose.get(playerState).listIterator();
		currentPlayerPose = (Image) iterator.next();

		points = 0;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public Image getPlayerPose() {

		return currentPlayerPose;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
// metoda pozwalająca na ustawienie ppozycji gracza
	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
		updatePose();
	}

	public void updatePose()
	{
		if (!iterator.hasNext())
			iterator = playerPose.get(playerState).listIterator();

		currentPlayerPose = (Image) iterator.next();
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public static int getPLAYERHEIGHT() {
		return PLAYERHEIGHT;
	}

	public static int getPLAYERWIDTH() {
		return PLAYERWIDTH;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void updatePosition(Set<Integer> pressed, PlayerMapPosition playerMapPosition)
	{
		if (playerState != PlayerState.jump)
		{
			if (playerMapPosition == PlayerMapPosition.inAir)
				setyPosition(yPosition + FALLINGMOVE);
		}
		else if (playerState == PlayerState.jump)
		{
			++jumpTime;
			if (jumpTime >= JUMPTIME)
			{
				jumpTime = 0;
				setPlayerState(PlayerState.stand);
			}
			else
				setyPosition(yPosition - FALLINGMOVE);
		}
		if (playerState == PlayerState.walk)
		{
			if (pressed.size() == 0)
				setPlayerState(PlayerState.stand);
		}

		for (Integer key : pressed)
		{
			// left
			if (key == 37)
			{
				if (getPlayerState() != PlayerState.jump)
					setPlayerState(PlayerState.walk);

				if (xPosition - LEFTMOVE > 0)
					setxPosition(xPosition - LEFTMOVE);
			}
			//right
			if (key == 39)
			{
				if (getPlayerState() != PlayerState.jump)
					setPlayerState(PlayerState.walk);

				setxPosition(xPosition + RIGHTMOVE);
			}
			//up
			if (key == 38)
			{
				if (getPlayerState() != PlayerState.jump
						&& playerMapPosition == PlayerMapPosition.onGround)
				{
					setPlayerState(PlayerState.jump);
				}
			}
		}
	}

	public void drawPlayer(Graphics2D graphics2D)
	{
		graphics2D.drawImage(getPlayerPose(), xPosition, yPosition,null);
	}

	public int calculateDistanceToPoint(int x, int y)//obliczenie dystansu do okreslonego punktu
	{
		int middleX = xPosition + (PLAYERWIDTH / 2);
		int middleY = yPosition + (PLAYERHEIGHT / 2);

		return (int) Math.round(Math.sqrt( Math.pow(middleX - x, 2) + Math.pow(middleY - y, 2)  ));

	}

}





  