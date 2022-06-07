package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame Level1 = new JFrame("BlockMan Adventures Level 1"); 
	static JFrame Start = new JFrame("BlockMan Adventures Launcher");
	static JFrame Level1Intermission = new JFrame();
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
	public void Clear(boolean clear) {
		if(clear) {
			Level1.setVisible(false);
			Level1Intermission.setSize(700,700);
			Level1Intermission.setVisible(true);
			JLabel Win = new JLabel("You Beat Level 1!");
			Level1Intermission.add(Win);
			Win.setBounds(250,50,200,100);
			Win.setFont(new Font("Serif", Font.BOLD, 50));
			Win.setVisible(true);
			JButton Again = new JButton("Play Again");
			Again.setBounds(200,500,100,100);
			Level1Intermission.add(Again);
			Again.setVisible(true);
			Again.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){  
					 		L1Create();
				         }  
			});
		}
	}
	public static void L1Create() {
        Level1.setSize(700,700);   
		 	Level1.setVisible(true);
	           Start.setVisible(false);
	           Level1Intermission.setVisible(false);
	           Level1Graphics L1 = new Level1Graphics();
	           Level1.add(L1);
	}
}
