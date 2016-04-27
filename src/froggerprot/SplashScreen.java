package froggerprot;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SplashScreen extends JPanel implements ActionListener
{
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    private JButton startButton, instructionButton;
    
    public SplashScreen()
    {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        startButton = new JButton("Start Game");
        startButton.setBounds(0,100,100,25);
        startButton.addActionListener(this);
        add(startButton);
        instructionButton = new JButton("Instructions");
        instructionButton.setBounds(0,200,100,25);
        instructionButton.addActionListener(this);
        add(instructionButton);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object o = ae.getSource();
        if(o == startButton)
        {
            MainScreen ms = new MainScreen();
            getParent().add(ms);
            setVisible(false);
            disable();
        }
        if(o == instructionButton)
        {
            JOptionPane.showMessageDialog(this, "Guide the Frog across the road"
                    + "\nAvoid moving cars"
                    + "\nIf you get hit you lose a life. You have 3 lives"
                    + "\nGet across the road and you move on to the next level"
                    + "\nEach level the speed of the cars increases");
        }
    }
}