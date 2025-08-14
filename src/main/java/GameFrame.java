import java.awt.*;
import javax.swing.*;

/**
   A frame that shows the game of Retro Run
*/
public class GameFrame extends JFrame
{
  private static final int FRAME_WIDTH = 800;
  private static final int FRAME_HEIGHT = 480;


  public GameFrame(String powerup)
  {
    //Frame
    setTitle("Retro Run");
    Game g1 = new Game(powerup, this);
    g1.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    add(g1);
    pack();
    
  }
 
}