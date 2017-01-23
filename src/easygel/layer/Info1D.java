package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

//Liusheng  2003-03-22

import java.awt.*;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class Info1D extends Information  implements Externalizable {
  //线段上的2点
  public Point point1,point2;
  //线的性质 up为上线 down为下线 left为泳道左线, right为泳道右线,line为电泳条带
  public String character;
  //泳道号，带号
  public int  laneN,lineN;
  //???lineinlane record line start num in lane
  public int lineinlane[]=new int[30];

  /**
   * added by wxs 2003/08/02
   * @return
   */
  public String toString(){
    String str;
    str="Property:";
    if(character.equals("up")) str+="up";
    else if(character.equals("down")) str+="down";
    else if(character.equals("left")) str+="left";
    else if(character.equals("right")) str+="right";
    else if(character.equals("line")) str+="line";
    else str+="undefined";
    str+=", Lane No:"+String.valueOf(laneN)+", Band No:"+String.valueOf(lineN);
    str+=", ["+point1.x+","+point1.y+"]";
    str+=", ["+point2.x+","+point2.y+"]";
    return str;
  }

  public Info1D duplicate(){
    Info1D id=new Info1D();
    id.character=new String(this.character);
    id.ID=this.ID;
    id.isSelected=this.isSelected;
    id.laneN=this.laneN;
    if(this.lineinlane==null) id.lineinlane=null;
    else{
      int []temp=new int[this.lineinlane.length];
      for(int ii=1;ii<=this.lineinlane.length;ii++) temp[ii-1]=this.lineinlane[ii-1];
      id.lineinlane=temp;
    }
    id.lineN=this.lineN;
    id.point1=this.clonePoint(this.point1);
    id.point2=this.clonePoint(this.point2);
    id.visible=this.visible;
    return id;
  }

  public Info1D(){
    super();
  }

  public Info1D(Point point1,Point point2,String character,
                         int laneN,int lineN,int ID){
    super();
    this.point1 =point1;
    this.point2 =point2;
    this.character =character;
    this.laneN =laneN;
    this.lineN =lineN;
    this.ID =ID;
    this.isSelected =false;
    this.visible =true;
  }

  public void writeExternal(ObjectOutput out) throws IOException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      out.writeBoolean(visible);
      //isSelected=true;
      out.writeObject(point1);
      out.writeObject(point2);
      out.writeObject(character);
      //System.out.println("write"+character);
      out.writeInt(laneN);
      out.writeInt(lineN);
      for(int ii=1;ii<=30;ii++) out.writeInt(lineinlane[ii-1]);
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      visible=in.readBoolean();
      isSelected=true;
      point1=(Point)in.readObject();
      point2=(Point)in.readObject();
      character=(String)in.readObject();
      //System.out.println("read"+character);
      laneN=in.readInt();
      lineN=in.readInt();
      for(int ii=1;ii<=30;ii++) lineinlane[ii-1]=in.readInt();
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

}
