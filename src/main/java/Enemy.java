import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Represents a moving Enemy that can be drawn in a graphics window.
 */
public class Enemy extends MovingThing {
  private Image image;
  private int leftX;
  private int rightX;
  private boolean left;
  private boolean right;

  /**
   * Constructs a Enemy at a specified location with a specified speed, width and
   * height.
   * 
   * @param x the x location
   * @param y the y location
   * @param w the width
   * @param h the height
   * @param s the speed
   */
  public Enemy(int x, int y, int w, int h, int s, int x1, int x2) {
    super(x, y, w, h, s);
    leftX = x1;
    rightX = x2;
    try {
      URL url = getClass().getResource("Enemy.gif");
      ImageIcon icon = new ImageIcon(url);
      image = icon.getImage();
    } catch (Exception e) {
      // feel free to do something here
    }
    int rand = (int)(Math.random() * 2)+ 1;
    if(rand == 1){
      left = true;
      right = false;
    }else{
      left = false;
      right = true;
    }
  }

  /**
   * Adjusts the x and y of the Enemy based on a specified direction, and the speed
   * at which the Enemy is moving. Enemys can move LEFT, RIGHT, UP or DOWN
   * 
   * @param direction the direction in which to move
   */
  public void move(String direction) {
    if(direction.equals("LEFT"))
      setX(getX() - getSpeed());
    else if(direction.equals("RIGHT"))
      setX(getX() + getSpeed());
    
  }

  /**
   * Gets the left x-coordinate.
   *
   * @return The left x-coordinate.
   */
  public int getLeftX()
  {
    return leftX;
  }
  
  /**
   * Gets the right x-coordinate.
   *
   * @return The right x-coordinate.
   */
  public int getRightX()
  {
    return rightX;
  }
  
  public boolean getRight()
  {
    return right;
  }
  
  public boolean getLeft()
  {
    return left;
  }
  
  public void setRight(boolean b)
  {
    right = b;
  }
  
  public void setLeft(boolean b)
  {
    left = b;
  }
  
  /**
   * Draws a Enemy in a specified Graphics window
   * 
   * @param window the Graphics window
   */
  public void draw(Graphics window) {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }

}