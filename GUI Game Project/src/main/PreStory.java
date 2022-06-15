package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class PreStory extends Panel implements ActionListener, KeyListener{
    //Timer stuff
    private Timer timer;
    private int delay = 20;

    //Block movement variables
    private int blockX = 280;
    private int blockY = 540;
    private double vely;
    private double velx;

    //Coin Stuff
    Color color = Color.yellow;
    Color color2 = Color.yellow;
    private int coinCount = 0;

    //Clouds

    private int cloud1Dir = -2;
    private int cloud2Dir = -2;
    private int cloudPuff1X = 480;
    private int cloudPuff2X = 520;
    private int cloudPuff3X = 560;
    private int cloudPuff4X = 680;
    private int cloudPuff5X = 720;
    private int cloudPuff6X = 760;

    boolean onGround = true;

    public Rectangle blockManCreate() {
        Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
        return blockMan;
    }

    public void paint(Graphics g) {
        setBackground(Color.decode("#79c2f8"));

        g.setColor(Color.white);
        //Top Cloud
        g.fillOval(cloudPuff1X,150,70,60);
        g.fillOval(cloudPuff2X,150,70,60);
        g.fillOval(cloudPuff3X,150,70,60);

        //Bottom Cloud
        g.fillOval(cloudPuff4X,300,70,60);
        g.fillOval(cloudPuff5X,300,70,60);
        g.fillOval(cloudPuff6X,300,70,60);

        g.setColor(Color.decode("#69bc14"));
        g.fill3DRect(0,600,1000,440,true);
        g.setColor(Color.decode("#a65d11"));
        g.fillRect(0,640,1000,440);

        //House
        g.setColor(Color.decode("#d3d5ce"));
        g.fill3DRect(40,300,400,300,true);
        g.setColor(Color.decode("#744700"));
        g.fill3DRect(190,450,100,150,true);
        g.setColor(Color.decode("#b16c01"));
        g.fillRect(210,450,3,150);
        g.fillRect(230,450,3,150);
        g.fillRect(250,450,3,150);
        g.fillRect(270,450,3,150);
        g.setColor(Color.yellow);
        g.fillOval(200,525,20,20);
        g.setColor(Color.black);
        g.fillRect(75,380,100,100);
        g.fillRect(305,380,100,100);
        g.setColor(Color.blue);
        g.fillRect(80,385,90,90);
        g.fillRect(310,385,90,90);
        g.setColor(Color.decode("#990000"));
        int[] xvals = {40,140,340,440};
        int[] yvals = {300,200,200,300};
        g.fillPolygon(xvals,yvals,4);

        //BlockMan
        if(blockY<=540) {
            g.setColor(Color.gray);
            g.fill3DRect(blockX,blockY,60,60,true);
        }else {
            g.setColor(Color.gray);
            g.fill3DRect(blockX,540,60,60,true);
        }


    }








    public PreStory(){

        addKeyListener(this);
        timer = new Timer(delay,this);
        timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

        }

        //Increments right each second or something
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            slowRightTimer.stop();
            slowLeftTimer.stop();
            if(velx<3) {
                velx=3;
                moveXTimer.start();
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

    Timer moveYTimer = new Timer(10,moveY);
    Timer gravityTimer = new Timer(50,gravity);
    Timer moveXTimer = new Timer(10,moveX);
    Timer slowLeftTimer = new Timer(50,slowLeft);
    Timer slowRightTimer = new Timer(50,slowRight);

    @Override
    public void actionPerformed(ActionEvent e) {


        if (blockX < 10){
            blockX = 10;
        }
        if (blockX > 1020){
            Main cl1 = new Main();
            cl1.L1Create();
            timer.stop();
        }


        repaint();
    }
}


