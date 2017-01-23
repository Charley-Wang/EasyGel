package easygel;

import javax.swing.*;

public class Verifier extends InputVerifier {
  public static final char INT ='i';
  public static final char FLOAT='f';
  private char type;
  private boolean isMin,isMax;
  private double min,max;
  private JDialog dialog;
  private boolean isInputInt;

  public Verifier(char type,boolean isMin,double min,boolean isMax,double max,
                  JDialog dialog,boolean isInputInt){
    this.type=type;
    this.isMin=isMin;
    this.isMax=isMax;
    this.min=min;
    this.max=max;
    this.dialog=dialog;
    this.isInputInt=isInputInt;
  }

  public boolean verify(JComponent input) {
    JTextField tf = (JTextField) input;
    String s=tf.getText();
    char c;
    double num=0;
    for(int ii=1;ii<=s.length();ii++){
      c=s.charAt(ii-1);
      if(type==FLOAT){
        if(Character.isDigit(c)==false && c!='.' && c!='-'){
          JOptionPane.showMessageDialog(dialog,"Your input is: "+s+"\nInput should be a number.",
                                        "Input Error",JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
      else if(type==INT){
        if(Character.isDigit(c)==false && c!='-'){
          JOptionPane.showMessageDialog(dialog,"Your input is: "+s+"\nInput should be an integer.",
                                        "Input Error",JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
    }

    try{
      num=Double.parseDouble(s);
    }
    catch(NumberFormatException e){
      JOptionPane.showMessageDialog(dialog,"Your input is: "+s+"\nInput should be a number",
                                      "Input Error",JOptionPane.ERROR_MESSAGE);
      return false;
    }
    String minMax;


    if(isMin==true){
      if(num<min){
        if(isInputInt==true) minMax=String.valueOf((int)min);
        else minMax=String.valueOf(min);
        JOptionPane.showMessageDialog(dialog,"Your input is: "+s+"\nNumber should be greater than or equal to "+minMax,
                                      "Input Error",JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    if(isMax==true){
      if(num>max){
        if(isInputInt==true) minMax=String.valueOf((int)max);
        else minMax=String.valueOf(max);
        JOptionPane.showMessageDialog(dialog,"Your input is: "+s+"\nNumber should be less than or equal to "+minMax,
                                      "Input Error",JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }

    return true;
  }

  // end of the class
}
