
package froggerprot;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.net.URL;

public class Player extends Sprite
{
    int lives;
    
    public Player(int xLocation, int yLocation, URL imageLocation)
    {
        super(xLocation, yLocation, imageLocation);
        lives = 3;
    }
    
    ArrayList<Integer> buttonsPressed = new ArrayList<Integer>();
    
    public void keyPressed(KeyEvent e)
    {
        if(!buttonsPressed.contains(e.getKeyCode()))
        {
            buttonsPressed.add(e.getKeyCode());
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        Integer releaseKeyCode = new Integer(e.getKeyCode());
        buttonsPressed.remove(releaseKeyCode);
    }
    
    public void processKeys()
    {
        if (buttonsPressed.contains(KeyEvent.VK_UP)) {moveUp();}
        if (buttonsPressed.contains(KeyEvent.VK_DOWN)) {moveDown();}
        if (buttonsPressed.contains(KeyEvent.VK_RIGHT)) {moveRight();}
        if (buttonsPressed.contains(KeyEvent.VK_LEFT)) {moveLeft();}
    }
        
    public void moveUp()    {y-=5;}
    public void moveDown()  {y+=5;}    
    public void moveRight() {x+=5;}
    public void moveLeft()  {x-=5;}
    
    public boolean checkCondition()
    {
        if(lives <= 0) {return false;}
        else {return true;}
    }
}