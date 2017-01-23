package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

import java.awt .*;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class InfoLine  extends Information implements Externalizable{
  public Point Pt1,Pt2;

  // 为其他的备用点
  public Point pp_Pt1,pp_Pt2;

  public Color color;
  public int thick;
  public boolean isDot;
  public InfoLine(){
    super();
  }

  public InfoLine duplicate(){
    InfoLine il=new InfoLine();
    il.color=this.cloneColor(this.color);
    il.ID=this.ID;
    il.isDot=this.isDot;
    il.isSelected=this.isSelected;
    il.Pt1=this.clonePoint(this.Pt1);
    il.Pt2=this.clonePoint(this.Pt2);
    il.thick=this.thick;
    il.visible=this.visible;
    return il;
  }

  public InfoLine(int id,Point Pt1,Point Pt2){
    super();

    this.pp_Pt1 =null;
    this.pp_Pt2 =null;

    ID=id;
    this.Pt1 =Pt1;
    this.Pt2 =Pt2;
    color=new Color(0,0,0);
    thick=1;
    visible=true;
    isDot=false;
    isSelected=false;
  }
  public void writeExternal(ObjectOutput out) throws IOException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      out.writeObject(this.Pt1);
      out.writeObject(this.Pt2);
      out.writeObject(this.color);
      out.writeInt(this.thick);
      out.writeBoolean(this.isDot ) ;
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
     /**@todo Implement this java.io.Externalizable method*/
   try{
       this.Pt1=(Point)in.readObject();
       this.Pt2=(Point)in.readObject();
       this.color=(Color)in.readObject();
       this.thick=in.readInt();
       this.isDot=in.readBoolean( ) ;
       this.isSelected =false;
       this.visible =true;
    }
   catch(IOException e){
       throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
   }
  }

}