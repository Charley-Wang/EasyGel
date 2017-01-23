package easygel;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import easygel.uiti.*;
import easygel.file.*;
import com.borland.dx.sql.dataset.*;
import com.borland.dx.dataset.*;
import java.io.*;
import javax.swing.border.*;
import javax.sound.sampled.*;
import easygel.setting.*;
import java.lang.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class FrameInit extends JFrame{
  private Database database = new Database();
  private User user=new User();
  private String systemDir;
  private ImageIcon icon;
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabelBackground = new JLabel();
  private JPasswordField jTextPassword = new JPasswordField();
  private JTextField jTextName = new JTextField();

  private EasyGelSet easySet;

  public FrameInit(EasyGelSet easySet) {
    super("Welcome to EasyGel !");
    this.easySet=easySet;
    easygel.uiti.WxsUiti.setIcon(this);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    WxsUiti.centerFrame(this,485,380);
    jPanel1.setLayout(xYLayout1);
    jTextPassword.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextPassword_keyPressed(e);
      }
    });
    jTextName.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextName_keyPressed(e);
      }
    });

    jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jPanel1_mouseClicked(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jTextName,  new XYConstraints(380, 273, 85, 21));
    jPanel1.add(jTextPassword,    new XYConstraints(381, 312, 83, 21));
    jPanel1.add(jLabelBackground,        new XYConstraints(-2, 0, 493, 363));

    String imageDir;
    systemDir=System.getProperties().getProperty("user.dir");
    imageDir=systemDir+"\\icon\\EasyGel.jpg";
    //imageDir="F:\\EasyGel\\EasyGel\\icon\\EasyGel.jpg";
    icon=new ImageIcon(imageDir);
    jLabelBackground.setText("");
    jLabelBackground.setIcon(icon);

    jTextPassword.setEchoChar('*');
    jTextPassword.setHorizontalAlignment(SwingConstants.LEFT);
    jTextPassword.setPreferredSize(new Dimension(5, 25));
    jTextPassword.setBorder(BorderFactory.createLoweredBevelBorder());
    jTextPassword.setFont(new Font("Times New Roman",Font.BOLD ,16));

    jTextName.setMargin(new Insets(0, 0, 0, 0));
    jTextName.setFont(new Font("Times New Roman",Font.BOLD ,16));
    jTextName.setBorder(BorderFactory.createLoweredBevelBorder());

    this.playMusic();

  }

  private void playMusic(){

  }

  void jButtonReg_actionPerformed(ActionEvent e) {
    login(false);
  }

  void jTextName_keyPressed(KeyEvent e) {
    if(e.getKeyChar() =='\n') jTextPassword.requestFocus();
  }

  void jTextPassword_keyPressed(KeyEvent e) {
    if(e.getKeyCode() =='\n')  login(false);
  }

  private  void login(boolean isSimple){
    if(isSimple==true){
      user.userName ="";
      user.rootWorkDir ="";
      user.hira =0;
      this.runFrameMain() ;
      this.dispose();
      return;
    }
    try{
      Robot robot=new Robot();
      robot.keyPress(KeyEvent.VK_F5);
    }
    catch(AWTException e2){
      e2.printStackTrace();
    }
    String name,password;
    name=this.jTextName.getText().trim() ;
    password=this.jTextPassword.getText().trim();
    if(name.length() ==0 && password.length() ==0){
      JOptionPane myOptionPane=new JOptionPane();
      Object[] options = { "Guest", "User","Exit" };
      int  selectOption;
      selectOption=myOptionPane.showOptionDialog(null, "Will you use the system as a guest?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE , null, options, options[0]);
      if(selectOption==0){
        user.userName ="";
        user.rootWorkDir ="";
        user.hira =0;
        this.runFrameMain() ;
      }
      else if(selectOption==1){
      }
      else{
          System.exit(0);
      }
    }
    else{
      name.toLowerCase();
      password.toLowerCase();
      database.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor(
          "jdbc:borland:dslocal:"+systemDir+"\\database\\EasyGel.jds",
          "riceyi", "riceyi", false, "com.borland.datastore.jdbc.DataStoreDriver"));
      QueryDataSet qDataSet = new QueryDataSet();
      qDataSet.setQuery(new com.borland.dx.sql.dataset.QueryDescriptor(database, "SELECT \"user\".\"name\",\"user\".\"password\",\"user\".\"hira\",\"user\".\"rootpath\" " +
        "FROM\"user\" WHERE \"user\".\"name\"=\'"+name+"\'AND\"user\".\"password\"=\'"+password+"\'", null, true, Load.ALL));
      qDataSet.executeQuery();
      if(qDataSet.getRowCount() >=1){
        qDataSet.goToRow(1);
        user.userName =qDataSet.getString(1);
        user.hira =qDataSet.getShort(3);
        user.rootWorkDir =qDataSet.getString(4);
        this.runFrameMain();
      }
      else{
        JOptionPane.showMessageDialog(null,"User name or password is not right.");
      }
    }
  }

  void runFrameMain(){
    /*
    FrameMain myFrameMain=new FrameMain(this.systemDir,this.easySet);
    myFrameMain.setUser(user);
    myFrameMain.setSystemDir(this.systemDir);
    myFrameMain.show();
    this.dispose() ;
    */
  }

  void jPanel1_mouseClicked(MouseEvent e) {
    if(e.getClickCount()>=2) login(true);
  }

}