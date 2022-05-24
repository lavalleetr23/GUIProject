package main;
import java.awt.*;
import javax.swing.*;
public class Level1Graphics extends Canvas {
	public void paint(Graphics g) {
		for(int i=0;i<300;i++) {
			int x = (int) (Math.random()*600);
			int y = (int) (Math.random()*600);
			g.fillOval(x, y, 30, 30);
			if(x>0) {
				setForeground(Color.BLUE);
			}
			if(x>100) {
				setForeground(Color.MAGENTA);
			}
			if(x>300) {
				setForeground(Color.CYAN);
			}
			if(x>500) {
			setForeground(Color.GREEN);
			}
		}
	}
}
