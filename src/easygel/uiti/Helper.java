package easygel.uiti;

import javax.help.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class Helper {
  public  static  void  Helper(String str) {
    HelpSet helpSet;
    HelpBroker broker;
    helpSet=null;
    try{
      java.net.URL helpURL=new java.net.URL ("file:./help/EasyGelMap.hs");
      helpSet=new HelpSet(null,helpURL);
    }
    catch(Exception e2){
      System.out.println(e2.toString());
    }
    broker=helpSet.createHelpBroker();
    try{
      broker.setCurrentID(str);
    }
    catch(BadIDException e){
      broker.setCurrentID("AboutEasyGel");
    }
    broker.setDisplayed(true);
  }

  public static Cursor createHelpCursor(){
    String gifFile ="icon\\Help.gif";
    ImageIcon icon;
    Point point=new Point(6,2);
    icon = new ImageIcon(gifFile);
    int width = icon.getIconWidth();
    int height = icon.getIconHeight();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension d = toolkit.getBestCursorSize(width, height);
    Image bimage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
    bimage.getGraphics().drawImage(icon.getImage(), 0, 0, icon.getImageObserver());
    return toolkit.createCustomCursor(bimage, point, "");
  }
}