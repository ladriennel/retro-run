import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Game extends JPanel implements KeyListener, ActionListener {

  private BufferedImage back;
  
  private ImageIcon heart1;

  private Key key1;
  private Key key2;
  private Key key3;
  private boolean key1Taken;
  private boolean key2Taken;
  private boolean key3Taken;
  private boolean key1Used;
  private boolean key2Used;
  private boolean key3Used;

  private Door[] doors;
  
  private Character character;
  private Enemies enemies;
  private int enemyHeight = 17;
  private int enemyWidth = 16;
  
  private Queue<Sphere> spheres;
  private boolean[] keys;
  private Inventory inventory;
  private static final int LINE1 = 90;
  private static final int LINE2 = 160;
  private static final int LINE3 = 230;
  private static final int LINE4 = 290;
  private static final int LINE5 = 350;
  private static final int LINE6 = 420;
  
  private int totalHealth;
  private int jumpHeight;

  private Timer timer;		// For Thread Management
  private static final int UPDATE_INTERVAL = 10; // in ms

  public static JFrame gameFrame;
  
  public Game(String powerup, JFrame game){
    gameFrame = game;
    
    totalHealth = 3;
    jumpHeight = 75;
    inventory = new Inventory(250, 0, 300, 30);
    if(powerup == "High Jump")
    {
      inventory.add("HighJump.png");
    }
    else if(powerup == "Extra Heart")
    {
      totalHealth++;
    }
    else if(powerup == "Checkpoint")
    {
      inventory.add("CheckpointSet.png");
    }

    keys = new boolean[3]; //keyboard keys
    key1 = new Key(25, 185, 25, 40, 1);
    key2 = new Key(175, 45, 25, 40, 2);
    key3 = new Key(755, 435, 25, 40, 3);
    key1Taken = false;
    key2Taken = false;
    key3Taken = false;

    doors = new Door[3];
    doors[0] = new Door(1);
    doors[1] = new Door(2);
    doors[2] = new Door(3);
    
    //10,450
    character = new Character(10, 450, 25, 31, 2);
    character.setJump(jumpHeight);
    enemies = new Enemies();
    //add enemies here
    enemies.add(new Enemy(250, 461, enemyWidth, enemyHeight, 1, 170, 370));
    enemies.add(new Enemy(450, 461, enemyWidth, enemyHeight, 1, 420, 520));
    enemies.add(new Enemy(150, 401, enemyWidth, enemyHeight, 1, 115, 250));
    enemies.add(new Enemy(350, 401, enemyWidth, enemyHeight, 1, 300, 500));
    enemies.add(new Enemy(150, 271, enemyWidth, enemyHeight, 1, 150, 220));
    enemies.add(new Enemy(350, 271, enemyWidth, enemyHeight, 1, 375, 485));
    enemies.add(new Enemy(450, 141, enemyWidth, enemyHeight, 1, 350, 490));
    enemies.add(new Enemy(250, 141, enemyWidth, enemyHeight, 1, 100, 275));
    enemies.add(new Enemy(550, 71, enemyWidth, enemyHeight, 1, 515, 570));
    enemies.add(new Enemy(250, 71, enemyWidth, enemyHeight, 1, 130, 275));

    //moving Spheres
    spheres = new LinkedList<Sphere>();
    int tempX = 770;
    int tempR = 7;
    for(int i = 0; i < 15; i++){
      spheres.add(new Sphere(tempX, 350 - tempR, tempR, tempR, 1));
      tempX += 140;
      if(i<5){
        tempR +=3;
      }
    }
    this.addKeyListener(this);
    timer = new Timer(UPDATE_INTERVAL, this);
    timer.start();
    setFocusable(true);
    requestFocus();
  }

  public void paintComponent(Graphics window) {
    //window.drawRect(10,10,10,10);
  
    Graphics2D twoDGraph = (Graphics2D) window;

    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));
  
    Graphics graphToBack = back.createGraphics();

    ImageIcon background = new ImageIcon(getClass().getResource("/Background.png"));
    graphToBack.drawImage(background.getImage(),0,0,null);

    if(totalHealth == 0){
      switchToEndFrame("LOST");
    }
    if(win()){
      switchToEndFrame("WON");
    }
    
    heart1 = new ImageIcon(getClass().getResource("/Heart.png"));

    int healthX = 10;
    for(int i = 0; i < totalHealth; i++)
    {
      graphToBack.drawImage(heart1.getImage(), healthX, 10, 20, 20, null);
      healthX += 25;
    }
    
    graphToBack.setColor(Color.white);

    //Platforms
    //Height 70 between each line
    graphToBack.drawLine(0, LINE6, 600, LINE6);
    graphToBack.drawLine(700, LINE6, 800, LINE6);
    graphToBack.drawLine(75, LINE5, 800, LINE5);
    graphToBack.drawLine(125, LINE4, 250, LINE4);
    graphToBack.drawLine(350, LINE4, 500, LINE4);
    graphToBack.drawLine(600, LINE4, 700, LINE4);
    graphToBack.drawLine(0, LINE3, 75, LINE3);
    graphToBack.drawLine(250, LINE3, 350, LINE3);
    graphToBack.drawLine(725, LINE3, 800, LINE3);
    graphToBack.drawLine(0, LINE2, 700, LINE2);
    graphToBack.drawLine(0, LINE1, 50, LINE1);
    graphToBack.drawLine(100, LINE1, 300, LINE1);
    graphToBack.drawLine(450, LINE1, 800, LINE1);

    inventory.draw(graphToBack);
    enemies.drawEmAll(graphToBack);

    for(Door d: doors){
      if(d != null){
        d.draw(graphToBack);
      }
    }
    
    enemies.moveEmAll();
    if(character.isJumping()){
      character.goJump();
    }else if(!character.isOnPlatform()){
      character.fall();
    }
    character.draw(graphToBack);

    key1Collision();
    key2Collision();
    key3Collision();


    if(!key1Taken)
    {
      key1.draw(graphToBack);
    }
    else if(!key1Used)
    {
      inventory.add("Key.png");
      key1Used = true;
    }
    if(!key2Taken)
    {
      key2.draw(graphToBack);
    }
    else if(!key2Used)
    {
      inventory.add("Key2.png");
      key2Used = true;
    }
    if(!key3Taken)
    {
      key3.draw(graphToBack);
    }
    else if(!key3Used)
    {
      inventory.add("Key3.png");
      key3Used = true;
    }
    

    graphToBack.setColor(Color.white);
    boolean addSphere = false;
    for(Sphere s : spheres){
      s.move("LEFT");
      if(s.offScreen()){
        addSphere = true;
      }else{
        addSphere = false;
      }
    }
    if(addSphere){
      int tempX = 770;
      int tempR = 7;
      for(int i = 0; i < 15; i++){
        spheres.add(new Sphere(tempX, 350 - tempR, tempR, tempR, 1));
        tempX += 140;
        if(i<5){
          tempR +=3;
        }
      }
    }
    for(Sphere s : spheres){
      s.draw(graphToBack);
    }
    
    enemyCollision();
    sphereCollision();
    
    //last line
    twoDGraph.drawImage(back, null, 0, 0);
    //repaint();
  }
  
  public void actionPerformed(ActionEvent e) {
    if(keys[0]){
			character.move("LEFT");
		}else if(keys[1]){
			character.move("RIGHT");
		}else if(keys[2]){
			character.move("JUMP");
		}
		repaint();
  }

  public void keyPressed(KeyEvent e) {
   
    if (e.getKeyCode() == KeyEvent.VK_1) {
      inventory.use(1,character, null);
    }
    if (e.getKeyCode() == KeyEvent.VK_2) {
      inventory.use(2,character, doors);
    }
    if (e.getKeyCode() == KeyEvent.VK_3) {
      inventory.use(3,character, doors);
    }
    if (e.getKeyCode() == KeyEvent.VK_4) {
      inventory.use(4,character, doors);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = true;
      character.move("LEFT");
      character.faceLeft();
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = true;
      character.move("RIGHT");
      character.faceRight();
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE && character.isOnPlatform()) {
      keys[2] = true;
      character.move("JUMP");
    }
    
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[2] = false;
    }
   repaint();
  }

  public void keyTyped(KeyEvent e) {
    // no code needed here
  }


  public void key1Collision()
  {
    int charXLeft = character.getX();
    int charXRight = character.getX() + character.getWidth();
    int charYTop = character.getY();

    int key1XLeft = key1.getX();
    int key1XRight = key1.getX() + key1.getWidth();
    int key1YTop = key1.getY();

    if(((charXRight > key1XLeft) && (charXRight < key1XRight)) && ((charYTop > key1YTop) && (charYTop < key1YTop + key1.getHeight())))
    {
      key1Taken = true;
    }
    else if(((charXLeft > key1XLeft) && (charXLeft < key1XRight)) && ((charYTop > key1YTop) && (charYTop < key1YTop + key1.getHeight())))
    {
      key1Taken = true;
    }
  }
  
  public void key2Collision()
  {
    int charXLeft = character.getX();
    int charXRight = character.getX() + character.getWidth();
    int charYTop = character.getY();
    
    int key2XLeft = key2.getX();
    int key2XRight = key2.getX() + key2.getWidth();
    int key2YTop = key2.getY();
    
    if(((charXRight > key2XLeft) && (charXRight < key2XRight)) && ((charYTop > key2YTop) && (charYTop < key2YTop + key2.getHeight())))
    {
      key2Taken = true;
    }
    else if(((charXLeft > key2XLeft) && (charXLeft < key2XRight)) && ((charYTop > key2YTop) && (charYTop < key2YTop + key2.getHeight())))
    {
      key2Taken = true;
    }
  }
  
  public void key3Collision()
  {
    int charXLeft = character.getX();
    int charXRight = character.getX() + character.getWidth();
    int charYTop = character.getY();

    int key3XLeft = key3.getX();
    int key3XRight = key3.getX() + key3.getWidth();
    int key3YTop = key3.getY();

    if(((charXRight > key3XLeft) && (charXRight < key3XRight)) && ((charYTop > key3YTop) && (charYTop < key3YTop + key3.getHeight())))
    {
      key3Taken = true;
    }
    else if(((charXLeft > key3XLeft) && (charXLeft < key3XRight)) && ((charYTop > key3YTop) && (charYTop < key3YTop + key3.getHeight())))
    {
      key3Taken = true;
    }
  }

  public void enemyCollision()
  {
    int charXLeft = character.getX();
    int charXRight = character.getX() + character.getWidth();
    int charYTop = character.getY();
    int charYBottom = character.getY() + character.getHeight();

    for(int i = 0; i < enemies.size(); i++)
    {
      int enemyXLeft = enemies.get(i).getX();
      int enemyXRight = enemies.get(i).getX() + enemies.get(i).getWidth();
      int enemyYTop = enemies.get(i).getY();

      if(((charXRight > enemyXLeft) && (charXRight < enemyXRight)) && ((charYTop < enemyYTop) && (charYBottom > enemyYTop)))
      {
        totalHealth--;
        character.setX(character.getCheckX());
        character.setY(character.getCheckY());
      }
      else if(((charXLeft > enemyXLeft) && (charXLeft < enemyXRight)) && ((charYTop < enemyYTop) && (charYBottom > enemyYTop)))
      {
        totalHealth--;
        character.setX(character.getCheckX());
        character.setY(character.getCheckY());
      }
    }
  }

  public void sphereCollision()
  {
    Iterator<Sphere> test = spheres.iterator();
    
    int charXLeft = character.getX();
    int charXRight = character.getX() + character.getWidth();
    int charYTop = character.getY();
    int charYBottom = character.getY() + character.getHeight();

    while(test.hasNext())
    {
      Sphere ball = test.next();

      int ballXLeft = ball.getX();
      int ballXRight = ball.getX() + ball.getWidth();
      int ballYTop = ball.getY();

      if(((charXRight > ballXLeft) && (charXRight < ballXRight)) && ((charYTop < ballYTop) && (charYBottom > ballYTop)))
      {
        totalHealth--;
        character.setX(character.getCheckX());
        character.setY(character.getCheckY());
      }
      else if(((charXLeft > ballXLeft) && (charXLeft < ballXRight)) && ((charYTop < ballYTop) && (charYBottom > ballYTop)))
      {
        totalHealth--;
        character.setX(character.getCheckX());
        character.setY(character.getCheckY());
      }
    }
  }

  public boolean win(){
    if((character.getY() >= 0) && ((character.getY()+character.getHeight()) <= 90)){
      if(character.getX() > 700){
        return true;
      }
    }
    return false;
  }
  
  public void switchToEndFrame(String s)
  {
    
    gameFrame.setVisible(false);
    JFrame end = new EndFrame(s);
    end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    end.setVisible(true);
   
  }
}