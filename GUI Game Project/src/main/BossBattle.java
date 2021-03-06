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
	private int blockX = 100;
	private int blockY = 540;
	private double vely;
	private double velx;
	
	private int playerHealth = 3;
	private int bossHealth = 500;
	
	private int p1X;
	private int p1Y;
	private int p2X;
	private int p2Y;
	private int p3X;
	private int p3Y;
	private int bpX;
	private int bpY;
	
	private boolean p1vis = false;
	private boolean p2vis = false;
	private boolean p3vis = false;
	private boolean bpvis = false;
	
	private int ammo = 3;
	
	private int fuel = 100;
	private boolean jetpackuse=false;
	
	private int phase = 1;
	
	private boolean win;
	private boolean gameover=false;
	
	Rectangle Boss = new Rectangle(800,100,200,500);
	Rectangle p1 = new Rectangle(p1X,p1Y,20,20);
	Rectangle p2 = new Rectangle(p2X,p2Y,20,20);
	Rectangle p3 = new Rectangle(p3X,p2Y,20,20);
	Rectangle bp = new Rectangle(bpX,bpY,40,40);
    
	public Rectangle blockManCreate() {
        Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
        return blockMan;
    }
	
	public void paint(Graphics g) {
        p1.setBounds(p1X,p1Y,20,20);
        p2.setBounds(p2X,p2Y,20,20);
        p3.setBounds(p3X,p3Y,20,20);
        bp.setBounds(bpX,bpY,40,40);
		//background
        setBackground(Color.decode("#79c2f8"));
		//Ground
		g.setColor(Color.decode("#69bc14"));
		g.fillRect(0,600,1000,440);
		//Player Health
		if(playerHealth>=1) {
			g.setColor(Color.red);
			g.fill3DRect(800,650,50,20,true);
			g.fill3DRect(815,635,20,50,true);
		}else {
			g.setColor(Color.gray);
			g.fill3DRect(800,650,50,20,true);
			g.fill3DRect(815,635,20,50,true);
		}
		if(playerHealth>=2) {
			g.setColor(Color.red);
			g.fill3DRect(860,650,50,20,true);
			g.fill3DRect(875,635,20,50,true);
		}else {
			g.setColor(Color.gray);
			g.fill3DRect(860,650,50,20,true);
			g.fill3DRect(875,635,20,50,true);
		}
		if(playerHealth==3) {
			g.setColor(Color.red);
			g.fill3DRect(920,650,50,20,true);
			g.fill3DRect(935,635,20,50,true);
		}else {
			g.setColor(Color.gray);
			g.fill3DRect(920,650,50,20,true);
			g.fill3DRect(935,635,20,50,true);
		}
		//Player Ammo
		if(ammo>=1) {
			g.setColor(Color.black);
		}else {
			g.setColor(Color.gray);
		}
		g.fill3DRect(850,700,10,10,true);
		if(ammo>=2) {
			g.setColor(Color.black);
		}else {
			g.setColor(Color.gray);
		}
		g.fill3DRect(900,700,10,10,true);
		if(ammo==3) {
			g.setColor(Color.black);
		}else {
			g.setColor(Color.gray);
		}
		g.fill3DRect(950,700,10,10,true);
		//Boss Health
		g.setColor(Color.red);
		g.fill3DRect(250,50,bossHealth,50,true);
		//BlockMan
		g.setColor(Color.gray);
		g.fill3DRect(blockX,blockY,60,60,true);
		//Jetpack
		g.setColor(Color.black);
		g.fill3DRect(blockX-10,blockY+15,10,30,true);
		if(jetpackuse) {
			g.setColor(Color.orange);
			g.fillRect(blockX-10,blockY+45,8,10);
		}
		//Boss
		g.setColor(Color.decode("#ff00e6"));
		g.fill3DRect(800,100,200,500,true);
		//Fuel gauge
		g.setColor(Color.orange);
		g.fill3DRect(700,735-fuel,50,fuel,true);
		g.setColor(Color.gray);
		g.fill3DRect(700,635,50,100-fuel,true);
		//Projectiles
		if(p1vis) {
			g.setColor(Color.black);
			g.fill3DRect(p1X,p1Y,20,20,true);
		}
		if(p2vis) {
			g.setColor(Color.black);
			g.fill3DRect(p2X,p2Y,20,20,true);
		}
		if(p3vis) {
			g.setColor(Color.black);
			g.fill3DRect(p3X,p3Y,20,20,true);
		}
		if(bpvis) {
			g.setColor(Color.black);
			g.fill3DRect(bpX,bpY,40,40,true);
		}
		if(gameover) {
			g.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
			if(win){
				g.drawString("You Win!",300,500);
			}else {
				g.drawString("You Lost!",300,500);
			}
			timer.stop();
		}
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
            }
        }
		if(e.getKeyCode()==KeyEvent.VK_F) {
			if(!p1vis) {
				p1Y = blockY + 20;
				p1X = blockX + 60;
				p1vis=true;
				ammo-=1;
			}else if(!p2vis) {
				p2Y = blockY + 20;
				p2X = blockX + 60;
				p2vis=true;
				ammo-=1;
			}else if(!p3vis) {
				p3Y = blockY + 20;
				p3X = blockX + 60;
				p3vis=true;
				ammo-=1;
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
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_SPACE){
                if(fuel>0) {
                	vely=-10;
                	moveYTimer.start();
                	gravityTimer.start();
                	jetpackuse = true;
                }
              
        }

        //These two if statements set the boundaries for how far left or right it can go

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) {
            slowLeftTimer.start();
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) {
            slowRightTimer.start();
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
        	jetpackuse=false;
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(blockY>540) {
			blockY=540;
		}
		if(blockY<100) {
			blockY=100;
			vely=0;
		}
		if(blockX<0) {
			blockX=0;
		}
		if(blockX>940) {
			blockX=940;
		}
		if(blockManCreate().intersects(Boss)) {
			playerHealth-=1;
			velx=-10;
		}
		if(p1vis) {
			p1X+=10;
		}
		if(p2vis) {
			p2X+=10;
		}
		if(p3vis) {
			p3X+=10;
		}
		if(p1.intersects(Boss)) {
			if(p1vis) {
				bossHealth-=50;
				p1vis =false;
				ammo+=1;
			}
		}
		if(p2.intersects(Boss)) {
			if(p2vis) {
				bossHealth-=50;
				p2vis =false;
				ammo+=1;
			}
		}
		if(p3.intersects(Boss)) {
			if(p3vis) {
				bossHealth-=50;
				p3vis =false;
				ammo+=1;
			}
		}
		if(bossHealth>0 && !bpvis) {
			bpX=760;
			bpY=blockY-10;
			bpvis=true;
		}
		if(bossHealth<500) {
			phase=2;
		}
		if(bpvis){
			if(phase==1) {
				bpX-=20;
			}else if(phase==2) {
				bpX-=30;
			}
		}
		if(bp.intersects(blockManCreate())) {
			playerHealth-=1;
			bpvis=false;
			bpX=740;
		}
		if(bpX<-40) {
			bpvis=false;
		}
		if(playerHealth<=0 || bossHealth<=0) {
			gameover = true;
			if(playerHealth<=0){
				win=false;
			}else {
				win=true;
			}
		}
		if(jetpackuse) {
			fuel-=5;
		}
		if(!jetpackuse&&fuel<100){
				fuel+=1;
		}
		if(fuel<=0){
			jetpackuse=false;
		}
		repaint();
	}
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
            }else {
                vely=0;
                velx=0;
                gravityTimer.stop();
                moveXTimer.stop();
                moveYTimer.stop();
                slowLeftTimer.stop();
                slowRightTimer.stop();
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
    	if(blockY<540) {
    		return false;
    	}else {
    		return true;
    	}
    }
    Timer moveYTimer = new Timer(10,moveY);
    Timer gravityTimer = new Timer(50,gravity);
    Timer moveXTimer = new Timer(10,moveX);
    Timer slowLeftTimer = new Timer(50,slowLeft);
    Timer slowRightTimer = new Timer(50,slowRight);
}
