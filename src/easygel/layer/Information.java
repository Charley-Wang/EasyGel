package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

import java.awt.*;

public class Information {
  public int ID;
  public boolean visible;
  public boolean isSelected;
  public int status;

  public int getID(){
   return ID;
  }
  public Information(){

  }
  //added by LiuYong
  public Information(int no){
    ID=no;
    visible=false;
    isSelected=false;
  }
  //added by LiuYong
  public Information(int no,boolean vis,boolean selected){
    ID=no;
    visible=vis;
    isSelected=selected;
  }

  public Color cloneColor(Color c){
    if(c==null) return null;
    Color newc=new Color(c.getRed(),c.getGreen(),c.getBlue());
    return newc;
  }
  public Point clonePoint(Point p){
    if(p==null) return null;
    Point newp=new Point(p.x,p.y);
    return newp;
  }
  public Rectangle closeRectangle(Rectangle rect){
    if(rect==null) return null;
    Rectangle newRect=new Rectangle(rect.getLocation().x,
                                    rect.getLocation().y,
                                    (int)rect.getWidth(),
                                    (int)rect.getHeight());
    return newRect;
  }

}
