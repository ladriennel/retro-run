import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Enemies represents a group of Enemy
 */
public class Enemies {
  private List<Enemy> enemies;
 
  /**
   * Constructs an Enemies collection
   */
  public Enemies() {
    enemies = new ArrayList<Enemy>();
    
  }

  /**
   * Adds an enemy to Enemies
   * 
   * @param en the enemy
   */
  public void add(Enemy en) {
      enemies.add(en);
  }

  /**
   * Gets the enemy at a specified location
   *
   * @param i the index of the enemy in Enemies
   * @return the enemy at index i
   */
  public Enemy get(int i){
    return enemies.get(i);
  }

  /**
   * Returns the size of Enemies
   * 
   * @return the size of Enemies
   */
  public int size()
  {
    return enemies.size();
  }

  /**
   * Removes the enemy at a specified location
   * 
   * @param i the index of the enemy in Enemies
   */
  public void remove(int i)
  {
    enemies.remove(i);
  }

  /**
   * Draws all of the Enemies
   * 
   * @param window the graphics window
   */
  public void drawEmAll(Graphics window) {
    for(Enemy x: enemies)
      x.draw(window);
  }

  /**
   * Moves all the enemies
   */
  public void moveEmAll() {
    for(Enemy x: enemies)
    {
      if(x.getX() >= x.getRightX() && x.getRight())
      {
        x.setLeft(true);
        x.setRight(false);
      }
      else if(x.getX() + x.getWidth() <= x.getLeftX() && x.getLeft())
      {
        x.setLeft(false);
        x.setRight(true);
      }
      if(x.getLeft()){
        x.move("LEFT");
      }else{
        x.move("RIGHT");
      }
    }
  }

  /**
   * Returns a String version of Enemies 
   * NOTE: just use the ArrayList toString
   */
  public String toString() {
    return "";
  }
}