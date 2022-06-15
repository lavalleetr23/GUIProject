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

    //Clouds
    private int cloud1X = 480;
    private int cloud2X = 680;
    private int cloud1Dir = -4;
    private int cloud2Dir = -4;
    private int cloudPuff1X = 480;
    private int cloudPuff2X = 520;
    private int cloudPuff3X = 560;
    private int cloudPuff4X = 680;
    private int cloudPuff5X = 720;
    private int cloudPuff6X = 760;


    //Block movement variables
    private int blockX = 25;
    private int blockY = 160;
    private double vely=0;
    private double velx=0;

    //Coin Stuff
    Color color = Color.yellow;
    Color color2 = Color.yellow;
    private int coinCount = 0;

    public Rectangle blockManCreate() {
        Rectangle blockMan = new Rectangle(blockX,blockY,60,60);
        return blockMan;
    }

    boolean onGround = true;
    Rectangle plank = new Rectangle(0,680,520,30);
    Rectangle p1 =new Rectangle(0,220,470,40);
    Rectangle p2 =new Rectangle(650,220,400,40);
    Rectangle cloud1 = new Rectangle(cloud1X,420,130,60);
    Rectangle cloud2 = new Rectangle(cloud2X,650,130,60);
    Rectangle coin1 = new Rectangle(900,80,50,50);
    Rectangle coin2 = new Rectangle(580,550,50,50);
    Rectangle doorHit = new Rectangle(40,640,10,10);


    public void paint(Graphics g){
        //background
        setBackground(Color.decode("#79c2f8"));

        //Ground
        g.setColor(Color.darkGray);
        //Mountain Left
        g.fillRect(0,220,370,700);
        g.fillRect(370,220,100,40);
        g.fillRect(350,240,100,40);
        g.fillRect(330,260,100,40);
        g.fillRect(310,280,100,40);
        g.fillRect(290,300,100,40);
        //Mountain Right
        g.fillRect(850,220,400,700);
        g.fillRect(650,220,160,40);
        g.fillRect(770,240,200,40);
        g.fillRect(790,260,200,40);
        g.fillRect(810,280,200,40);
        g.fillRect(830,300,200,40);


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
        g.fillOval(900,80,50,50);

        g.setColor(color2);
        g.fillOval(580,550,50,50);



        //Door
        g.setColor(Color.black);
        g.fillRect(20,600,100,80);
        g.fillArc(20,580,100,40,180,-180);

        //Hitbox for door
        g.setColor(Color.black);
        g.fillRect(40,640,10,10);

        //BlockMan

        g.setColor(Color.gray);
        g.fillRect(blockX,blockY,60,60);;

        //Cloud Platforms
        g.setColor(Color.white);

        //Top Cloud
        g.fillOval(cloudPuff1X,420,70,60);
        g.fillOval(cloudPuff2X,420,70,60);
        g.fillOval(cloudPuff3X,420,70,60);

        //Bottom Cloud
        g.fillOval(cloudPuff4X,650,70,60);
        g.fillOval(cloudPuff5X,650,70,60);
        g.fillOval(cloudPuff6X,650,70,60);

        //Wooden Platform
        g.setColor(Color.decode("#634312"));
        g.fillRect(0,680,520,30);
        g.fillRect(460,240,5,450);
        g.fillRect(40,240,5,450);
        g.setColor(Color.decode("#b3b1b6"));
        g.fillRect(35,230,15,20);
        g.fillRect(455,230,15,20);

        g.setColor(Color.BLACK);

        //Borders
        g.fillRect(0,0,1000,10);
        g.fillRect(0,0,10,800);
        g.fillRect(980,0,10,800);
        g.fillRect(0,780,1000,10);




        //Coin Counter
        g.drawString("Coins " + coinCount,900,50);
        g.drawString(blockX+", "+blockY,800,50);

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
        if(!OnGroundTest()){
            moveYTimer.start();
            gravityTimer.start();
        }

        cloud1X = cloud1X + cloud1Dir;
        cloud2X = cloud2X + cloud2Dir;
        cloudPuff1X = cloudPuff1X + cloud1Dir;
        cloudPuff2X = cloudPuff2X + cloud1Dir;
        cloudPuff3X = cloudPuff3X + cloud1Dir;
        cloudPuff4X = cloudPuff4X + cloud2Dir;
        cloudPuff5X = cloudPuff5X + cloud2Dir;
        cloudPuff6X = cloudPuff6X + cloud2Dir;

        cloud1.setLocation(cloud1X,420);
        cloud2.setLocation(cloud2X,650);
        if (cloudPuff3X > 770 || cloudPuff1X < 370){
            cloud1Dir*=-1;
        }
        if (cloudPuff6X > 770 || cloudPuff4X < 370){
            cloud2Dir*=-1;
        }
        if (blockX < 10){
            blockX = 10;
        }
        if (blockX > 990){
            blockX = 990;
        }
        if (blockManCreate().intersects(doorHit)){
            timer.stop();
            Main cL1 = new Main();
            cL1.Level3Clear(true);
        }
        //Collisions
        if (blockManCreate().intersects(p1)){
            if(blockY<=220) {
                blockY = 160;
                vely = 0;
                onGround = true;
            }else {
                blockY=260;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }

        }
        if (blockManCreate().intersects(p2)){
            if(blockY<=220) {
                blockY = 160;
                vely = 0;
                onGround = true;
            }else {
                blockY=260;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }

        }
        if (blockManCreate().intersects(plank)){
            if(blockY<=680) {
                blockY = 620;
                vely = 0;
                onGround = true;
            }else {
                blockY=710;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }
        }
        if (blockManCreate().intersects(cloud1)){
            blockX+=cloud1Dir;
            if(blockY<=420) {
                blockY = 370;
                vely = 0;
                onGround = true;
            }else {
                blockY=450;
                moveYTimer.start();
                gravityTimer.start();
                vely=-vely;
                onGround=false;
                gravityTimer.start();
            }
        }
        if (blockManCreate().intersects(cloud2)){
            blockX+=cloud2Dir;
            if(blockY<=650) {
                blockY = 600;
                vely = 0;
                onGround = true;
            }else {
                blockY=680;
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
                color = Color.decode("#79c2f8");
            }
        }
        if (blockManCreate().intersects(coin2)){
            if(color2==Color.yellow) {
                coinCount++;
                color2 = Color.decode("#79c2f8");
            }
        }

        if(blockY>700) {
        	blockX=25;
        	blockY=160;
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
        return blockManCreate().intersects(plank) || blockManCreate().intersects(p1) || blockManCreate().intersects(p2) || blockManCreate().intersects(cloud1) || blockManCreate().intersects(cloud2);
    }
    Timer moveYTimer = new Timer(10,moveY);
    Timer gravityTimer = new Timer(50,gravity);
    Timer moveXTimer = new Timer(10,moveX);
    Timer slowLeftTimer = new Timer(50,slowLeft);
    Timer slowRightTimer = new Timer(50,slowRight);


}


