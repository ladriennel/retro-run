import java.awt.*;

/**
 * Represents a moving Enemy that can be drawn in a graphics window.
 */
public class Sphere extends MovingThing {

  /**
   * Constructs a Enemy with a location of (10,10), a width, height, and speed of 10
   */
  public Sphere() {
    this(10, 10, 10, 10, 10);
  }

  /**
   * Constructs a Enemy at a specified location with width, height, and speed of 10.
   * 
   * @param x the x location
   * @param y the y location
   */
  public Sphere(int x, int y) {
    this(x, y, 10, 10, 10);
  }

  /**
   * Constructs a Enemy at a specified location with a specified speed with a width
   * and height of 10.
   * 
   * @param x the x location
   * @param y the y location
   * @param s the speed
   */
  public Sphere(int x, int y, int s) {
    this(x, y, 10, 10, s);
  }

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
  public Sphere(int x, int y, int w, int h, int s) {
    super(x, y, w, h, s);

  }

  /**
   * Adjusts the x and y of the Enemy based on a specified direction, and the speed
   * at which the Enemy is moving. Enemys can move LEFT, RIGHT, UP or DOWN
   * 
   * @param direction the direction in which to move
   */
  public void move(String direction) {
    if(direction.equals("LEFT") && getX() > 0){
      if(getX() < 75 - getWidth() && getY() < 420 - getHeight())
      {
        setX(getX() - 1);
        setY(getY() + 2);
      }
      else
        setX(getX() - getSpeed());
    }
    else if(getX() <= 0)
    {
      setX(getX() - getSpeed());
      
    }
  }

  public boolean offScreen(){
    if(getX() <= 0){
      return true;
    }return false;
  }
  
  /**
   * Draws a Sphere in a specified Graphics window
   * 
   * @param window the Graphics window
   */
  public void draw(Graphics window) {
    window.fillOval(getX(), getY(), getWidth(), getHeight());
  }

}