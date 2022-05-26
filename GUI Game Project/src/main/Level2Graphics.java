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
	private int delay = 10;

	//Block movement variables
	private int blockX = 320;
	private int blockY = 540;
	private double vely;
	private double velx;
	int p1X = (int) (Math.random()*500);
	int p1Y = (int) (Math.random()*100)+400;
	int p2X = (int) (Math.random()*500);
	int p2Y = (int) (Math.random()*100)+300;
	int doorX = (int) (Math.random()*500);
	int doorY = (int) (Math.random()*100)+200;
	//Coin Stuff
	Color color = Color.yellow;
	Color color2 = Color.yellow;
	private int coinCount = 0;

	boolean onGround = true;
	Rectangle door = new Rectangle(doorX,doorY,200,30);
	Rectangle p1 =new Rectangle(p1X,p1Y,200,30);
	Rectangle p2 =new Rectangle(p2X,p2Y,200,30);
	Rectangle coin1 = new Rectangle(p1X+75,p1Y-75,50,50);
	Rectangle coin2 = new Rectangle(p2X+75,p2X-75,50,50);
	Rectangle doorHit = new Rectangle(375,160,10,10);
	int level;
	public Rectangle blockManCreate() {
		Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
		return blockMan;
	}


	public void paint(Graphics g) {
		//background
		setBackground(Color.CYAN);




		//Ground
		g.setColor(Color.GREEN);
		g.fillRect(0,600,700,440);

		//Door
		g.setColor(Color.black);
		g.fillRect(325,120,100,80);
		g.fillArc(325,100,100,40,180,-180);

		//Coins
		g.setColor(color);
		g.fillOval(p1X+75,p1Y-75,50,50);

		g.setColor(color2);
		g.fillOval(p2X+75,p2Y-75,50,50);

		g.setColor(Color.yellow);
		g.fillOval(-20,-20,140,140);



		//Door Platform
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(doorX,doorY,200,30);




		//Hitbox for door
		g.setColor(Color.black);
		g.fillRect(365,160,10,10);

		//BlockMan
		g.setColor(Color.gray);
		g.fillRect(blockX,blockY,60,60);


		g.setColor(Color.lightGray);

		//Borders
		g.fillRect(0,0,700,10);
		g.fillRect(0,0,10,700);
		g.fillRect(680,0,10,700);
		g.fillRect(0,680,700,10);

		//Platforms
		g.fillRect(p1X,p1Y,200,30);
		g.fillRect(p2X,p2Y,200,30);

		//Directions

		g.drawString("Press arrow keys to move left and right, and spacebar to jump", 40, 500);
		
		//Coin Counter
		g.drawString("Coins " + coinCount,600,50);

	}
	//Its what looks for key inputs
	public Level2Graphics(){

		addKeyListener(this);
		timer = new Timer(delay,this);
		timer.start();
	}

	//This happens whenever a key or a sort of action is performed
	@Override
	public void actionPerformed(ActionEvent e) {
		//Basically the block moves since as you hold the button it keeps doing this and repainting the Gui

		if (blockX < 10){
			blockX = 10;
		}
		if (blockX > 620){
			blockX = 620;
		}
		if (blockManCreate().intersects(doorHit)){
			timer.stop();
	           Level2Graphics LX = new Level2Graphics();
	           LX.setLevel(level);
			   JFrame NextLevel = new JFrame("BlockMan Adventures Level " + level); 
	           NextLevel.setSize(700,700);
	           NextLevel.setVisible(true);
	           this.setVisible(false);
	           NextLevel.add(LX);
		}

		if (blockManCreate().intersects(p1)){
			if(blockY<=p1Y) {
				blockY = p1Y-60;
				vely = 0;
				onGround = true;
			}else {
				blockY+=Math.abs(blockY-p1Y-30);
				vely=-vely/2;
			}
		}
		if (blockManCreate().intersects(p2)){
			if(blockY<=p2Y) {
				blockY = p2Y-60;
				vely = 0;
				onGround = true;	
			}else {
				blockY+=blockY-p2Y-30;
				vely=-vely;
			}

		}
		if (blockManCreate().intersects(door)){
			if(blockY<=doorY) {
			blockY = doorY-60;
			vely = 0;
			onGround = true;
			}else {
				blockY+=blockY-doorY-30;
				vely=-vely;
			}
		}
		if (blockManCreate().intersects(coin1)){
			if(color==Color.yellow) {
			coinCount++;
			color = Color.cyan;
			}
		}
		if (blockManCreate().intersects(coin2)){
			if(color2==Color.yellow) {
			coinCount++;
			color2 = Color.cyan;
			}
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
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
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			slowLeftTimer.start();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
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
			  if(blockY<540 && !(blockManCreate().intersects(door)||blockManCreate().intersects(p1)||blockManCreate().intersects(p2))) {
				  vely+=1;
			  }else {
				  vely=0;
				  velx=0;
				  gravityTimer.stop();
				  moveXTimer.stop();
				  moveYTimer.stop();
				  slowLeftTimer.stop();
				  slowRightTimer.stop();
				  if(blockY>540) {
					  blockY=540;
				  }
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
		return blockManCreate().intersects(door) || blockManCreate().intersects(p1) || blockManCreate().intersects(p2) || blockY == 540;
	}	
	Timer moveYTimer = new Timer(10,moveY);
	Timer gravityTimer = new Timer(50,gravity);
	Timer moveXTimer = new Timer(10,moveX);
	Timer slowLeftTimer = new Timer(50,slowLeft);
	Timer slowRightTimer = new Timer(50,slowRight);
	public int getLevel() {
		return level;
	}
	public void setLevel(int x) {
		level = x+1;
	}
}
