package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogControlImage extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();

  public DialogControlImage(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogControlImage() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jLabel1.setText("File Name: ");
    jLabel2.setText("Color Mode: ");
    jLabel3.setText("Background Color: ");
    jLabel4.setText("Image Type: ");
    jLabel5.setText("Image Width: ");
    jLabel6.setText("Image Height: ");
    jLabel7.setText("Work Folder: ");
    jLabel8.setText("Image Result: ");
    getContentPane().add(panel1);
    panel1.add(jLabel1,    new XYConstraints(22, 21, 61, 27));
    panel1.add(jLabel2,   new XYConstraints(22, 55, 62, 20));
    panel1.add(jLabel3,   new XYConstraints(20, 90, 63, 25));
    panel1.add(jLabel4,  new XYConstraints(18, 125, 61, 22));
    panel1.add(jLabel5,   new XYConstraints(19, 157, 67, 23));
    panel1.add(jLabel6,  new XYConstraints(20, 195, 65, 26));
    panel1.add(jLabel7,  new XYConstraints(20, 232, 86, 20));
    panel1.add(jLabel8,  new XYConstraints(22, 264, 83, 24));
  }
}