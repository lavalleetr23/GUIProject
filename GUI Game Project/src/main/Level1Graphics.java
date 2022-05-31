package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Level1Graphics extends Panel implements ActionListener, KeyListener {
	//Timer stuff
	private Timer timer;
	private int delay = 20;

	//Block movement variables
	private int blockX = 320;
	private int blockY = 540;
	private double vely;
	private double velx;

	//Coin Stuff
	Color color = Color.yellow;
	Color color2 = Color.yellow;
	private int coinCount = 0;
	
	boolean onGround = true;
	Rectangle door = new Rectangle(275,200,200,30);
	Rectangle p1 =new Rectangle(400,490,200,30);
	Rectangle p2 =new Rectangle(40,350,200,30);
	Rectangle coin1 = new Rectangle(120,290,50,50);
	Rectangle coin2 = new Rectangle(480,430,50,50);
	Rectangle doorHit = new Rectangle(375,160,10,10);
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
		g.fillOval(120,290,50,50);

		g.setColor(color2);
		g.fillOval(480,430,50,50);

		g.setColor(Color.yellow);
		g.fillOval(-20,-20,140,140);



		//Door Platform
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(275,200,200,30);




		//Hitbox for door
		g.setColor(Color.black);
		g.fillRect(365,160,10,10);

		//BlockMan
		if(blockY<=540) {
		g.setColor(Color.gray);
		g.fillRect(blockX,blockY,60,60);
		}else {
			g.setColor(Color.gray);
			g.fillRect(blockX,540,60,60);
		}


		g.setColor(Color.lightGray);

		//Borders
		g.fillRect(0,0,700,10);
		g.fillRect(0,0,10,700);
		g.fillRect(680,0,10,700);
		g.fillRect(0,680,700,10);

		//Platforms
		g.fillRect(40,350,200,30);
		g.fillRect(400,490,200,30);

		//Directions

		g.drawString("Press arrow keys to move left and right, and spacebar to jump", 40, 500);
		
		//Coin Counter
		g.drawString("Coins " + coinCount,600,50);
		
	}
	//Its what looks for key inputs
	public Level1Graphics(){

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
			this.setVisible(false);
			Intermission inter = new Intermission();
			this.add(inter);
			JPanel im = new JPanel();
			JLabel win = new JLabel("You Win");
			win.setBounds(300,300,100,50);
			win.setVisible(true);
			this.add(win);
			im.add(win);
		}

		if (blockManCreate().intersects(p1)){
			if(blockY<=490) {
				blockY = 430;
				vely = 0;
				onGround = true;
			}else {
				blockY=520;
				vely=-vely/2;
			}
		}
		if (blockManCreate().intersects(p2)){
			if(blockY<=350) {
				blockY = 290;
				vely = 0;
				onGround = true;	
			}else {
				blockY=380;
				vely=-vely;
			}

		}
		if (blockManCreate().intersects(door)){
			if(blockY<=200) {
			blockY = 140;
			vely = 0;
			onGround = true;
			}else {
				blockY=230;
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





		repaint(blockX,blockY,60,60);
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
			if(velx>-5) {
				velx-=5;
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
			if(velx<5) {
				velx=5;
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
				  onGround = false;
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


}
