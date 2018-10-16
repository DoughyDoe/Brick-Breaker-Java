import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Game extends JPanel 
	{
		boolean Loser = false;
 		boolean Winner = false;
	   static boolean right = false;
 	   static boolean left = false;
		protected JLabel Title, Score, HighScoreScore, HighScoreName, RightSide, JohnCenaGif;
      protected JButton RestartButton;
		protected JTextField NameField;
		protected Container container;
		protected Timer TimerBall, TimerPaddle;
		protected int Ballx, Bally, Batx, Baty, Brickx, Bricky, time, Width, Height, moveX, moveY, Count, BallWidth, BallHeight, BatWidth, BatHeight, BrickWidth, BrickHeight, ScoreText;
		protected String  ScoreName, Status;
		protected Rectangle Ball = new Rectangle(Ballx, Bally, 5, 5);
		protected Rectangle Bat = new Rectangle(Batx, Baty, 40, 5);
		protected Rectangle[] Brick = new Rectangle[12];
		public Game()
			{
				time=60;
				Ballx = 160;
   			Bally = 218;
            BallWidth = 5;
            BallHeight = 5;
 				Batx = 160;
 				Baty = 245;
            BatWidth = 40;
            BatHeight = 5;
 				Brickx = 70;
 				Bricky = 50;
				BrickWidth = 30;
				BrickHeight = 10;
				Width = 350;
				Height = 450;
				moveX = 3;
				moveY = 3;
				Count = 0;
            Status = null;
				Keys KEYCLICKER = new Keys();
            ActionHandler handy = new ActionHandler();
            TimerBall = new Timer(30, handy);
            TimerBall.start();
				addKeyListener(KEYCLICKER);
            setFocusable(true);
				
				for (int i = 0; i < Brick.length; i++) 
              {
                  Brick[i] = new Rectangle(Brickx, Bricky, 30, 10);
                     if (i == 5) 
                       {
                         Brickx = 70;
                         Bricky = 62;
                       }
                     if (i == 9) 
                       {
                         Brickx = 100;
                         Bricky = 74;
                       }
                         Brickx += 31;
              }
	    	
		}
 public void paint (Graphics g)
	{
	   super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
  		g.fillRect(0, 0, 350, 450);
  		g.setColor(Color.blue);
  		g.fillOval(Ballx, Bally, BallWidth, BallHeight);
  		g.setColor(Color.green);
  		g.fillRect(Batx, Baty, BatWidth, BatHeight);
  		g.setColor(Color.GRAY);
  		g.fillRect(0, 251, 450, 200);
  		g.setColor(Color.red);
  		g.drawRect(0, 0, 343, 250);
		for (int i = 0; i < Brick.length; i++) 
		 	{
   			if (Brick[i] != null)
               {
    					g.fillRect(Brick[i].x, Brick[i].y, Brick[i].width, Brick[i].height);
   				}	
  			}


  		if (Loser == true || Winner == true) 
			{
   			Font f = new Font("Arial", Font.BOLD, 20);
   			g.setFont(f);
   			g.drawString(Status, 90, 160);
   			Loser = false;
   			Winner = false;
  			}
		setSize(350,450);
 	}
	
private class ActionHandler implements ActionListener
   { 
  public void actionPerformed(ActionEvent e)
      {
			if(e.getSource()==TimerBall)
				{
					Ballx+= moveX;
					Bally+= moveY;
					if(Ballx <= 0 || Ballx >= Width - 5)
						{
							moveX = moveX * -1;
						}
					if(Bally <= 0 || Bally >= Height - 5)
						{
							moveY = moveY * -1;
						}
					if(Bally==Baty&&Ballx>=Batx&&Ballx<=(Batx+BatWidth))
						{
							moveY = moveY*-1;
						}
						 for (int i = 0; i < Brick.length; i++)
                     {
                        if (Brick[i] != null)
                           {
                              if (Bally>=Brick[i].y&&Bally<=(Brick[i].y+BrickHeight)&&Ballx>=Brick[i].x&&Ballx<=(Brick[i].x+BrickWidth)) 
                                 {
                                     Brick[i] = null;
                                     moveY = -moveY;
                                     Count++;
												 repaint();
                                 }
                           } 
                     }
                 if (Count == Brick.length) 
                     {
                         Winner = true;
                         Status = "Noice~";
                         repaint();
                         while (Winner == true);
                         {
                         TimerBall.stop();
                         TimerPaddle.stop();
                         }
                     }
						if (Bally >= 250) 
                     {
                         Loser = true;
                         Status = "Cry About It";
                         repaint();
                         while (Loser == true);
                         {
                         TimerBall.stop();
                         TimerPaddle.stop();
                         }

                     }


			repaint();
               try
                  {
               	if(ScoreText<Count)
							{
                        
								FileOutputStream fos = new FileOutputStream( "HighScore.txt", false );
				 				PrintWriter pw = new PrintWriter( fos );
								pw.print(Count);
								
								pw.close();
							}	
                    }
               catch(FileNotFoundException f)
                  {
                     System.out.println("File Not Found");
                  }			
			}
		}
	}
private class Keys implements KeyListener
{		
  public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				
				left = true;
				if(left == true)
				{
            Batx -= 5;
				repaint();
				}
            if (Batx <= 4) 
                     {
                         Batx = 4;
                     } 
           repaint();       
			}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
			{
   			right = true;
				if(right == true)
				{
				Batx += 5;
				repaint();
				}
				if (Batx >= 298) 
                     {
                         Batx = 298;
                     }
				repaint();
  			}
 	}

 public void keyReleased(KeyEvent e)
  {
  		
  		if (e.getKeyCode() == KeyEvent.VK_LEFT) 
  			{
   			left = false;
  			}

  		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
  			{
   			right = false;
  			}
 }
 public void keyTyped(KeyEvent NA) 
  {
  }

}					

}
		

