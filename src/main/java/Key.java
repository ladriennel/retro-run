import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class Key{

  private Image image;
  private int x;
  private int y;
  private int w;
  private int h;
  
  /**
   * Constructs a Key at a specified location with a specified width, height, and image
   * 
   * @param x the x location
   * @param y the y location
   * @param w the width
   * @param h the height
   * @param key the key number
   */
  public Key(int x1, int y1, int w1, int h1, int key){
    x = x1;
    y = y1;
    w = w1;
    h = h1;
    if(key == 1)
    {
      try {
        URL url = getClass().getResource("Key.png");
        ImageIcon icon = new ImageIcon(url);
        image = icon.getImage();
      } catch (Exception e) {
        // feel free to do something here
      }
    }
    else if(key == 2)
    {
      try {
        URL url = getClass().getResource("Key2.png");
        ImageIcon icon = new ImageIcon(url);
        image = icon.getImage();
      } catch (Exception e) {
        // feel free to do something here
      }
    }
    else if(key == 3)
    {
      try {
        URL url = getClass().getResource("Key3.png");
        ImageIcon icon = new ImageIcon(url);
        image = icon.getImage();
      } catch (Exception e) {
        // feel free to do something here
      }
    }
  }

  /**
   * Gets the x-coordinate of the key.
   *
   * @return the x-coordinate of the key.
   */
  public int getX(){
    return x;
  }

  /**
   * Gets the y-coordinate of the key.
   *
   * @return the y-coordinate of the key.
   */
  public int getY(){
    return y;
  }

  /**
   * Gets the height of the key.
   *
   * @return the height of the key.
   */
  public int getHeight(){
    return h;
  }

  /**
   * Gets the width of the key.
   *
   * @return the width of the key.
   */
  public int getWidth(){
    return w;
  }

  /**
   * Draws a Enemy in a specified Graphics window
   * 
   * @param window the Graphics window
   */
  public void draw(Graphics window){
    window.drawImage(image, x, y, w, h, null);
  }
}