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
    private int blockY = 60;
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
    Rectangle door = new Rectangle(275,200,200,30);
    Rectangle p1 =new Rectangle(400,490,200,30);
    Rectangle p2 =new Rectangle(40,350,200,30);
    Rectangle coin1 = new Rectangle(120,290,50,50);
    Rectangle coin2 = new Rectangle(480,430,50,50);
    Rectangle doorHit = new Rectangle(375,160,10,10);

    public void paint(Graphics g){
        //background
        setBackground(Color.white);

        //Ground
        g.setColor(Color.darkGray);
        g.fillRect(0,100,200,700);





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
            Main cL1 = new Main();
            cL1.Level1Clear(true);
        }
        //Collisions
        if (blockManCreate().intersects(p1)){
            Main cl = new Main();
            cl.Level2Clear(true);
            if(blockX<=340&&blockY>490) {
                blockX=340;
                moveYTimer.start();
            }else if(blockX<=600&&blockX>590&&blockY>490) {
                blockX=600;
                moveYTimer.start();
            }else if(blockY<=490) {
                blockY = 430;
                vely = 0;
                onGround = true;
            }else {
                blockY=620;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely/2;
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


