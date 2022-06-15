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

    public void paint(Graphics g) {
        setBackground(Color.decode("#79c2f8"));

        g.setColor(Color.decode("#69bc14"));
        g.fill3DRect(0,600,1000,440,true);
        g.setColor(Color.decode("#a65d11"));
        g.fillRect(0,640,1000,440);

        //House
        g.setColor(Color.decode("#d3d5ce"));
        g.fill3DRect(40,300,400,300,true);
        g.setColor(Color.decode("#744700"));
        g.fill3DRect(190,450,100,150,true);

    }








    public PreStory(){

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


