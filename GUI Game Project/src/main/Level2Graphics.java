package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Level2Graphics extends Panel implements ActionListener, KeyListener {
	//Timer stuff
	private Timer timer;
	private int delay = 20;

	//Block movement variables
	private int blockX = 25;
	private int blockY = 680;
	private double vely;
	private double velx;

	//Coin Stuff
	Color color = Color.yellow;
	Color color2 = Color.yellow;
	private int coinCount = 0;

	public Rectangle blockManCreate() {
		Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
		return blockMan;
	}

	public void paint(Graphics g){
		//background
		setBackground(Color.DARK_GRAY);

		//Ground
		g.setColor(Color.BLACK);
		g.fillRect(0,500,1000,440);

		//Holes
		g.setColor(Color.DARK_GRAY);
		g.fillRect(275,500,100,100);
		g.fillRect(475,500,100,100);
		g.fillRect(675,500,100,100);

		//Lava
		g.setColor(Color.red);
		g.fillRect(275,520,100,80);
		g.fillRect(475,520,100,80);
		g.fillRect(675,520,100,80);

		//Platforms
		g.setColor(Color.black);
		g.fillRect(0,180,275,40);
		g.fillRect(375,180,100,40);
		g.fillRect(575,180,100,40);
		g.fillRect(775,180,225,40);

		//Ladder
		g.setColor(Color.white);
		g.fillRect(840,220,10,280);
		g.fillRect(900,220,10,280);
		g.fillRect(840,240,60,10);
		g.fillRect(840,280,60,10);
		g.fillRect(840,320,60,10);
		g.fillRect(840,360,60,10);
		g.fillRect(840,400,60,10);
		g.fillRect(840,440,60,10);
		g.fillRect(840,480,60,10);



		//Coins
		g.setColor(color);
		g.fillOval(300,80,50,50);

		g.setColor(color2);
		g.fillOval(500,330,50,50);



		//Door
		g.setColor(Color.white);
		g.fillRect(20,100,100,80);
		g.fillArc(20,80,100,40,180,-180);

		//Hitbox for door
		g.setColor(Color.white);
		g.fillRect(40,160,10,10);

		//BlockMan
		if(blockY<=540) {
			g.setColor(Color.gray);
			g.fillRect(blockX,blockY,60,60);
		}else {
			g.setColor(Color.gray);
			g.fillRect(blockX,440,60,60);
		}


		g.setColor(Color.BLACK);

		//Borders
		g.fillRect(0,0,1000,10);
		g.fillRect(0,0,10,700);
		g.fillRect(980,0,10,700);
		g.fillRect(0,680,1000,10);




		//Coin Counter
		g.drawString("Coins " + coinCount,900,50);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	//Timer stuff

}
