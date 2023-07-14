// imports 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import.swing.*;

public class GameFrame extends JFrame
{
    //now we have a panel that we can do things on
    GamePanel panel;

    public GameFrame()
    {
        // ?? Why is panel no intialized on top
        panel = new GamePanel();
        this.add(Panel);
        this.setTtitle("Pong Game");
        this.SetResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIST_ON_CLOSE);
        this.pack()
        this.setVisable(True);

    }
}