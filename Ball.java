// The imports.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle
{
    Random random;
    int xVelocity;
    int yVelocity;
    int intialSpeed = 2;
    

    public Ball(int xPos, int yPos, int width, int height)
    {
        super (xPos, yPos, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
        {
            // So rXD can only be -1 or 1 (which allows us to get a way of detmining a random direction).
            randomXDirection--;
        }
        setXDirection(randomXDirection * intialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
        {
            // So rXD can only be -1 or 1 (which allows us to get a way of detmining a random direction).
            randomYDirection--;
        }
        setYDirection(randomYDirection * intialSpeed);

    }

    public void setXDirection(int randomXDirection)
    {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection)
    {
        yVelocity = randomYDirection;
    }
    public void move()
    {
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}