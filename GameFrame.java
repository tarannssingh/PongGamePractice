// imports 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame
{
    //now we have a panel that we can do things on
    GamePanel panel;

    public GameFrame()
    {
        // ?? Why is panel no intialized on top
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();  
        // makes the window go to the middle      
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}