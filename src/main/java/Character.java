import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Represents a moving Character that can be drawn in a graphics window.
 */
public class Character extends MovingThing {
  private Image image;
  
  private int jump;
  private int jumpHolder;
  private boolean jumping;
  private boolean goingDown;
  
  private int checkX;
  private int checkY;


  private boolean[] doorsOn;

  /**
   * Constructs a Character at a specified location with a specified
   * speed, width, and height.
   * 
   * @param x the x location
   * @param y the y location
   * @param w the width
   * @param h the height
   * @param s the speed
   */
  public Character(int x, int y, int w, int h, int s) {
    super(x, y, w, h, s);

    try {
      URL url = getClass().getResource("Character.png");
      ImageIcon icon = new ImageIcon(url);
      image = icon.getImage();
    } catch (Exception e) {
      // feel free to do something here
    }
    
    jump = 75; 
    jumpHolder = jump;
    jumping = false;
    goingDown = false;
    checkX = 10;
    checkY = 450;
    doorsOn = new boolean [3];
    doorsOn[0] = true;
    doorsOn[1] = true;
    doorsOn[2] = true;
  }

  /**
   * Adjusts the x and y of the Character based on a specified direction, and the speed
   * at which the Character is moving. Characters can move LEFT, RIGHT, UP or DOWN
   * 
   * @param direction the direction in which to move
   */
  public void move(String direction) {
    if(direction.equals("LEFT") && getX() > 0 && !door1Collision())
      setX(getX() - getSpeed());
    else if(direction.equals("RIGHT") && getX() < (800-getWidth()) && !door2Collision() && !door3Collision())
      setX(getX() + getSpeed());
    else if(direction.equals("JUMP"))
    {
      jumping = true;
    }
    
  }

  public void fall(){
    setY(getY() + 1);
  }

  public void setCheckX(int x){
    checkX = x;
  }

  public void setCheckY(int y){
    checkY = y;
  }

  public int getCheckX(){
    return checkX;
  }

  public int getCheckY(){
    return checkY;
  }
  
  public void setJump(int j){
    jump = j;
    jumpHolder = jump;
  }
  
  public boolean isJumping(){
    return jumping;
  }
  
  public void goJump(){
    if(isOnPlatform() && !goingDown)
    {
      setY(getY() - 1);
      jump--;
    }
    else//Character is in the air right now
    {
      if(jump == 0 || hitCeiling())
      {
        goingDown = true;
      }
      if(!goingDown)
      {
        setY(getY() - 1);
        jump--;
      }else if(goingDown){
        if(!isOnPlatform())
        {
          fall();
          jump++;
        }else{
          jump = jumpHolder;
          jumping = false;
          goingDown = false;
        }
      }
    }
  }

  //90, 160, 230, 290, 350, 420, 500
  //40, 110, 180, 240, 300, 370, 450
  //59, 129, 199, 259, 319, 389, 450
  public boolean isOnPlatform(){
    if(getY() == 59 && ((getX() <= 50 && getX() >= 0-getWidth()) || (getX() <= 300 && getX() >= 100-getWidth()) || (getX() <= 800 && getX() >= 450-getWidth()))){
      return true;
    }else if(getY() == 129 && (getX() <= 700 && getX() >= 0-getWidth() )){
      return true;
    }else if(getY() == 199 && ((getX() <= 75 && getX() >= 0-getWidth()) || (getX() <= 350 && getX() >= 250-getWidth()) || (getX() <= 800 && getX() >= 725-getWidth()))){
      return true;
    }else if(getY() == 259 && ((getX() <= 250 && getX() >= 125-getWidth()) || (getX() <= 500 && getX() >= 350-getWidth()) || (getX() <= 700 && getX() >= 600-getWidth()))){
      return true;
    }else if(getY() == 319 && (getX() <= 800 && getX() >= 75-getWidth())){
      return true;
    }else if(getY() == 389 && ((getX() <= 600 && getX() >= 0) || (getX() <= 800 && getX() >= 700-getWidth()))){
      return true;
    }else if(getY() == 450){
      return true;
    }
    
    return false; 
  }

  
  public boolean hitCeiling(){
    if(getY()==0){
      return true;
    }else if(getY() == 90 && ((getX() <= 50 && getX() >= 0-getWidth()) || (getX() <= 300 && getX() >= 100-getWidth()) || (getX() <= 800 && getX() >= 450-getWidth()))){   
      return true;
    }else if(getY() == 160 && (getX() <= 700 && getX() >= 0-getWidth() )){
      return true;
    }else if(getY() == 230 && ((getX() <= 75 && getX() >= 0-getWidth()) || (getX() <= 350 && getX() >= 250-getWidth()) || (getX() <= 800 && getX() >= 725-getWidth()))){
      return true;
    }else if(getY() == 290 && ((getX() <= 250 && getX() >= 125-getWidth()) || (getX() <= 500 && getX() >= 350-getWidth()) || (getX() <= 700 && getX() >= 600-getWidth()))){
      return true;
    }else if(getY() == 350 && (getX() <= 800 && getX() >= 75-getWidth())){
      return true;
    }else if(getY() == 420 && ((getX() <= 600 && getX() >= 0) || (getX() <= 800 && getX() >= 700-getWidth()))){
      return true;
    }
    return false;
  }

  public boolean door1Collision()
  { 
    if(!doorsOn[0]){
      return false;
    }
    if((getX() < 560) && (getY() >= 90) && ((getY()+getHeight()) <= 160)){
      return true;
    }
    return false;
  }

  public boolean door2Collision()
  {
    if(!doorsOn[1]){
      return false;
    }
    if((getX()+getWidth() > 720) && (getY() >= 420) && ((getY()+getHeight()) < 520)){
      return true;
    }
    return false;
  }

  public boolean door3Collision()
  {
    if(!doorsOn[2]){
      return false;
    }
    if((getX()+getWidth() > 650) && (getY() >= 0) && ((getY()+getHeight()) <= 90)){
      return true;
    }
    return false;
  }

  public void setDoorOff(int door){
    doorsOn[door] = false;
  }
  /**
   * Draws a Character in a specified Graphics window
   * 
   * @param window the Graphics window
   */
  public void draw(Graphics window) {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }

  public void faceLeft(){
    try {
      URL url = getClass().getResource("CharacterLeft.png");
      ImageIcon icon = new ImageIcon(url);
      image = icon.getImage();
    } catch (Exception e) {
      // feel free to do something here
    }
  }

  public void faceRight(){
    try {
      URL url = getClass().getResource("Character.png");
      ImageIcon icon = new ImageIcon(url);
      image = icon.getImage();
    } catch (Exception e) {
      // feel free to do something here
    }
  }
}