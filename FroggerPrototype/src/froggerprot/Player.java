
package froggerprot;

import java.awt.event.KeyEvent;

public class Player extends Sprite
{
    int moveX;
    int moveY;
    int playerStride = 5;
    int lives;
    
    public Player(int xLocation, int yLocation, String imageLocation)
    {
        super(xLocation, yLocation, imageLocation);
        lives = 3;
    }
    
    public void move()
    {
        x = x + moveX;
        y = y + moveY;
        
        if(x < 1){x = 1;}
        if(y < 1){y = 1;}
    }
    
    public void keyPressed(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_UP)
        {
            moveY = -playerStride;
            moveX = 0;
        }
        if(key == KeyEvent.VK_DOWN)
        {
            moveY = playerStride;
            moveX = 0;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            moveX = -playerStride;
            moveY = 0;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            moveX = playerStride;
            moveY = 0;
        }
    }
    
    public void keyReleased(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_UP)
        {
            moveY = 0;
            moveX = 0;
        }
        if(key == KeyEvent.VK_DOWN)
        {
            moveY = 0;
            moveX = 0;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            moveX = 0;
            moveY = 0;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            moveX = 0;
            moveY = 0;
        }
    }
    
    public boolean checkCondition()
    {
        if(lives <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

