package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class BossBattle extends Panel implements ActionListener, KeyListener  {
	//Timer stuff
	private Timer timer;
	private int delay = 20;

	//Block movement variables
	private int blockX = 25;
	private int blockY = 440;
	private double vely;
	private double velx;
	
	private int playerHealth = 3;
	private int bossHealth = 500;
	
    public Rectangle blockManCreate() {
        Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
        return blockMan;
    }
	
	public void paint(Graphics g) {
        //background
        setBackground(Color.decode("#79c2f8"));
		//Ground
		g.setColor(Color.decode("#69bc14"));
		g.fillRect(0,600,1000,440);
		//Player Health
		if(playerHealth>=1) {
			g.setColor(Color.red);
			g.fillRect(800,650,50,20);
			g.fillRect(815,635,20,50);
		}
		if(playerHealth>=2) {
			g.setColor(Color.red);
			g.fillRect(860,650,50,20);
			g.fillRect(875,635,20,50);
		}else {
			g.setColor(Color.gray);
			g.fillRect(860,650,50,20);
			g.fillRect(875,635,20,50);
		}
		if(playerHealth==3) {
			g.setColor(Color.red);
			g.fillRect(920,650,50,20);
			g.fillRect(935,635,20,50);
		}else {
			g.setColor(Color.gray);
			g.fillRect(920,650,50,20);
			g.fillRect(935,635,20,50);
		}
		//Boss Health
		g.setColor(Color.red);
		g.fillRect(250,50,bossHealth,50);
	}
    public BossBattle(){

        addKeyListener(this);
        timer = new Timer(delay,this);
        timer.start();



    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
