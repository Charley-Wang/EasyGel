package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;

public class DialogAbout extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JButton jButton1 = new JButton();
  private TitledBorder titledBorder1;
  private ImageIcon icon;
  private JLabel jLabel4 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder2;
  private XYLayout xYLayout2 = new XYLayout();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();

  public DialogAbout(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogAbout() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    String imageDir;
    String systemDir;
    systemDir=System.getProperties().getProperty("user.dir");
    imageDir=systemDir+"\\icon\\Protein5.jpg";
    icon=new ImageIcon(imageDir);
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jLabel1.setForeground(Color.blue);
    jLabel1.setText("Thank you for using EasyGels.");
    jLabel2.setForeground(Color.blue);
    jLabel2.setText("此软件为自主开发的专业分析软件");
    jLabel3.setForeground(Color.blue);
    jLabel3.setText("您将享受到我们的终生免费升级和咨询服务");
    jButton1.setText("OK");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jLabel4.setIcon(icon);
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jLabel5.setFont(new java.awt.Font("Serif", 3, 20));
    jLabel5.setForeground(new Color(255, 131, 0));
    jLabel5.setText("EasyGels");
    panel1.setMinimumSize(new Dimension(357, 196));
    panel1.setPreferredSize(new Dimension(357, 196));
    jLabel6.setForeground(Color.red);
    jLabel6.setText("版权所有，未经授权，禁止使用！");
    jLabel7.setForeground(Color.magenta);
    jLabel7.setToolTipText("");
    jLabel7.setText("公司网站：www.bio-delta.com.cn");
    jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jLabel7_mouseClicked(e);
      }
    });
    getContentPane().add(panel1);
    jPanel1.add(jLabel6,       new XYConstraints(113, 33, 185, 23));
    jPanel1.add(jLabel5,  new XYConstraints(154, 6, 87, 21));
    panel1.add(jLabel7,                                              new XYConstraints(43, 167, 189, 21));
    panel1.add(jButton1,                    new XYConstraints(253, 165, 63, 24));
    panel1.add(jLabel4,                 new XYConstraints(39, 13, 96, 66));
    panel1.add(jLabel1,               new XYConstraints(36, 78, 229, 37));
    panel1.add(jLabel3,               new XYConstraints(36, 117, 255, 39));
    panel1.add(jLabel2,               new XYConstraints(36, 96, 187, 39));
    panel1.add(jPanel1,               new XYConstraints(24, 8, 312, 150));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(new Dimension(357, 196));
    this.setLocation((screenSize.width - 357) / 2,
                      (screenSize.height -196) / 2);

    this.setVisible(true);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }

  void jLabel7_mouseClicked(MouseEvent e) {
  }
}