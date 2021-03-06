package froggerprot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    String highScore;
    JLabel levelLabel;
    JLabel livesLabel;
    JLabel scoreLabel;
    boolean gameOver = false;
       

    public MainScreen()
    { 
        setLayout(null);
        try (BufferedReader br = new BufferedReader(new FileReader("score.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                highScore = sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        setup();
        setFocusable(true);
        start(); 
    }
    
    public void setup()
    {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        
        ImageIcon ii = new ImageIcon(getClass().getResource("/froggerprot/frogger_game_board.jpeg"));
        background = ii.getImage();   
        goal = new Rectangle(0, 0, 800, 20);  
        player = new Player(400, 500, getClass().getResource("/froggerprot/chickenback.png"));
        
        levelLabel = new JLabel("Level: "+level);
        add(levelLabel);
        levelLabel.setBounds(0,0,100,75);
        levelLabel.setForeground(Color.white);
        
        livesLabel = new JLabel("Lives: "+lives);
        add(livesLabel);
        livesLabel.setBounds(101,0,100,75);
        livesLabel.setForeground(Color.white);
        
        scoreLabel = new JLabel("High Score: Level "+highScore);
        add(scoreLabel);
        scoreLabel.setBounds(610,0,300,75);
        scoreLabel.setForeground(Color.white);  
    }
    
    public void start()
    {
        lives = player.lives;
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
        
        int randGen = 1 + randomGenerator.nextInt(4);
        int randGen2 = 1 + randomGenerator.nextInt(4);
        
        int xLocation = -100;
        int yLocation = 0;
        
        switch (randGen)
        {
            case 1: randGen = 1;
                yLocation = 85;
                break;
            case 2: randGen = 2;
                yLocation = 145;
                break;
            case 3: randGen = 3;
                yLocation = 195;
                break;
            case 4: randGen = 4;
                yLocation = 255;
                break;
        }

        int y2Location = 1 + (int)(Math.random() * ((4 - 1) + 1));
        
        Sprite car = new Sprite((xLocation-randomGenerator.nextInt(50)) , 240 + (yLocation), getClass().getResource("/froggerprot/car.png"));
        Sprite boat = new Sprite((xLocation-randomGenerator.nextInt(50)) , (y2Location * 45), getClass().getResource("/froggerprot/sailboat.png"));
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
            
            JOptionPane.showMessageDialog(null, "You Won Level: " + level, "", 1);
            speed = speed+2;
            level++;
            
            t.stop();
            newGame();
            levelLabel.setText("Level: " +level);     
        }
    }
    
    public void newGame()
    {
       if (player.checkCondition()==false)
       {
          writeScore();
          JOptionPane.showMessageDialog(null, "Game Over!", "", 0); 
          System.exit(1);
       }
       else
       {
        start();    
       }
       player.buttonsPressed.clear();
    }
    
   public void writeScore()
    {
        BufferedWriter writer = null;
        try {
            File logFile = new File("score.txt");

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(String.valueOf(level));
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally {
            try 
            {             
                writer.close();
            } 
            catch (Exception e) {
            }
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
