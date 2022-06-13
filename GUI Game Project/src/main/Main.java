package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	static JFrame Start = new JFrame("BlockMan Adventures Launcher");
    static JFrame Level1 = new JFrame("BlockMan Adventures Level 1");
	static JFrame Level2 = new JFrame("BlockMan Adventures Level 2");
	static JFrame Level3 = new JFrame("BlockMan Adventures Level 2");

	public static void main(String[] args) {

		//Sets starting frame
		Start.setSize(700,700);
		Start.setVisible(true);

		//Sets the main page panel
		JPanel StartPanel = new JPanel();
		StartPanel.setBackground(Color.lightGray);
		StartPanel.setBounds(0,0,700,700);
		StartPanel.setVisible(true);
		Start.add(StartPanel);

		//Sets label for start page
		JLabel Title = new JLabel("BlockMan Adventures");
		StartPanel.add(Title);
		Title.setBounds(250, 50, 200, 100);
		Title.setFont(new Font("Serif", Font.BOLD, 50));
		Title.setVisible(true);

		//Creates and adds button
		JButton Play = new JButton("Play");
		Start.add(Play);
		Play.setBounds(300, 550, 100, 50);
		Play.setVisible(true);

		//Adds Action Listener to the Play button, opens new frame when clicked
		Play.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){  
				 		L1Create();
			         }  
		});


	}
	public void Level1Clear(boolean clear) {
		if(clear) {
			Level2.setSize(1000,700);
			Level2.setVisible(true);
			Level1.setVisible(false);
			Level2Graphics L2 = new Level2Graphics();
			Level2.add(L2);

		}
	}
	public void Level2Clear(boolean clear) {
		if(clear) {
			Level3.setSize(1000,800);
			Level3.setVisible(true);
			Level2.setVisible(false);
			Level3Graphics L3 = new Level3Graphics();
			Level3.add(L3);

		}
	}
	public static void L1Create() {
        Level1.setSize(700,700);
		Level1.setVisible(true);
		Start.setVisible(false);
		Level1Graphics L1 = new Level1Graphics();
		Level1.add(L1);
	}
}
