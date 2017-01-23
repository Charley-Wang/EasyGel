package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.dx.sql.dataset.*;
import com.borland.dx.dataset.*;
import com.borland.dbswing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogStdPhy extends JDialog {
  private JPanel panel1 = new JPanel();
  private Database database = new Database();
  private QueryDataSet queryDataSet = new QueryDataSet();
  private XYLayout xYLayout1 = new XYLayout();
  private JdbNavToolBar jdbNavToolBar1 = new JdbNavToolBar();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JdbTable jdbTable1 = new JdbTable();
  private JButton jButton1 = new JButton();
  private String systemDir;
  private Column column1 = new Column();
  private Column column2 = new Column();
  private Column column3 = new Column();
  private Column column4 = new Column();
  private Column column5 = new Column();
  private Column column6 = new Column();
  private Column column7 = new Column();
  private Column column8 = new Column();
  private Column column9 = new Column();
  private Column column10 = new Column();
  private Column column11 = new Column();
  private Column column12 = new Column();
  private Column column13 = new Column();
  private int currentLine;
  private Column column14 = new Column();
  private Column column15 = new Column();
  private Column column16 = new Column();
  private Column column17 = new Column();
  private Column column18 = new Column();
  private Column column19 = new Column();
  private Column column20 = new Column();
  private Column column21 = new Column();
  private Column column22 = new Column();
  private Column column23 = new Column();
  private Column column24 = new Column();
  private Column column25 = new Column();
  private Column column26 = new Column();
  private Column column27 = new Column();
  private Column column28 = new Column();
  private Column column29 = new Column();
  private Column column30 = new Column();
  private Column column31 = new Column();
  private Column column32 = new Column();
  private Column column33 = new Column();
  private JButton jButton2 = new JButton();

  public DialogStdPhy(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    currentLine=0;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    systemDir=System.getProperties().getProperty("user.dir");
    String databaseFile=systemDir+"\\database\\EasyGel.jds";
    String select="SELECT \"stdphy\".\"instruction\",\"stdphy\".\"unit\"";
    for(int ii=1;ii<=30;ii++){
      select+=",\"stdphy\".\"num"+String.valueOf(ii)+"\"";
    }
    select+=" FROM \"stdphy\"";
    database.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor(
        "jdbc:borland:dslocal:"+databaseFile, "riceyi", "riceyi", false, "com.borland.datastore.jdbc.DataStoreDriver"));
    queryDataSet.setQuery(new com.borland.dx.sql.dataset.QueryDescriptor(database,select,null,true,Load.ALL));
    jdbTable1.setDataSet(queryDataSet);
    jdbTable1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jdbTable1_mouseClicked(e);
      }
    });
    jdbNavToolBar1.setBorder(null);
    //jdbNavToolBar1.setToolTipText("\"aaa\",\"ccc\"");
    jdbNavToolBar1.setDataSet(queryDataSet);
    jButton1.setForeground(Color.blue);
    jButton1.setBorder(null);
    jButton1.setPreferredSize(new Dimension(28, 25));
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("OK");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton1_mouseEntered(e);
      }
    });
    jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton1_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton1_mouseExited(e);
      }
    });
    jButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jButton1_mouseMoved(e);
      }
    });
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(WindowEvent e) {
        this_windowClosed(e);
      }
    });
    column1.setCaption("Instruction");
    column1.setColumnName("INTERNALROW");
    column1.setDataType(com.borland.dx.dataset.Variant.LONG);
    column1.setRowId(true);
    column1.setTableName("stdphy");
    column1.setServerColumnName("INTERNALROW");
    column1.setSqlType(-5);
    column1.setHidden(true);
    column2.setCaption("Instruction");
    column2.setColumnName("instruction");
    column2.setDataType(com.borland.dx.dataset.Variant.STRING);
    column2.setTableName("stdphy");
    column2.setServerColumnName("instruction");
    column2.setSqlType(12);
    column3.setCaption("Unit");
    column3.setColumnName("unit");
    column3.setDataType(com.borland.dx.dataset.Variant.STRING);
    column3.setTableName("stdphy");
    column3.setServerColumnName("unit");
    column3.setSqlType(12);
    column4.setCaption("Data 1");
    column4.setColumnName("num1");
    column4.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column4.setTableName("stdphy");
    column4.setServerColumnName("num1");
    column4.setSqlType(8);
    column5.setCaption("Data 2");
    column5.setColumnName("num2");
    column5.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column5.setTableName("stdphy");
    column5.setServerColumnName("num2");
    column5.setSqlType(8);
    column6.setCaption("Data 3");
    column6.setColumnName("num3");
    column6.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column6.setTableName("stdphy");
    column6.setServerColumnName("num3");
    column6.setSqlType(8);
    column7.setCaption("Data 4");
    column7.setColumnName("num4");
    column7.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column7.setTableName("stdphy");
    column7.setServerColumnName("num4");
    column7.setSqlType(8);
    column8.setCaption("Data 5");
    column8.setColumnName("num5");
    column8.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column8.setTableName("stdphy");
    column8.setServerColumnName("num5");
    column8.setSqlType(8);
    column9.setCaption("Data 6");
    column9.setColumnName("num6");
    column9.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column9.setTableName("stdphy");
    column9.setServerColumnName("num6");
    column9.setSqlType(8);
    column10.setCaption("Data 7");
    column10.setColumnName("num7");
    column10.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column10.setTableName("stdphy");
    column10.setServerColumnName("num7");
    column10.setSqlType(8);
    column11.setCaption("Data 8");
    column11.setColumnName("num8");
    column11.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column11.setTableName("stdphy");
    column11.setServerColumnName("num8");
    column11.setSqlType(8);
    column12.setCaption("Data 9");
    column12.setColumnName("num9");
    column12.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column12.setTableName("stdphy");
    column12.setServerColumnName("num9");
    column12.setSqlType(8);
    column13.setCaption("Data 10");
    column13.setColumnName("num10");
    column13.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column13.setTableName("stdphy");
    column13.setServerColumnName("num10");
    column13.setSqlType(8);
    column14.setColumnName("num11");
    column14.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column14.setPreferredOrdinal(-1);
    column14.setServerColumnName("NewColumn1");
    column14.setSqlType(0);
    column14.setCaption("Data 11");
    column15.setColumnName("num12");
    column15.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column15.setPreferredOrdinal(-1);
    column15.setServerColumnName("NewColumn1");
    column15.setSqlType(0);
    column16.setColumnName("num13");
    column16.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column16.setPreferredOrdinal(-1);
    column16.setServerColumnName("NewColumn2");
    column16.setSqlType(0);
    column17.setColumnName("num14");
    column17.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column17.setPreferredOrdinal(-1);
    column17.setServerColumnName("NewColumn3");
    column17.setSqlType(0);
    column18.setColumnName("num15");
    column18.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column18.setPreferredOrdinal(-1);
    column18.setServerColumnName("NewColumn4");
    column18.setSqlType(0);
    column19.setColumnName("num16");
    column19.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column19.setPreferredOrdinal(-1);
    column19.setServerColumnName("NewColumn5");
    column19.setSqlType(0);
    column20.setColumnName("num17");
    column20.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column20.setPreferredOrdinal(-1);
    column20.setServerColumnName("NewColumn6");
    column20.setSqlType(0);
    column21.setColumnName("num18");
    column21.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column21.setPreferredOrdinal(-1);
    column21.setServerColumnName("NewColumn7");
    column21.setSqlType(0);
    column22.setColumnName("num19");
    column22.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column22.setPreferredOrdinal(-1);
    column22.setServerColumnName("NewColumn8");
    column22.setSqlType(0);
    column23.setColumnName("num20");
    column23.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column23.setPreferredOrdinal(-1);
    column23.setServerColumnName("NewColumn9");
    column23.setSqlType(0);
    column24.setColumnName("num21");
    column24.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column24.setPreferredOrdinal(-1);
    column24.setServerColumnName("NewColumn10");
    column24.setSqlType(0);
    column25.setColumnName("num22");
    column25.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column25.setPreferredOrdinal(-1);
    column25.setServerColumnName("NewColumn11");
    column25.setSqlType(0);
    column26.setColumnName("num23");
    column26.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column26.setPreferredOrdinal(-1);
    column26.setServerColumnName("NewColumn12");
    column26.setSqlType(0);
    column27.setColumnName("num24");
    column27.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column27.setPreferredOrdinal(-1);
    column27.setServerColumnName("NewColumn1");
    column27.setSqlType(0);
    column28.setColumnName("num25");
    column28.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column28.setPreferredOrdinal(-1);
    column28.setServerColumnName("NewColumn2");
    column28.setSqlType(0);
    column29.setColumnName("num26");
    column29.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column29.setPreferredOrdinal(-1);
    column29.setServerColumnName("NewColumn3");
    column29.setSqlType(0);
    column30.setColumnName("num27");
    column30.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column30.setPreferredOrdinal(-1);
    column30.setServerColumnName("NewColumn4");
    column30.setSqlType(0);
    column31.setColumnName("num28");
    column31.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column31.setPreferredOrdinal(-1);
    column31.setServerColumnName("NewColumn5");
    column31.setSqlType(0);
    column32.setColumnName("num29");
    column32.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column32.setPreferredOrdinal(-1);
    column32.setServerColumnName("NewColumn6");
    column32.setSqlType(0);
    column33.setColumnName("num30");
    column33.setDataType(com.borland.dx.dataset.Variant.DOUBLE);
    column33.setPreferredOrdinal(-1);
    column33.setServerColumnName("NewColumn7");
    column33.setSqlType(0);
    jButton2.setForeground(Color.blue);
    jButton2.setBorder(null);
    String tip="Double clik the interested row and column and then click OK button";
    jButton2.setToolTipText(tip);
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Help");
    panel1.add(jScrollPane1,   new XYConstraints(2, 2, 429, 172));
    panel1.add(jdbNavToolBar1,               new XYConstraints(2, 179, 433, 38));
    jScrollPane1.getViewport().add(jdbTable1, null);
    jdbNavToolBar1.add(jButton1, null);
    this.getContentPane().add(panel1, BorderLayout.NORTH);
    setTips();
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    queryDataSet.setColumns(new Column[] {column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24,
        column25, column26, column1, column27, column28, column29, column30, column31, column32, column33, column2, column3, column4, column5,
        column6, column7, column8, column9, column10, column11, column12, column13});
  }
  private void setTips(){
    jdbNavToolBar1 .getFirstButton() .setToolTipText("First Record");
    jdbNavToolBar1 .getDeleteButton().setToolTipText("Delete Record");
    jdbNavToolBar1 .getInsertButton().setToolTipText("Add Record") ;
    jdbNavToolBar1 .getNextButton() .setToolTipText("Next Record") ;
    jdbNavToolBar1.getPostButton(). setToolTipText("  ") ;
    jdbNavToolBar1.getRefreshButton(). setToolTipText("Refresh") ;
    jdbNavToolBar1.getSaveButton() . setToolTipText("Save") ;
    jdbNavToolBar1.getCancelButton() . setToolTipText("Cancel") ;
    jdbNavToolBar1.getDittoButton().setToolTipText("");
    jdbNavToolBar1.getLastButton().setToolTipText("Last Record");
    jdbNavToolBar1.getPriorButton() . setToolTipText("Last Record") ;
    jButton1.setToolTipText("Exit");
    easygel.uiti.WxsUiti.centerDialog(this,428,233);
    jdbNavToolBar1.add(jButton2, null);
    jdbNavToolBar1.add(jButton1, null);
    jdbNavToolBar1.add(jButton1, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    //退出事务管理
    jdbNavToolBar1.getSaveButton().doClick();
    this.hide() ;
  }

  void this_windowClosed(WindowEvent e) {
    //退出事务管理
    jdbNavToolBar1.getSaveButton().doClick();
  }

  void jButton1_mouseMoved(MouseEvent e) {

  }

  void jButton1_mouseEntered(MouseEvent e) {
    jButton1 .setBorder(BorderFactory.createRaisedBevelBorder() );
  }

  void jButton1_mouseExited(MouseEvent e) {
    jButton1 .setBorder(BorderFactory.createEmptyBorder());
  }

  public  String getInstrution(){
    if(currentLine==0) return "";
    else return (String)jdbTable1.getValueAt(currentLine-1,0);
  }

  public String getUnit(){
    return (String)jdbTable1.getValueAt(currentLine-1,1);
  }

  public double[] getData(){
    double data[];
    int datan=0;
    data=null;
    for( int ii=1;ii<=10;ii++){
      if(jdbTable1.getValueAt(currentLine-1,ii+1)==null) continue;
       datan++;
    }
    data=new double[datan];
    datan=0;
    for( int ii=1;ii<=10;ii++){
      if(jdbTable1.getValueAt(currentLine-1,ii+1)==null) continue;
       data[datan]=((Double)jdbTable1.getValueAt(currentLine-1,ii+1)).doubleValue();
       datan++;
    }
    return data;
  }

  void jdbTable1_mouseClicked(MouseEvent e) {
    currentLine=jdbTable1.getSelectedRow() +1;
    this.setTitle("Select No "+String.valueOf(currentLine)+" as the standard.");
  }
}