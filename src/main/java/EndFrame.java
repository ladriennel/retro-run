import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
   A frame that shows the end screen of Retro Run
*/
public class EndFrame extends JFrame
{
  private static final int FRAME_WIDTH = 800;
  private static final int FRAME_HEIGHT = 500;

  private JPanel base;
  private JPanel middlePanel;
  private JButton backButton;
  private JLabel message;

  public EndFrame(String m)
  {
    //Frame
    setTitle("Retro Run");
    //add(new JLabel(new ImageIcon("retro.jpg")), BorderLayout.CENTER);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    if(m.equals("LOST")){
      message = new JLabel("YOU LOST!", SwingConstants.CENTER);
    }else if(m.equals("WON")){
      message = new JLabel("YOU WIN!", SwingConstants.CENTER);
    }
    base = new JPanel(new GridLayout(2, 1, 0, 0));
    base.setBackground(new Color(108, 56, 67));
    middlePanel = new JPanel();
    middlePanel.setBackground((new Color(108, 56, 67)));
    
    message.setForeground(Color.WHITE);
    message.setFont(new Font("Serif", Font.PLAIN, 60));
    
    backButton = new JButton("Restart");
    backButton.setBackground(new Color(196, 136, 125));
    backButton.setForeground(Color.WHITE);
    backButton.setOpaque(true);
    backButton.setBorderPainted(false);
    backButton.setFocusPainted(false);
    backButton.setFont(new Font("Serif", Font.PLAIN, 40));
    middlePanel.add(backButton, BorderLayout.CENTER);
    base.add(message);
    base.add(middlePanel);
    add(base);

    backButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){switchToStartFrame();}
    });
  }

  public void switchToStartFrame()
  {
    setVisible(false);
    Main.start.setVisible(true);
  }
}