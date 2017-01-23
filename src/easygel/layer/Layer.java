package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

import java.util.*;
import easygel.*;
import java.awt .*;
import easygel.*;
import java.awt.geom.*;
import java.awt.event.*;


public abstract class Layer {
  protected DialogImage dialogImage;
  //向量一定要在此new一下！！！
  /**当前层的内容*/
  protected Vector current=new Vector();

  /** 为唯一的标识符 */
  public  int ID;

  /**表示操作的顺序：分别为“add”“remove”“modify”*/
  protected  Vector done =new Vector();

  protected  Vector add=new Vector();
  protected  Vector remove=new Vector();
  //protected  Vector modify=new Vector();

  public void initLayer(){
    // ???
    //dialogImag=;
    current=new Vector();
    ID=-1000000;
    done=new Vector();
    add=new Vector();
    remove=new Vector();
  }

  public Layer() {
    ID=-1000000;
    //System.out.print("\n"+this.getClass().getName()+",ID1"+ID);
  }

  public Layer(DialogImage di){
    ID=-1000000;
    dialogImage=di;
    //System.out.print("\n"+this.getClass().getName()+"ID2"+ID);
  }

  //abstract void draw(Graphics g);

  /**
   * <p>删除元素（removeElement）：</p>
   * <p>----1、在done中增加“remove”</p>
   * <p>----2、将要删除的元素增加到remove中</p>
   * <p>----3、将元素从current中删除</p>
   * <p>----4、将undo清空</p>
   */
  public void removeElement(Information infor,boolean setMainDone,
                            String layerSign,int step,boolean removeCurrent){
    done.addElement("remove");
    remove.addElement(infor);

    int idNum=this.getIDNumber(infor);
    if(idNum!=0) {
      if(removeCurrent==true){
        current.remove(idNum-1);
      }
      else{
        infor.status=0;
      }
    }

    if(setMainDone==true) this.dialogImage.setMainDone(layerSign,step);

    //this.dialogImage.printUndoStatus("in remove element");
    //System.out.println(this.toString());
  }

  public int getIDNumber(Information info){
    int id=0;
    if(current==null) id=0;
    else{
      for(int ii=1;ii<=current.size();ii++){
        //System.out.println("info ID"+info.getID());
        //System.out.println("ele ID"+((Information)current.elementAt(ii-1)).getID());
        if(((Information)current.elementAt(ii-1)).getID()==info.getID()){
          id=ii;
          break;
        }
      }
    }
    return id;
  }
  /*
  public void removeElement(Object o,boolean setMainDone,String layerSign,int step){
    done.addElement("remove");
    remove.addElement(o);
    current.remove(o);
    if(setMainDone==true){
      this.dialogImage .setMainDone(layerSign,step) ;
    }
  }
  */

  public Vector getCurrent(){
    return current;
  }

  /**
   * 修改对象o，的modifyID为value
   * <p>修改元素（modifyElement）：</p>
   * <p>----1、在done中增加“modify”</p>
   * <p>----2、将要修改的元素增加到modify中</p>
   * <p>----3、将元素从current中修改</p>
   * <p>----4、将undo清空</p>
   */
  /*
  public void modifyElement(Information oldObject,Information newObject,
                            boolean setMainDone,String layerSign,int step){
    done.addElement("modify");
    Information oldObject2=new Information();
    Information newObject2=new Information();
    oldObject2=oldObject;
    newObject2=newObject;
    modify.addElement(oldObject2);
    current.removeElement(oldObject2);
    current.addElement(newObject2);
    if(setMainDone==true){
      this.dialogImage .setMainDone(layerSign,step);
    }
  }
  */
  /*
  public void modifyElement(Object oldObject,Object newObject,
                            boolean setMainDone,String layerSign,int step){
    done.addElement("modify");
    modify.addElement(oldObject);
    current.removeElement(oldObject);
    current.addElement(newObject);
    if(setMainDone==true){
      this.dialogImage .setMainDone(layerSign,step);
    }
  }
  */

  public String toString(){
    String str="";
    Information info;
    str="\ncurrent:";
    for(int ii=1;ii<=current.size();ii++){
      info=(Information)current.elementAt(ii-1);
      str+="\n    <"+ii+"> ID="+String.valueOf(info.ID)+","+info.toString();
    }

    str+="\ndone:";
    for(int ii=1;ii<=done.size();ii++){
      String aaa=(String)done.elementAt(ii-1);
      str+="\n    ("+ii+") "+aaa;
    }

    str+="\nadd:";
    for(int ii=1;ii<=add.size();ii++){
      String aaa=((Integer)add.elementAt(ii-1)).toString();
      str+="\n    ^"+ii+"^ "+aaa;
    }

    str+="\nremove:";
    for(int ii=1;ii<=remove.size();ii++){
      info=(Information)remove.elementAt(ii-1);
      str+="\n    {"+ii+"} ID="+String.valueOf(info.ID)+","+info.toString();
    }

    return str;
  }


  /**
   * <p>undo操作：</p>
   * <p>----1、在done中查出最后操作类型，并删除之</p>
   * <p>----2、操作类型==“add”，在add中查出最后一次增加的ID，并在add中删除之</p>
   * <p>------------------------根据ID查出对象，将此从current中删除之</p>
   * <p>------------------------将此对象保存在reAdd中</p>
   * <p>------------------------在undo中增加“add”</p>
   * <p>----3、操作类型==“remove”，在remove中查出最后一个对象，并在remove中删除之</p>
   * <p>--------------------------将此对象加入到current中</p>
   * <p>--------------------------将此对象的ID加入到reRemove中</p>
   * <p>--------------------------在undo中增加“remove”</p>
   * <p>----4、操作类型==“modify”，在modify中查出最后一个对象，并在modify中删除之</p>
   * <p>--------------------------在current将此对象按ID查出</p>
   * <p>--------------------------将两个对象在reModify和current中互换</p>
   * <p>--------------------------在undo中增加“modify”</p>
   */
  public void undo(){
    int doneSize=done.size();
    if(doneSize==0) return;
    String type=(String)done.elementAt(doneSize-1);
    if(type.equals("add")){
      int id;
      id=((Integer)add.elementAt((add.size())-1)).intValue();
      Information infor=this.getElement(current,id);
      add.remove(add.size()-1);
      current.removeElement(infor);
    }
    else if(type.equals("remove")){
      int lastnum;
      lastnum=remove.size() ;
      Information infor;
      infor=(Information)remove.elementAt(lastnum-1);
      remove.remove(lastnum-1);
      current.addElement(infor);
    }
    done.remove(doneSize-1);

    //this.dialogImage.printUndoStatus("in remove element");
    //System.out.println(this.toString());
  }

  private Information getElement(Vector v,long id){
    Information infor;
    int vSize;
    vSize=v.size();
    //System.out.println("vSize in getElement:"+vSize+", id="+id);
    infor=null;
    for(int iii=1;iii<=vSize;iii++){
      if(((Information)v.elementAt(iii-1)).getID()==id){
        infor=(Information)v.elementAt(iii-1);
        //System.out.println("in getElement:"+infor);
        break;
      }
    }
    return infor;
  }

  public int getNextID(){
    return ID;
  }

  public void setCurrentID(int id){
    this.ID=id;
  }

  public void addElement(Information infor,boolean setMainDone,
                         String layerSign,int step,int index){
    if(index>=0) current.setElementAt(infor,index);
    else current.addElement(infor);
    done.addElement("add");
    add.addElement(new Integer(infor.ID));
    if(setMainDone==true) this.dialogImage .setMainDone(layerSign,step);
  }

  public void rePaintAll(){
    this.dialogImage.repaint();
  }

  public void setStauts(int status){
    for(int ii=1;ii<=current.size();ii++)
      ((Information)current.elementAt(ii-1)).status=status;
  }

  public void removeCurrentStatus(int status){
    int size=current.size();
    Information infor;
    for(int ii=1;ii<=size;ii++){
      infor=(Information)current.elementAt(ii-1);
      if(infor.status==status){
        //current.removeElement(infor);
        current.remove(infor);
        ii--;
        size--;
      }
    }
  }

  public void deleteSelectedElement(boolean setMainDone,String layerSign){
    int enum2;
    enum2=current.size() ;
    Information infor;
    int removeNum=0;
    this.setStauts(1);
    for(int ii=1;ii<=enum2;ii++){
      infor=(Information)current.elementAt(ii-1);
      if(infor.isSelected ==true){
        removeNum++;
        this.removeElement(infor,setMainDone,layerSign,removeNum,false);
        enum2--;
      }
    }
    this.removeCurrentStatus(0);
    this.dialogImage .repaint() ;
  }

  // for Encrypt subClass need to be reride them as follows
  /*
  public int getIDNumber(Information... info){
    super(info);
  }
  public void removeElement(Information... infor,boolean setMainDone,String layerSign,int step,boolean removeCurrent){
    super(infor,setMainDone,layerSign,step,removeCurrent);
  }
  */

}