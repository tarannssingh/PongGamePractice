// imports 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import.swing.*;

public class GamePanel extends JPanel implements Runnable
{
    // Here are the basics of the game panel we will make
    // Static will make sure that every instance of GamePanel has i, and final makes it unchangeable
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5.0/9));
    // Taking the width and height, we will make a set dimension
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score socre;


    public GamePanel()
    {

    }

    // this creates a new ball when called
    public void newBall()
    {

    }
    // both new ball and paddle are useful to reset the game
    public void newPaddles()
    {

    }
    // inorder to edit what GUI displays
    public void paint(Graphics G)
    {

    }

    public void draw(Graphics G)
    {

    }

    public void move()
    {

    }

    public void checkCollision()
    {

    }

    public void run()
    {

    }
    // Key Actions to use
    public class AL extrends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {

        }

        public void KeyReleased(KeyEvent e)
        {

        }
    }

}