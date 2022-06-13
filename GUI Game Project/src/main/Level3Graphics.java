package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Level3Graphics extends Panel implements ActionListener, KeyListener {
    //Timer stuff
    private Timer timer;
    private int delay = 20;

    //Block movement variables
    private int blockX = 25;
    private int blockY = 160;
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
    Rectangle door = new Rectangle();
    Rectangle p1 =new Rectangle(0,220,470,80);
    Rectangle p2 =new Rectangle();
    Rectangle coin1 = new Rectangle();
    Rectangle coin2 = new Rectangle();
    Rectangle doorHit = new Rectangle();

    public void paint(Graphics g){
        //background
        setBackground(Color.decode("#d2efff"));

        //Ground
        g.setColor(Color.darkGray);
        g.fillRect(0,220,370,700);
        g.fillRect(370,220,100,40);
        g.fillRect(350,240,100,40);
        g.fillRect(330,260,100,40);
        g.fillRect(310,280,100,40);
        g.fillRect(290,300,100,40);


        //Waterfall
        g.setColor(Color.cyan);
        g.fillRect(160,220,100,700);
        g.setColor(Color.white);
        g.fillOval(175,730,50,50);
        g.fillOval(145,730,50,50);
        g.fillOval(205,730,50,50);
        g.fillOval(235,730,40,50);



        //Coins
        g.setColor(color);
        g.fillOval(300,80,50,50);

        g.setColor(color2);
        g.fillOval(500,330,50,50);



        //Door
        g.setColor(Color.black);
        g.fillRect(900,100,100,80);
        g.fillArc(900,80,100,40,180,-180);

        //Hitbox for door
        g.setColor(Color.white);
        g.fillRect(940,160,10,10);

        //BlockMan

        g.setColor(Color.gray);
        g.fillRect(blockX,160,60,60);



        g.setColor(Color.BLACK);

        //Borders
        g.fillRect(0,0,1000,10);
        g.fillRect(0,0,10,800);
        g.fillRect(980,0,10,800);
        g.fillRect(0,780,1000,10);




        //Coin Counter
        g.drawString("Coins " + coinCount,900,50);

    }
    public Level3Graphics(){

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
        if (blockX > 990){
            blockX = 990;
        }
        if (blockManCreate().intersects(doorHit)){
            timer.stop();
            Main cL1 = new Main();
            cL1.Level1Clear(true);
        }
        //Collisions
        if (blockManCreate().intersects(p1)){
            if(blockY<=220 && blockX < 471) {
                blockY = 160;
                vely = 0;
                onGround = true;
            }else {
                blockY=250;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }

        }
        if (blockManCreate().intersects(p2)){
            if(blockY<=350) {
                blockY = 290;
                vely = 0;
                onGround = true;
            }else {
                blockY=380;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }

        }
        if (blockManCreate().intersects(door)){
            if(blockY<=200) {
                blockY = 140;
                vely = 0;
                onGround = true;
            }else {
                blockY=230;
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
            if(blockY>220 && !(blockManCreate().intersects(door)||blockManCreate().intersects(p1)||blockManCreate().intersects(p2))) {
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


