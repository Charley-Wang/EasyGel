package easygel;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.uiti.*;
import java.io.*;

public class DialogReport extends JDialog {
  String[] colNames={"No",""};
  String[][] dataTable={{"Wxs1","25"},{"Wxr","30"}};
  private DefaultTableModel dt=new DefaultTableModel(dataTable,colNames);
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JPanel jPanel2 = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private TitledBorder titledBorder1;
  private GridLayout gridLayout1 = new GridLayout();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JEditorPane jEditorPane1 = new JEditorPane();
  private JTable jTable1 = new JTable(dt);
  private JButton jButton6 = new JButton();

  private  String [] editString;
  private  String [] columnNames;
  private  String[][] columnData;

  private int saveFormat;

  private FrameMain frameMain;
  private JButton jButton1 = new JButton();

  public DialogReport(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frameMain=(FrameMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogReport() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    jPanel1.setLayout(borderLayout1);
    jScrollPane2.setPreferredSize(new Dimension(100, 100));
    jPanel2.setBorder(titledBorder1);
    jPanel2.setMinimumSize(new Dimension(400, 40));
    jPanel2.setPreferredSize(new Dimension(400, 40));
    jPanel2.setLayout(gridLayout1);
    jEditorPane1.setForeground(Color.magenta);
    jEditorPane1.setMinimumSize(new Dimension(400, 60));
    jEditorPane1.setPreferredSize(new Dimension(400, 60));
    jEditorPane1.setText("jEditorPane1");
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("To txt...");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("Exit");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("To Excel ...");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jScrollPane1.setMinimumSize(new Dimension(400, 150));
    jScrollPane1.setPreferredSize(new Dimension(400, 150));
    jTable1.setForeground(Color.magenta);
    jTable1.setGridColor(SystemColor.desktop);
    jTable1.setSelectionBackground(Color.cyan);
    jTable1.setSelectionForeground(Color.blue);
    jButton1.setText("Save");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        this_mouseClicked(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jScrollPane2, BorderLayout.NORTH);
    jScrollPane2.getViewport().add(jEditorPane1, null);
    jPanel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton1, null);
    jPanel2.add(jButton2, null);
    jPanel2.add(jButton3, null);
    jPanel2.add(jButton6, null);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
  }

  public void setContext(String winTitle,String []editContext,
                         String[] columnName,String[][]columnData,
                         boolean isUpperNull){

    // 调整0.0为0
    for(int ii=1;ii<=columnData.length ;ii++){
      for(int jj=1;jj<=columnData[0].length ;jj++){
        if(columnData[ii-1][jj-1].equals("0.0")) columnData[ii-1][jj-1]="0";
      }
    }

    // 设置上三角为空
    if(isUpperNull==true){
      for(int ii=1;ii<=columnData.length ;ii++){
        for(int jj=ii+1;jj<=columnData[0].length ;jj++){
           columnData[ii-1][jj-1]="";
        }
      }
    }

    this.editString= editContext;
    this.columnNames =columnName;
    this.columnData =columnData;

    this.setTitle(winTitle);
    String str="";
    for(int ii=1;ii<=this.editString.length;ii++){
      str+=this.editString[ii-1]+"\n";
    }
    this.jEditorPane1 .setText(str);

    this.jTable1 .removeAll() ;
    dt=new DefaultTableModel(columnData,columnName);
    this.jTable1 .setModel(dt);
  }

  void jButton6_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.save(".xls",1);
  }

  private  String readImageDir(){
    String imageDir="";
    String systemDir;
    File initDir;
    systemDir=System.getProperties().getProperty("user.dir");
    initDir=new File(systemDir+"\\database\\ImageDir.ini");
    if(initDir.exists() ==true){
      try{
        FileInputStream inputFile=new FileInputStream(systemDir+"\\database\\ImageDir.ini");
        DataInputStream inputData=new DataInputStream(inputFile);
        imageDir=inputData.readLine();
        inputData.close() ;
        inputFile.close() ;
        initDir=new File(imageDir);
        if(initDir.isDirectory() ==false){
          imageDir=systemDir;
        }
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    else{
      imageDir=systemDir;
    }
    return imageDir;
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.save(".txt",2);
  }

  private void save(String fileEnd,int saveFormat){
    String end;
    end=fileEnd;
    FileDialog filedlg=new FileDialog(this.frameMain,"Please select a file: ???"+end);
    filedlg.setMode(filedlg.SAVE);
    filedlg.setDirectory(this.readImageDir());
    filedlg.show();
    String saveFile;
    if(filedlg.getName().length() !=0){
      saveFile=filedlg.getDirectory() +"\\"+ filedlg.getFile();
      saveFile+=end;
    }
    else{
      return;
    }

   boolean checked=true;
   if(checked==true){
     File file=new File(saveFile);
     if(file.exists() ==true){
       JOptionPane myOptionPane=new JOptionPane();
       Object[] options = { "Overwrite", "No"};
       int  selectOption;
       selectOption=myOptionPane.showOptionDialog(null,
                 "Overwrite?", "Warning",
                 JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE ,
                null, options, options[0]);
      if(selectOption==1) return;
       file.delete();
     }
   }

    ToExcel toExcel=new ToExcel();
    if(saveFormat==1)
      toExcel.writeToExcel(saveFile,columnNames,editString,columnData);
    else if(saveFormat==2)
      toExcel.writeToTxt(saveFile,columnNames,editString,columnData,false);
    else
      toExcel.writeToTxt(saveFile,columnNames,editString,columnData,true);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.save(".txt",3);
  }

  void this_mouseClicked(MouseEvent e) {
  }

}