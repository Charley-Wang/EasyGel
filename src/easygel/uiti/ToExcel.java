package easygel.uiti;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

import java.io.File;
import java.util.*;
import jxl.write.*;
import jxl.*;
import java.io.IOException;
import jxl.write.biff.RowsExceededException;
import java.io.*;

public class ToExcel {
  private String filePathName;
  private String []str;
  double [][]data;
  public ToExcel() {
  }

  public void writeToExcel(String filename,String []columnName,String []str,String [][]data){
    WritableWorkbook workbook;
    workbook=null;
    try{
      workbook = Workbook.createWorkbook(new File(filename));
    }
    catch(IOException e){
    }
    WritableSheet sheet = workbook.createSheet("First Sheet", 0);
    try{
      int lineNum=0;
      for(int ii=0;ii<str.length;ii++){
        Label label0 = new Label(0, lineNum, str[ii]);
        lineNum++;
        sheet.addCell(label0);
      }
      for(int ii=0;ii<2;ii++){
        Label label0 = new Label(0, lineNum, "");
        lineNum++;
        sheet.addCell(label0);
      }
      for(int ii=0;ii<columnName.length;ii++){
        Label label1 = new Label(ii, lineNum, columnName[ii]);
        sheet.addCell(label1);
      }
      lineNum++;
      for(int ii=0;ii<data.length;ii++){
        for(int jj=0;jj<data[0].length;jj++){
          try{
            double doub=Double.parseDouble(data[ii][jj]);
            jxl.write.Number number=new jxl.write.Number(jj,lineNum+ii,doub);
            sheet.addCell(number);
          }
          catch(NumberFormatException e){
            Label label2 =new Label(jj, lineNum+ii, data[ii][jj]);
            sheet.addCell(label2);
          }
        }
      }
    }
    catch(WriteException e){
    }

    try{
      workbook.write();
      workbook.close();
    }
    catch(IOException e){
    }
  }


  public void writeToTxt(String filename,String []columnName,String []str,String [][]data,boolean needTitle){
    BufferedWriter out;
    String text="";
    try{
      FileOutputStream outFile=new FileOutputStream(filename);
      out = new BufferedWriter(new OutputStreamWriter(outFile, "UTF-16"));

      if(needTitle==true){
        for(int ii=1;ii<=str.length;ii++){
          out.write(str[ii-1]);
          out.newLine();
        }
        for(int ii=1;ii<=2;ii++){
          out.newLine();
        }
        for(int ii=1;ii<=columnName.length;ii++){
          text+=columnName[ii-1]+" ";
        }
        out.write(text);
        out.newLine();
      }

      for(int ii=0;ii<data.length;ii++){
        text="";
        for(int jj=0;jj<data[0].length;jj++){
          if(needTitle==true)  text+=data[ii][jj]+" ";
          else text+=data[ii][jj]+"\t";
        }
        out.write(text);
        out.newLine();
      }

      out.close();
      outFile.close();
    }
    catch(FileNotFoundException e){
    }
    catch(IOException e){
    }

  }

}

