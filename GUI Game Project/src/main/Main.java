package main;
import javax.swing.*;
import java.awt.*;
public class Main {
	public static void main(String[] args) {
		JFrame Start = new JFrame("BlockMan Adventures Launcher");
		Start.setSize(700,700);
		Start.setVisible(true);
		JPanel StartPanel = new JPanel();
		StartPanel.setBackground(Color.GREEN);
		StartPanel.setBounds(0,0,700,700);
		StartPanel.setVisible(true);
		Start.add(StartPanel);
		JLabel Title = new JLabel("BlockMan Adventures");
		StartPanel.add(Title);
		StartPanel.validate();
		Title.setBounds(250, 50, 200, 100);
		Title.setFont(new Font("Serif", Font.BOLD, 50));
		Title.setVisible(true);
		JButton Play = new JButton("Play");
		Start.add(Play);
		Play.setBounds(300, 550, 100, 50);
		Play.setVisible(true);
	}
}
