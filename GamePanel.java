// imports 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable
{
    // Here are the basics of the game panel we will make
    // Static will make sure that every instance of GamePanel has it, and final makes it unchangeable
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
    Score score;


    public GamePanel()
    {
        //Make the ball and paddles
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    // this creates a new ball when called
    public void newBall()
    {
        random = new Random();
        // We also added random y placement
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER) + 1, BALL_DIAMETER, BALL_DIAMETER);

    }
    // both new ball and paddle are useful to reset the game
    public void newPaddles()
    {   // this is us setting up the paddles
        //Bascally just making rectangles and telling where they will be
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    // inorder to edit what GUI displays. "g" is in terms of the graphics of the panel I believe. 
    public void paint(Graphics g)
    {
        // from my understanding, we are creating this image
        // then we get the graphics of the image and assign it to graphics
        // then we pass that through draw
        // after that, we use the draw method to do what we want to the graphics
        // and then after that, we draw the full image onto our panel
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

        // this way of creating the vizualzes was to make it where there is no buffering. Contrary to the Snake game, where that was just rendered
        // using the panel's graphics, this way of doing it allows us to draw everything, in the background (I think), and then render it on top
        // of the image. This means that there is no odd rendering happening. It is rendered than added 
        // Putting in "this" as a paramter for drawImage() allows us to use the panal to monitor the image generation process
    }

    public void draw(Graphics G)
    {
        // We give G to these paddles to draw themselves on the pannel.
        paddle1.draw(G);
        paddle2.draw(G);
        ball.draw(G);
        score.draw(G);
    }

    public void move()
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    // NOTE: I made an error orgionally here where my if statments had ";" at the end of them. That makes the statment with the if statment just execute.
    public void checkCollision()
    {

        // Ball boundaries so it bounces off screen
        if (ball.y <= 0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= (GAME_HEIGHT - BALL_DIAMETER))
        {
            ball.setYDirection(-ball.yVelocity);
        }


        // Ball bounces off of the paddles.
        //Because ball and the paddles are subclasses of rectnagles, we can use the intersects method which will tell us if they touch
        if (ball.intersects(paddle1))
        {
            ball.xVelocity = Math.abs(ball.xVelocity);
            
            //The below are optional to make the game harder as turns progress 

            ball.xVelocity += 0.2;
            
            if (ball.yVelocity > 0)
            { 
                ball.yVelocity += 0.2;
            }
            else
            {
                ball.yVelocity -= 0.2;
            }
            // This updates the directions for the ball
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2))
        {
            ball.xVelocity = -Math.abs(ball.xVelocity);
            
            //The below are optional to make the game harder as turns progress 

            ball.xVelocity -= 0.2;
            
            if (ball.yVelocity > 0)
            { 
                ball.yVelocity += 0.2;
            }
            else
            {
                ball.yVelocity -= 0.2;
            }
            // This updates the directions for the ball
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }



        // This will stop the paddles at the window edges.
        if (paddle1.y <= 0)
        {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }
        if (paddle2.y <= 0)
        {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }



        // This will give a player one point and create new paddles and ball.
        if (ball.x <= 0 )
        {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println(score.player2);
        }
        if (ball.x >= (GAME_WIDTH - BALL_DIAMETER))
        {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println(score.player1);
        }    



    }

    public void run()
    {
        // The Game Loop. It makes it 60 FPS
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1)
            {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    // Key Actions to use
    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void KeyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}