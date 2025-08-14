import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
   A frame that shows the main menu of Retro Run
*/
public class StartFrame extends JFrame
{
  private static final int FRAME_WIDTH = 800;
  private static final int FRAME_HEIGHT = 500;
  
  private JButton startButton;
  private JButton exitButton;
  private JPanel buttonPanel;
  private JLabel nameLabel;
  private JLabel blankLabel1;
  private JLabel blankLabel2;
  private JLabel blankLabel3;

  
  public StartFrame()
  {
    //Frame
    setTitle("Retro Run");
    //add(new JLabel(new ImageIcon("retro.jpg")), BorderLayout.CENTER);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    getContentPane().setBackground(new Color(108, 56, 67));  
    //Title
    //nameLabel = new JLabel("Retro Run", SwingConstants.CENTER);
    nameLabel = new JLabel(new ImageIcon(getClass().getResource("/name.png")));
    //nameLabel.setFont(new Font("Serif", Font.ITALIC, 60)); 
    //nameLabel.setForeground(Color.WHITE);
    
    //Space at the bottom
    blankLabel1 = new JLabel("   ");
    blankLabel1.setFont(new Font("Serif", Font.ITALIC, 40)); 
    add(blankLabel1, BorderLayout.SOUTH);
    blankLabel2 = new JLabel("      ");
    blankLabel2.setFont(new Font("Serif", Font.ITALIC, 110)); 
    add(blankLabel2, BorderLayout.EAST);
    blankLabel3 = new JLabel("      ");
    blankLabel3.setFont(new Font("Serif", Font.ITALIC, 110)); 
    add(blankLabel3, BorderLayout.WEST);

    //Buttons
    buttonPanel = new JPanel(new GridLayout(3, 4, 20, 20));
    startButton = new JButton("Start Game");
    startButton.setBackground(new Color(196, 136, 125));
    startButton.setForeground(Color.WHITE);
    startButton.setOpaque(true);
    startButton.setBorderPainted(false);
    startButton.setFocusPainted(false);
    startButton.setFont(new Font("Serif", Font.PLAIN, 40));
    exitButton = new JButton("Exit");
    exitButton.setBackground(new Color(196, 136, 125));
    exitButton.setForeground(Color.WHITE);
    exitButton.setOpaque(true);
    exitButton.setBorderPainted(false);
    exitButton.setFocusPainted(false);
    exitButton.setFont(new Font("Serif", Font.PLAIN, 40));

    //When start button is pressed, switches to the Menu
    startButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){switchToMenuFrame();}
    });
    
    //Closes the window
    exitButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){System.exit(0);}
    });

    //Adding to Panel
    buttonPanel.add(nameLabel);
    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);
    add(buttonPanel, BorderLayout.CENTER);
    buttonPanel.setBackground(new Color(108, 56, 67));
  }

  //Switches to MenuFrame
  public void switchToMenuFrame()
  {
    setVisible(false);
    JFrame menu = new MenuFrame();
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menu.setVisible(true);
  }
}