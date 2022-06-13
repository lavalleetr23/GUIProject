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
	private int blockY = 440;
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

	boolean onGround = true;
	Rectangle door = new Rectangle(0,180,275,40);
	Rectangle p1 =new Rectangle(775,180,225,40);
	Rectangle p2 =new Rectangle(575,180,100,40);
	Rectangle p3 =new Rectangle(375,180,100,40);
	Rectangle g1 =new Rectangle(0,500,275,440);
	Rectangle g2 =new Rectangle(375,500,100,440);
	Rectangle g3 =new Rectangle(575,500,100,440);
	Rectangle g4 =new Rectangle(775,500,225,440);
	Rectangle ladder =new Rectangle(840,220,70,280);
	Rectangle coin1 = new Rectangle(300,80,50,50);
	Rectangle coin2 = new Rectangle(500,330,50,50);
	Rectangle doorHit = new Rectangle(20,100,20,80);
	Rectangle lava1 = new Rectangle(275,520,100,80);
	Rectangle lava2 = new Rectangle(475,520,100,80);
	Rectangle lava3 = new Rectangle(675,520,100,80);
	


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
		g.setColor(Color.gray);
		g.fillRect(blockX,blockY,60,60);

		g.setColor(Color.BLACK);

		//Borders
		g.fillRect(0,0,1000,10);
		g.fillRect(0,0,10,700);
		g.fillRect(980,0,10,700);
		g.fillRect(0,680,1000,10);




		//Coin Counter
		g.drawString("Coins " + coinCount ,900,50);

	}
	public Level2Graphics(){

		addKeyListener(this);
		timer = new Timer(delay,this);
		timer.start();
	}
	//This happens whenever a key or a sort of action is performed
	@Override
	public void actionPerformed(ActionEvent e) {
		//Basically the block moves since as you hold the button it keeps doing this and repainting the Gui
		if (blockManCreate().intersects(doorHit)){
			timer.stop();
			Main cL2 = new Main();
			cL2.Level2Clear(true);
		}
		//Collisions
		if (blockManCreate().intersects(p1)){
				blockY = 120;
				vely = 0;
				onGround = true;
		}
		if (blockManCreate().intersects(p2)){
			if(blockY<=180) {
				blockY = 120;
				vely = 0;
				onGround = true;
			}else {
				blockY=220;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(p3)){
			if(blockY<=180) {
				blockY = 120;
				vely = 0;
				onGround = true;
			}else {
				blockY=220;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(g1)){
			if(blockY<=500) {
				blockY = 440;
				vely = 0;
				onGround = true;
			}else {
				blockY=940;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(g2)){
			if(blockY<=500) {
				blockY = 440;
				vely = 0;
				onGround = true;
			}else {
				blockY=940;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(g3)){
			if(blockY<=500) {
				blockY = 440;
				vely = 0;
				onGround = true;
			}else {
				blockY=940;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(g4)){
			if(blockY<=500) {
				blockY = 440;
				vely = 0;
				onGround = true;
			}else {
				blockY=940;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}

		}
		if (blockManCreate().intersects(door)){
			if(blockY<=180) {
				blockY = 120;
				vely = 0;
				onGround = true;
			}else {
				blockY=220;
				moveYTimer.start();
				gravityTimer.start();
				vely=-vely;
				onGround=false;
				gravityTimer.start();
			}
		}
		if (blockManCreate().intersects(coin1)){
			if(color==Color.yellow) {
				coinCount++;
				color = Color.DARK_GRAY;
			}
		}
		if (blockManCreate().intersects(coin2)){
			if(color2==Color.yellow) {
				coinCount++;
				color2 = Color.DARK_GRAY;
			}
		}
		if(blockManCreate().intersects(lava1)||blockManCreate().intersects(lava2)||blockManCreate().intersects(lava3)) {
			blockX=25;
			blockY=440;
			coinCount=0;
			color=Color.yellow;
			color2=Color.yellow;
		}

		repaint();

	}

	//This is for clicks
	@Override
	public void keyTyped(KeyEvent e) {

	}

	//This is when you hold the key
	@Override
	public void keyPressed(KeyEvent e) {
		//Increments left each second or something
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			slowRightTimer.stop();
			slowLeftTimer.stop();
			if(velx>-3) {
				velx-=3;
				moveXTimer.start();
			}
			if(!OnGroundTest()) {
				moveYTimer.start();
				gravityTimer.start();
				onGround = false;
			}
		}

		//Increments right each second or something
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			slowRightTimer.stop();
			slowLeftTimer.stop();
			if(velx<3) {
				velx=3;
				moveXTimer.start();
			}
			if(!OnGroundTest()) {
				moveYTimer.start();
				gravityTimer.start();
				onGround = false;
			}
		}

		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(onGround) {
				vely=-10;
				moveYTimer.start();
				gravityTimer.start();
				onGround = false;
			}
		}
		//These two if statements set the boundaries for how far left or right it can go

	}
	//In the name ngl
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) {
			slowLeftTimer.start();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) {
			slowRightTimer.start();
		}
	}
	//Adjusts the Y position according to the velocity
	ActionListener moveY = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			blockY+=vely;
		}
	};
	//Adjusts the X position according to the velocity
	ActionListener moveX = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			blockX+=velx;
		}
	};
	//Gravity
	ActionListener gravity = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!OnGroundTest()) {
				vely+=1;
				onGround = false;
			}else {
				vely=0;
				velx=0;
				gravityTimer.stop();
				moveXTimer.stop();
				moveYTimer.stop();
				slowLeftTimer.stop();
				slowRightTimer.stop();
				onGround = true;
			}
		}
	};
	//Slows leftward movement
	ActionListener slowLeft = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(velx<0) {
				velx+=0.1;
			}
		}
	};
	//Slows rightward movement
	ActionListener slowRight = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(velx>0) {
				velx-=0.1;
			}
		}
	};
	public boolean OnGroundTest() {
		return blockManCreate().intersects(door) || blockManCreate().intersects(p1) || blockManCreate().intersects(p2) || blockManCreate().intersects(p3) || blockManCreate().intersects(g1) || blockManCreate().intersects(g2) || blockManCreate().intersects(g3) || blockManCreate().intersects(g4) || blockManCreate().intersects(ladder);
	}
	Timer moveYTimer = new Timer(10,moveY);
	Timer gravityTimer = new Timer(50,gravity);
	Timer moveXTimer = new Timer(10,moveX);
	Timer slowLeftTimer = new Timer(50,slowLeft);
	Timer slowRightTimer = new Timer(50,slowRight);


}

