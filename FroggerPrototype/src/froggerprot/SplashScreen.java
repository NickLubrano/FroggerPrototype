package froggerprot;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.*;

public class SplashScreen extends JPanel implements ActionListener
{
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    private JButton startButton;
    
    public SplashScreen()
    {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        startButton = new JButton("Start Game");
        startButton.setBounds(0,0,100,25);
        startButton.addActionListener(this);
        this.add(startButton);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object o = ae.getSource();
        if(o == startButton)
        {
            MainScreen ms = new MainScreen();
            this.getParent().add(ms);
            this.setVisible(false);
            this.disable();
        }
    }
}