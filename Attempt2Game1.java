import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
//fix the key listerner with anybody ANYBODY
public class Attempt2Game1 extends JFrame
	{
	 	boolean Loser = false;
 		boolean Winner = false;
	   static boolean right = false;
 	   static boolean left = false;
		private JFrame jf;
		private JPanel jp;
		private JLabel Title, Score, HighScoreScore, HighScoreName, BottomHalf, JohnCenaGif;
      private JButton RestartButton;
		private JTextField NameField;
		private Container container;
		private Timer TimerBall, TimerPaddle;
		static int Ballx, Bally, Batx, Baty, Brickx, Bricky, time, Width, Height, moveX, moveY, Count, BallWidth, BallHeight, BatWidth, BatHeight, BrickWidth, BrickHeight, ScoreText;
		String  ScoreName, Status;
		Rectangle Ball = new Rectangle(Ballx, Bally, 5, 5);
		Rectangle Bat = new Rectangle(Batx, Baty, 40, 5);
		Rectangle[] Brick = new Rectangle[12];
		
public Attempt2Game1()
      { 		
				super("Brick Breaker Rip Off");
				JFrame jf = new JFrame();
				JPanel jp = new JPanel();
				
				jp.setLayout( new BorderLayout());
				jf.setSize(350,600);
				Game game = new Game();
				jp.add(game);
            Scanner scan = new Scanner(System.in);
				System.out.println(Count);
				try 
					{
		  				Scanner readFile = new Scanner (new File("HighScore.txt"));
						while(readFile.hasNext())
						{
						ScoreText = readFile.nextInt();
						}

						
					}
				
				catch(FileNotFoundException e)
    				{
						System.out.println("File Not Found");
    				}
				
            container = getContentPane();
	         container.setLayout( new BorderLayout());


				JPanel BottomHalf = new JPanel(new GridLayout(4,1));
				Score = new JLabel ("HighScore");
				HighScoreScore = new JLabel (ScoreText+"");
				BottomHalf.add(Score);
				BottomHalf.add(HighScoreScore);

            
				
				jp.add(BottomHalf,BorderLayout.SOUTH);
				jp.setVisible(true);
				
				jf.add(jp);
				jf.setResizable(false);
            jf.setVisible(true);
				

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


	

public static void main(String args[]) 
      {
		
        Attempt2Game1 AG1 = new Attempt2Game1();
        AG1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        catch(FileNotFoundException e)
        {
         System.out.println("File Not Found");
        }
      }
   

	}