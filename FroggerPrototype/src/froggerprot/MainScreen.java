
package froggerprot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

public class MainScreen extends JPanel implements ActionListener
{
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    ArrayList<Sprite> obstacles;
    Player player;
    Rectangle goal;
    Timer t = new Timer(30, this);
    Timer spawn = new Timer(1500, this);
    Image background;
    int speed=3;
    int level = 1;
    int lives = 3;
    JLabel levelLabel;
    JLabel livesLabel;
    
    
    
    
   
    

    
    
    
    public MainScreen()
    {
        
        setLayout(null);
        setup();
        setFocusable(true);
        start();
        
 
      
    }
    
    public void setup()
    {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        
        ImageIcon ii = new ImageIcon("src\\froggerprot\\frogger_game_board.jpeg");
        background = ii.getImage();   
        goal = new Rectangle(0, 0, 800, 20);  
        player = new Player(400, 500, "src\\froggerprot\\chickenback.png");
        
        levelLabel = new JLabel("Level: "+level);
        add(levelLabel);
        levelLabel.setBounds(0,0,100,75);
        levelLabel.setForeground(Color.white);
        
        livesLabel = new JLabel("Lives: "+lives);
        add(livesLabel);
        livesLabel.setBounds(101,0,100,75);
        livesLabel.setForeground(Color.white);
        
        
    }
    
    public void start()
    {
        
    lives = player.lives;
    level = player.level;
        
        
        player.x = 400;
        player.y = 500;
        obstacles = new ArrayList<Sprite>();    
        t.start();
        spawn.start();
        
        
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(background, 0, 0, 800, 600, this);
        g.drawImage(player.getImage(), player.x, player.y, player.width, player.height, this);
        g.fillRect(0, 0, 800, 20);
        for(int i = 0; i < obstacles.size(); i++)
        {
            g.drawImage(obstacles.get(i).getImage(), obstacles.get(i).x, obstacles.get(i).y, obstacles.get(i).width, obstacles.get(i).height, this);
        }
        
        Toolkit.getDefaultToolkit().sync();
        
       
    }
    
    private class TAdapter extends KeyAdapter
    {
        
        @Override
        public void keyReleased(KeyEvent ke)
        {
            player.keyReleased(ke);
        }
        
        @Override
        public void keyPressed(KeyEvent ke)
        {
            player.keyPressed(ke);
            player.processKeys();
        }
    }
    
    public void createEnemy()
    {
        Random randomGenerator = new Random();
        
        //int x1Location = -100 + (int)(Math.random());
        //int y1Location = (int)(Math.random());
        int xLocation = -100;
        int y1Location = 280 + randomGenerator.nextInt(240);
        
        //int x2Location = -100 + (int)(Math.random() * ((-100 - 0) + 1));
        int y2Location = 1 + (int)(Math.random() * ((4 - 1) + 1));
        
        Sprite car = new Sprite(xLocation , (y1Location), "src\\froggerprot\\car.png");
        Sprite boat = new Sprite(xLocation , (y2Location * 45), "src\\froggerprot\\sail boat.png");
        obstacles.add(car);
        obstacles.add(boat);
    }
    
    public void checkCollisions(Rectangle plr, Rectangle obs)
    {
        if(plr.intersects(obs))
        {
            player.lives =  player.lives - 1;
            JOptionPane.showMessageDialog(null, "You Lost! \nLives Remaining:" + player.lives, "", 0);
            t.stop();
            newGame();
            livesLabel.setText("Lives: "+player.lives);
         }
        if(plr.intersects(goal))
        {
            
            JOptionPane.showMessageDialog(null, "You Won Level: " + player.level, "", 1);
            speed = speed+2;
            player.level++;
            
            t.stop();
            newGame();
            levelLabel.setText("Level: " +player.level);
            
            
            
            
        }
    }
    
    public void newGame()
    {
       if (player.checkCondition()==false)
       {
          
          JOptionPane.showMessageDialog(null, "Game Over!", "", 0); 
          System.exit(1);
       }
       else
       {
        
        start();
       
       }
       
    }
    
   
    
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object o = ae.getSource();
        if(o == t)
        {
            for(int i = 0; i < obstacles.size(); i++)
            {
                obstacles.get(i).x = obstacles.get(i).x + speed;
                checkCollisions(player.checkBounds(), obstacles.get(i).checkBounds());
                 
       
                repaint();
    
            }
        }
        if(o == spawn)
        {
            createEnemy();
        }
    }
    
}
