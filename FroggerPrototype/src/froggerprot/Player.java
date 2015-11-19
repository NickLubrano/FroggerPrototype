
package froggerprot;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.*;

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
    
    LinkedList buttonsPressed = new LinkedList();
    
    public void keyPressed(KeyEvent e)
    {
        buttonsPressed.add(e);
//        int key = ke.getKeyCode();
//        
//        if(key == KeyEvent.VK_UP)
//        {
//            moveY = -playerStride;
//            moveX = 0;
//        }
//        if(key == KeyEvent.VK_DOWN)
//        {
//            moveY = playerStride;
//            moveX = 0;
//        }
//        if(key == KeyEvent.VK_LEFT)
//        {
//            moveX = -playerStride;
//            moveY = 0;
//        }
//        if(key == KeyEvent.VK_RIGHT)
//        {
//            moveX = playerStride;
//            moveY = 0;
//        }
//        if(key == KeyEvent.VK_UP && key == KeyEvent.VK_LEFT)
//        {
//            moveY = -playerStride;
//            moveX = -playerStride;
//        }
//        if(key == KeyEvent.VK_UP && key == KeyEvent.VK_RIGHT)
//        {
//            moveY = -playerStride;
//            moveX = playerStride;
//        }
//        if(key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT)
//        {
//            moveY = playerStride;
//            moveX = -playerStride;
//        }
//        if(key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT)
//        {
//            moveY = playerStride;
//            moveX = playerStride;
//        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        if(buttonsPressed.contains(e))
            buttonsPressed.remove(e);
//        int key = ke.getKeyCode();
//        
//        if(key == KeyEvent.VK_UP)
//        {
//            moveY = 0;
//            moveX = 0;
//        }
//        if(key == KeyEvent.VK_DOWN)
//        {
//            moveY = 0;
//            moveX = 0;
//        }
//        if(key == KeyEvent.VK_LEFT)
//        {
//            moveX = 0;
//            moveY = 0;
//        }
//        if(key == KeyEvent.VK_RIGHT)
//        {
//            moveX = 0;
//            moveY = 0;
//        }
    }
    
    public void processKeys()
    {
        for (Iterator i = buttonsPressed.iterator(); i.hasNext();)
        {
            int code = ((KeyEvent)i.next()).getKeyCode();
            
            if (code == KeyEvent.VK_UP)
                moveUp();
            else if (code == KeyEvent.VK_DOWN)
                moveDown();
            else if (code == KeyEvent.VK_RIGHT)
                moveRight();
            else if (code == KeyEvent.VK_LEFT)
                moveLeft();
            else
                ;
        }
    }
        
    public void moveUp()
    {
        moveY = -playerStride;
        moveX = 0;
        
        //x = x + moveX;
        y = y + moveY;
        
        //if(x < 1){x = 1;}
        //if(y < 1){y = 1;}
    }
    
    public void moveDown()
    {
        moveY = playerStride;
        moveX = 0;
        
        //x = x + moveX;
        y = y + moveY;
        
        //if(x < 1){x = 1;}
        //if(y < 1){y = 1;}
    }
    
    public void moveRight()
    {
        moveX = playerStride;
        moveY = 0;
        
        x = x + moveX;
        //y = y + moveY;
        
        //if(x < 1){x = 1;}
        //if(y < 1){y = 1;}
    }
    
    public void moveLeft()
    {
        moveX = -playerStride;
        moveY = 0;
        
        x = x + moveX;
        //y = y + moveY;
        
        //if(x < 1){x = 1;}
        //if(y < 1){y = 1;}
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