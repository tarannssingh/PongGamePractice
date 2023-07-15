// imports 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle
{
    //meant to differnetiate between paddle 1 and 2
    int ID;
    // Velocity of paddle
    int yVelocity;
    // SPeed of paddle
    int speed = 10;

    public Paddle(int xPos, int yPos, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        // Becuase this is a subclass of rectangle super class, we can call the super constructor here
        super (xPos, yPos, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.ID = id;
    }

    public void keyPressed(KeyEvent e)
    {
        switch(ID)
        {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
            if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch(ID)
        {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
            if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(0);
                    move();
                }
                break;
        }
    } 

    // WHAT IS THE POINT OF THIS ???????
    public void setYDirection(int yDirection)
    {
        yVelocity = yDirection;
    }
    public void move()
    {
        y = y + yVelocity;
    }
    public void draw(Graphics g)
    {
        if(ID == 1)
        {
            g.setColor(Color.blue);
        }
        else
        {
            g.setColor(Color.red);
        }
        // we do this, as for the super class rectangle, these are the constructors things
        g.fillRect(x, y, width, height);
        
    }
}