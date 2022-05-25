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
	private int delay = 8;

	//Block movement variables
	private int blockX = 320;
	private int blockY = 540;
	private int velx;
	private int vely;

	public void paint(Graphics g) {
		//background
		setBackground(Color.CYAN);

		//Ground
		g.setColor(Color.GREEN);
		g.fillRect(0,600,700,440);

		//BlockMan
		g.setColor(Color.gray);
		g.fillRect(blockX,blockY,60,60);

		//Door Platform
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(275,200,200,30);

		//Door
		g.setColor(Color.black);
		g.fillRect(325,120,100,80);
		g.fillArc(325,100,100,40,180,-180);


		g.setColor(Color.lightGray);

		//Borders
		g.fillRect(0,0,700,10);
		g.fillRect(0,0,10,700);
		g.fillRect(700,0,10,700);
		g.fillRect(0,700,700,10);

		//Platforms
		g.fillRect(40,350,200,30);
		g.fillRect(400,490,200,30);

		//Coins
		g.setColor(Color.yellow);
		g.fillOval(120,290,50,50);
		g.fillOval(480,430,50,50);

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
			velx -=1;
			new Timer(100,moveX).start();
		}

		//Increments right each second or something
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			velx +=1;
			new Timer(100,moveX).start();
		}

		//These two if statements set the boundaries for how far left or right it can go
		if (blockX < 10){
			blockX = 10;
		}
		if (blockX > 640){
			blockX = 640;
		}
		
		//Limits X velocity
		if(velx>3) {
			velx=3;
		}
		if(velx<-3) {
			velx=-3;
		}

	}

	//In the name ngl
	@Override
	public void keyReleased(KeyEvent e) {

	}
	//Adjusts the X position according to the velocity
	  ActionListener moveX = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	         blockX+=velx;
	      }
	  };
}
