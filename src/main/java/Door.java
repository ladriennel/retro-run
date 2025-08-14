import java.awt.*;

public class Door{

  private int x;
  private int y;
  private int w;
  private int h;
  private String color;

  /**
   * Constructs a Door with a specified number that assigns a x coordinate, 
   * y coordinate, width, height, and color.
   * 
   * @param i the door number
   */
  public Door(int i)
  {
    if(i == 1)
    {
      x = 550;
      y = 90;
      w = 10;
      h = 70;
      color = "#FFBF00";
    }
    else if(i == 2)
    {
      x = 720;
      y = 420;
      w = 10;
      h = 100;
      color = "#CC7722";
    }
    else if(i == 3)
    {
      x = 650;
      y = 0;
      w = 10;
      h = 90;
      color = "#702963";
    }
  }

  /**
   * Returns the x coordinate of the door
   * @return the x coordinate of the door
   */
  public int getX(){
    return x;
  }

  /**
   * Returns the y coordinate of the door
   * @return the y coordinate of the door
   */
  public int getY(){
    return y;
  }

  /**
   * Returns the height of the door
   * @return the height of the door
   */
  public int getHeight(){
    return h;
  }

  /**
   * Returns the width of the door
   * @return the width of the door
   */
  public int getWidth(){
    return w;
  }

  /**
   * Draws the door with the specified color, height, and width
   * at its x and y coordinate
   *
   * @param window the graphics window
   */
  public void draw(Graphics window){
    window.setColor(Color.decode(color));
    window.drawRect(x, y, w, h);
    window.fillRect(x, y, w, h);
    window.setColor(Color.white);
  }
}