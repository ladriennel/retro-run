import java.awt.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.*;

public class Inventory{

  private int x;
  private int y;
  private int w;
  private int h;
  private HashMap<Integer,ImageIcon> map;

  public Inventory(int x1, int y1, int w1, int h1){
    x = x1;
    y = y1;
    w = w1;
    h = h1;
    map = new HashMap<Integer,ImageIcon>();
    map.put(1,null);
    map.put(2,null);
    map.put(3,null);
    map.put(4,null);
  }

  public void add(String s){
    if(s.equals("HighJump.png") || s.equals("CheckpointSet.png")){
      map.put(1,new ImageIcon(getClass().getResource("/" + s)));
    }
    else if(s.equals("Key.png")){
      map.put(2,new ImageIcon(getClass().getResource("/" + s)));
    }
    else if(s.equals("Key2.png")){
      map.put(3,new ImageIcon(getClass().getResource("/" + s)));
    }
    else if(s.equals("Key3.png")){
      map.put(4,new ImageIcon(getClass().getResource("/" + s)));
    }
  }

  public void use(int key, Character character, Door[] doors){
    if(key == 1){
      if(map.get(key)!=null && map.get(key).getDescription().equals("HighJump.png")){
        character.setJump(125);
      }else if(map.get(key)!=null && map.get(key).getDescription().equals("CheckpointSet.png")){
        character.setCheckX(character.getX());
        character.setCheckY(character.getY());
      }
      map.put(key,null);
    }else if(key == 2){
      if(character.door1Collision()){
        doors[0] = null;
        character.setDoorOff(0);
        map.put(key,null);
      }
    }else if(key == 3){
      if(character.door2Collision()){
        doors[1] = null;
        character.setDoorOff(1);
        map.put(key,null);
      }
    }else if(key == 4){
      if(character.door3Collision()){
        doors[2] = null;
        character.setDoorOff(2);
        map.put(key,null);
      }
    }

  }

  public void draw(Graphics window) {
    int middle = ((x + 5 + x + w - 5) / 2) - 2;
    int bye = ((((middle + x + 5) / 2) - 2) + x + 5) / 2;
    int tempX = bye - 12;
    
    window.fillRect(x, y, w, h);
    window.setColor(Color.black);
    window.fillRect(x, y, 5, h);
    window.fillRect(x + w - 5, y, 5, h);
    window.fillRect(middle, y, 5, h);
    window.fillRect(((middle + x + 5) / 2) - 2, y, 5, h);
    window.fillRect(((middle + x + w - 5) / 2) - 2, y, 5, h);
    window.fillRect(x, y + h - 5, w, 5);

    for(int i : map.keySet()){
      if(map.get(i) == null){
        tempX+=74;
        continue;
      }
      window.drawImage(map.get(i).getImage(), tempX, 3, 20, 20, null);
      tempX+=74;
    }
  }
  
}