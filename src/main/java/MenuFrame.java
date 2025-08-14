import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
   A frame that shows the main menu of Retro Run
*/
public class MenuFrame extends JFrame
{
  private static final int FRAME_WIDTH = 800;
  private static final int FRAME_HEIGHT = 500;

  private JPanel basePanel;
  private JPanel rightBasePanel;
  private JPanel puPanel;
  private JButton powerUp1;
  private JButton powerUp2;
  private JButton powerUp3;
  private JLabel instructions;
  private JLabel instructions2;

  private ImageIcon checkpoint = new ImageIcon(getClass().getResource("/CheckpointSet.png"));
  private ImageIcon extraHeart = new ImageIcon(getClass().getResource("/ExtraHeart.png"));
  private ImageIcon highJump = new ImageIcon(getClass().getResource("/HighJump.png"));

  
  public MenuFrame()
  {
    //Frame
    setTitle("Retro Run");
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    powerUp1 = new JButton("High Jump");
    powerUp1.setIcon(highJump);
    powerUp1.setBackground(new Color(32,128,125));
    powerUp1.setForeground(Color.WHITE);
    powerUp1.setOpaque(true);
    powerUp1.setBorderPainted(false);
    powerUp1.setFocusPainted(false);
    powerUp1.setFont(new Font("Serif", Font.PLAIN, 20));
    
    powerUp2 = new JButton("Extra Heart");
    powerUp2.setIcon(extraHeart);
    powerUp2.setBackground(new Color(32,128,125));
    powerUp2.setForeground(Color.WHITE);
    powerUp2.setOpaque(true);
    powerUp2.setBorderPainted(false);
    powerUp2.setFocusPainted(false);
    powerUp2.setFont(new Font("Serif", Font.PLAIN, 20));
    
    powerUp3 = new JButton("Checkpoint Setter");
    powerUp3.setIcon(checkpoint);
    powerUp3.setBackground(new Color(32,128,125));
    powerUp3.setForeground(Color.WHITE);
    powerUp3.setOpaque(true);
    powerUp3.setBorderPainted(false);
    powerUp3.setFocusPainted(false);
    powerUp3.setFont(new Font("Serif", Font.PLAIN, 20));

    puPanel = new JPanel(new GridLayout(3, 1, 20, 20));
    puPanel.add(powerUp1);
    puPanel.add(powerUp2);
    puPanel.add(powerUp3);
    puPanel.setBackground(new Color(29,76,100));

    

    instructions = new JLabel("Choose a Powerup to Begin!", SwingConstants.CENTER);
    instructions.setForeground(Color.WHITE);
    instructions.setFont(new Font("Serif", Font.PLAIN, 20));

    instructions2 = new JLabel(new ImageIcon(getClass().getResource("/howTo.png")));
    
    rightBasePanel = new JPanel(new GridLayout(3, 1, 10, 10));
    rightBasePanel.setBackground(new Color(29,76,100));
    rightBasePanel.add(instructions);
    rightBasePanel.add(puPanel);
    
    
    basePanel = new JPanel(new GridLayout(1, 2, 10, 10));
    basePanel.add(instructions2);
    basePanel.add(rightBasePanel);
    
    add(basePanel, BorderLayout.CENTER);
    basePanel.setBackground(new Color(108, 56, 67));

      powerUp1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){switchToGameFrame("High Jump");}
        });

        powerUp2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){switchToGameFrame("Extra Heart");}
        });

        powerUp3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){switchToGameFrame("Checkpoint");}
        });
    
  }
  
  public void switchToGameFrame(String powerup)
  {
    setVisible(false);
    JFrame game = new GameFrame(powerup);
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.setVisible(true);
  }
}