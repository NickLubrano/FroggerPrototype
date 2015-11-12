package froggerprot;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.*;

public class SplashScreen
{
	public SplashScreen()
	{
		JFrame frame = new JFrame("ChickenRunner.java");
			frame.setResizable(true);//false);
                        frame.setVisible(true);

		JLabel L = new JLabel("");
			JLabel diff = new JLabel();
			JLabel diff2 = new JLabel();
			JLabel diff3 = new JLabel();
			JPanel P = new JPanel();
			P.setLayout(new BorderLayout() );
			JPanel subPanel = new JPanel();
			JPanel bottomPanel = new JPanel();
             
			ImageIcon icon = new ImageIcon("\\\\up.ist.local\\Users\\ncp5088\\Desktop\\ctd.png"); //these will hold the button images
			ImageIcon medIcon = new ImageIcon("\\\\up.ist.local\\Users\\ncp5088\\Desktop\\start.png");
			ImageIcon crdIcon = new ImageIcon("\\\\up.ist.local\\Users\\ncp5088\\Desktop\\crdButt.png");
			ImageIcon ldbIcon = new ImageIcon("\\\\up.ist.local\\Users\\ncp5088\\Desktop\\ldbButt.png");
						Scanner scanner = new Scanner(System.in);

				JButton buttonName = new JButton("Show Input Dialog Box"); //prompt for User name
				buttonName.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					String str = JOptionPane.showInputDialog(null, "Please enter your name:", "NAME", 1);
					String name = "";//This variable will hold the user's name
				if(str != null)
				{JOptionPane.showMessageDialog(null, "Hello " + str, "NAME", 1);
				name = str;}
				else
				{JOptionPane.showMessageDialog(null, "You pressed cancel button. \nDefault name = Dr.Pepper", "NAME", 1); //default name on cancel
				name = "Dr.Pepper";}
                                                        }
                                                            });         

			JButton buttonTwo = new JButton(); // construct a JButton
			buttonTwo.setPreferredSize(new Dimension(200, 99));
			buttonTwo.setIcon(medIcon);
			subPanel.add(buttonTwo);
			JButton buttonFour = new JButton("Show Input Dialog Box");
			buttonFour.setPreferredSize(new Dimension(90, 31));
			buttonFour.setIcon(crdIcon);
			bottomPanel.add(buttonFour);
			JButton buttonFive = new JButton();
			buttonFive.setPreferredSize(new Dimension(107, 31));
			buttonFive.setIcon(ldbIcon);
			bottomPanel.add(buttonFive);
	
				buttonFour.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null, "Creators:\n\nNevin Pathiyil\nArden Lane\nTakuya Tokunaga", "CREDITS",1); //this will display credits
                                                        }});

             //code for leaderboard goes here
             
			P.add(L, BorderLayout.PAGE_START); 
			P.add(subPanel, BorderLayout.CENTER);
			P.add(bottomPanel, BorderLayout.PAGE_END);
	}
}