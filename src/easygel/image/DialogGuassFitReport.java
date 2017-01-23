package easygel.image;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class DialogGuassFitReport extends JDialog {
  private XYLayout xYLayout1 = new XYLayout();
  private Image image;
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout2 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  DialogGuass di;

  public DialogGuassFitReport(Frame frame, String title, boolean modal,DialogGuass di) {
    super(frame, title, modal);
    try {
      this.di=di;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void paint(Graphics g){
    super.paint(g);
    g.drawImage(image,0,0,this);
  }
  private void jbInit() throws Exception {
    String img=System.getProperties().getProperty("user.dir")+"\\icon\\fit.jpg";
    image=this.getToolkit().createImage(img);
    //"");
    jPanel1.setLayout(xYLayout2);
    jLabel1.setText("jLabel1");
    jLabel2.setText("jLabel2");
    jLabel3.setText("jLabel3");
    jLabel4.setText("jLabel4");
    jLabel1.setForeground(Color.magenta);
    jLabel2.setForeground(Color.magenta);
    jLabel3.setForeground(Color.magenta);
    jLabel4.setForeground(Color.magenta);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jLabel2,  new XYConstraints(14, 290, 288, 22));
    jPanel1.add(jLabel3,  new XYConstraints(14, 313, 277, 22));
    jPanel1.add(jLabel4, new XYConstraints(14, 335, 271, 22));
    jPanel1.add(jLabel1,          new XYConstraints(14, 268, 262, 22));

    this.jLabel1.setText("σ= "+ Double.toString(di.phi));
    this.jLabel2.setText("μ= "+ Double.toString(di.mu));
    this.jLabel3.setText("Before fitting = "+ Double.toString(di.oriGray));
    this.jLabel4.setText("After fitting = "+ Double.toString(di.newGray));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.dispose();
  }
}