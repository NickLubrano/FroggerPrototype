
package froggerprot;

import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

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
    
    public MainScreen()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        
        ImageIcon ii = new ImageIcon(/*"src\\froggerprot\\road.png"*/"images/frogger_game_board.png");
        background = ii.getImage();
        
        obstacles = new ArrayList<Sprite>();
        player = new Player(400, 500, "src\\froggerprot\\chickenback.png");
        goal = new Rectangle(0, 0, 800, 20);
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
            player.move();
            repaint();
        }
        
        @Override
        public void keyPressed(KeyEvent ke)
        {
            player.keyPressed(ke);
            player.move();
            repaint();
        }
    }
    
    public void createEnemy()
    {
        int xLocation = -100 + (int)(Math.random() * ((-100 - 0) + 1));
        int yLocation = 1 + (int)(Math.random() * ((4 - 1) + 1));
        
        Sprite car = new Sprite(xLocation , (yLocation * 90), "src\\froggerprot\\car.png");
        obstacles.add(car);
    }
    
    public void checkCollisions(Rectangle plr, Rectangle obs)
    {
        if(plr.intersects(obs))
        {
            //Consider reseting to the beginning of the level
            //Tell user he or she has been hit
            player.lives = player.lives - 1;
        }
        if(!player.checkCondition())
        {
            JOptionPane.showMessageDialog(null, "You Lost!", "", 1);
            t.stop();
        }
        if(plr.intersects(goal))
        {
            JOptionPane.showMessageDialog(null, "You Won!", "", 1);
            t.stop();
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
                obstacles.get(i).x = obstacles.get(i).x + 3;
                
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
