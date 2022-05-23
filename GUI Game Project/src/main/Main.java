package main;
import javax.swing.*;
import java.awt.*;
public class Main {
	public static void main(String[] args) {
		JFrame Start = new JFrame("BlockMan Adventures Launcher");
		Start.setSize(700,700);
		Start.setVisible(true);
		JPanel StartPanel = new JPanel();
		StartPanel.setBackground(Color.RED);
		StartPanel.setBounds(0,0,700,700);
		StartPanel.setVisible(true);
		Start.add(StartPanel);
	}
}
