package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

/**
 * <p>操作内部程序解释</p>
 * <p>初始化：ID=0</p>
 * <p>增加元素（addElement）：</p>
 * <p>----1、ID++后增加对象于current中</p>
 * <p>----2、在done中增加“add”</p>
 * <p>----3、将undo清空</p>
 * <p>----4、在add中增加ID</p>
 * <p>删除元素（removeElement）：</p>
 * <p>----1、在done中增加“remove”</p>
 * <p>----2、将要删除的元素增加到remove中</p>
 * <p>----3、将元素从current中删除</p>
 * <p>----4、将undo清空</p>
 * <p>修改元素（modifyElement）：</p>
 * <p>----1、在done中增加“modify”</p>
 * <p>----2、将要修改的元素增加到modify中</p>
 * <p>----3、将元素从current中修改</p>
 * <p>----4、将undo清空</p>
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
 * <p>redo操作：</p>
 * <p>----1、在redo中查出最后操作类型，并删除之</p>
 * <p>----2、操作类型==“add”，在reAdd中查出最后一次增加的对象，并在reAdd中删除之</p>
 * <p>------------------------在add中将其ID加上，将对象添加到current中</p>
 * <p>------------------------在done中增加“add”</p>
 * <p>----3、操作类型==“remove”，在Reremove中查出最后一个ID，并在Reremove中删除之</p>
 * <p>---------------------------在current中查出此对象，并将其删除</p>
 * <p>---------------------------将此对象加入到remove中</p>
 * <p>---------------------------在done中增加“remove”</p>
 * <p>----4、操作类型==“modify”，在reModify中查出最后一个对象，并在reModify中删除之</p>
 * <p>--------------------------在current将此对象按ID查出</p>
 * <p>--------------------------将两个对象在modify和current中互换</p>
 * <p>--------------------------在done中增加“modify”</p>
 */

import java.util.*;
import java.awt .*;
import easygel.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;
import ij.process.*;

public class LayerText extends Layer implements Externalizable {
  public  LayerText(DialogImage di) {
    super(di);
  }

  public  LayerText() {
    super();
  }

  /**
    * <p>增加元素（addElement）：</p>
    * <p>----1、ID++后增加对象于current中</p>
    * <p>----2、在done中增加“add”</p>
    * <p>----3、将undo清空</p>
    * <p>----4、在add中增加ID</p>
   */
  // by wxs 20031003-2
  public void addElement(String text,Point point,boolean setMainDone,
                         String layerSign,int step,int id){
//                         int step,boolean isIDInc) {
    //if(isIDInc==true) ID++;
    InfoText textinfo=new InfoText(id,dialogImage,text,point);
    super.addElement(textinfo,setMainDone,layerSign,step,-1);
  }

  // by wxs 20031003-2
  public void addElement(InfoText textInfo,boolean setMainDone,
                         String layerSign,int step,int index) {
    //                   String layerSign,int step,boolean isIDInc,int index) {
    //int id;
    //if(isIDInc==true) {ID++;id=ID;}
    //else id=textInfo.getID();
    //id=textInfo.getID();
    super.addElement(textInfo,setMainDone,layerSign,step,index) ;
  }

  public void setDialogImage(DialogImage di){
    InfoText infoText;
    for(int iii=1;iii<=this.current .size() ;iii++){
      infoText=(InfoText)current.elementAt(iii-1);
      infoText.dialog =di;
      current.setElementAt(infoText,iii-1);
    }
    this.dialogImage =di;
  }

  /**
   * 给定一点，返回含有该点的对象集
   * @param p
   * @return
   */
  private InfoText getElement(Point p){
    InfoText ti;
    boolean in=false;
    ti=null;
    for(int iii=1;iii<=current.size() ;iii++){
      ti=(InfoText)current.elementAt(iii-1);
      in=(p.x >=ti.getRect() .x) && (p.x<=ti.getRect() .x+ti.getRect() .width-1) &&
         (p.y >=ti.getRect() .y) && (p.y<=ti.getRect() .y+ti.getRect() .height-1);
      if(in==true) break;
      else ti=null;
    }
    return ti;
  }

  /**
   * 设置ti已被选中
   * @param ti
   */
  private void hitElement(InfoText ti){
    ((InfoText)current.elementAt(current.indexOf(ti))).isSelected=true;
    DialogControlText dct;
    dct=((FrameMain)dialogImage.getframeMain()).getControlText();
    if(dct==null) return;
    if(dct.isVisible() ==true){
      dct.setUser(this,ti);
    }
  }

  public LayerText getLayerText(){
    return this;
  }

  public void mouseClicked(Point pt){
    InfoText ti;
    ti=this.getElement(pt);
    if(ti==null){
      for(int iii=1;iii<=current.size() ;iii++){
        ((InfoText)current.elementAt(iii-1)).isSelected=false;
      }
    }
    else{
      if(dialogImage.isPressedShift() ==false){
        for(int iii=1;iii<=current.size() ;iii++)
          ((InfoText)current.elementAt(iii-1)).isSelected=false;
      }
      this.hitElement(ti);
    }

    for(int iii=1;iii<=current.size() ;iii++){
      ti=((InfoText)current.elementAt(iii-1));
      //System.out.println("\nText="+ti.text+",selected:"+ti.isSelected);
    }
  }

  public void setPreparePoint(){
    if(current==null) return;
    int size=current.size();
    InfoText ti;
    for(int ii=1;ii<=size;ii++) {
      ti=(InfoText)current.elementAt(ii-1);
      ti.pp_point=new Point(ti.point.x,ti.point.y);
    }
  }

  public void moveText(int detx,int dety,boolean isModify){
    InfoText oldti,newti;
    int no=0;
    for(int iii=1;iii<=current.size();iii++){
      oldti=((InfoText)current.elementAt(iii-1));
      if(oldti.isSelected ==true){
        if(isModify==false){
          oldti.point.x+=detx;
          oldti.point.y+=dety;
          oldti.adjust();
        }
        else{
          if(oldti.point.x==oldti.pp_point.x && oldti.point.y==oldti.pp_point.y) continue;
          newti=new InfoText();
          newti=oldti.duplicate();
          newti.point .x+=detx;
          newti.point .y+=dety;
          newti.adjust();

          no++;
          oldti.point=new Point(oldti.pp_point.x,oldti.pp_point.y);
          oldti.adjust();
          this.removeElement(oldti,true,"Text",no,false);

          no++;
          //by wxs 20031003-2
          //this.addElement(newti,true,"Text",no,false,iii-1);
          this.addElement(newti,true,"Text",no,iii-1);
        }
      }
    }
  }

  /**
   * 给定一区，返回含有该点的对象集
   * @param r
   * @return
   */
  public Vector getElement(Rectangle r){
     Vector v=new Vector();
     return v;
  }

  public void draw(Graphics g,Point oriPoint){
    for(int iii=1;iii<=current.size() ;iii++)
    {
      Rectangle2D rect2D;
      Graphics2D g2d;
      g2d=(Graphics2D)g;
      Stroke oldStroke;
      oldStroke=g2d.getStroke();
      InfoText ti=((InfoText)current.elementAt(iii-1));
      if(ti.visible ==false) continue;

      //画框
      if(ti.visibleRect ==true){
        Rectangle rect=new Rectangle(0,0,0,0);
        Rectangle rect2=ti.getRect();
        rect.setLocation(rect2.getLocation().x+oriPoint.x ,
                         rect2.getLocation().y +oriPoint.y) ;
        rect.setSize((int)rect2.getWidth() ,(int)rect2.getHeight() );
        rect2D=rect;

        BasicStroke basicStroke;
        float width;
        int cap;
        int join;
        float miterlimit;
        float dash_phase=0;
         width=ti.rectThick;
        if(ti.isRound ==true) {
          cap=BasicStroke.CAP_ROUND ;
          join=BasicStroke.JOIN_ROUND;
        }
        else {
          cap=BasicStroke.CAP_SQUARE ;
          join=BasicStroke.JOIN_BEVEL;
        }
        miterlimit=10.0f;

       if(ti.isSelected ==false){
          if(ti.isDot ==true){
             float []dash={width*2,width*2,width*2,width*2};
              basicStroke= new BasicStroke(width, cap, join, miterlimit, dash,  dash_phase);
          }
          else{
              basicStroke= new BasicStroke(width, cap, join, miterlimit);
           }
           g2d.setColor(ti.colorRect);
        }
       else{
         float []dash={width,width,width*2,width*2,width*3,width*3};
          basicStroke= new BasicStroke(width, cap, join, miterlimit, dash,  dash_phase);
          g2d.setColor(ti.colorRect);
       }
       g2d.setStroke(basicStroke);
       g2d.draw(rect2D);
      }
      // 写文字
      g2d.setFont(ti.font);
      g2d.setColor(ti.colorText);
      g2d.rotate(ti.rotation,ti.rotationPoint .x,ti.rotationPoint .y);
      g2d.drawString(ti.text,ti.point.x+oriPoint.x,ti.point .y+oriPoint.y);
      // 恢复设置
      g2d.rotate(-1*ti.rotation,ti.rotationPoint .x,ti.rotationPoint .y);
      g2d.setStroke(oldStroke);
    }
  }

  public void save(ImageProcessor ipSave,Point oriPoint){
    for(int iii=1;iii<=current.size() ;iii++)
    {
      InfoText ti=((InfoText)current.elementAt(iii-1));
      if(ti.visible ==false) continue;
      //画框
      if(ti.visibleRect ==true){
        ipSave.setColor(ti.colorRect);
        ipSave.setLineWidth(ti.rectThick);
        //    if(ti.isDot==true) ipSave.setDotsMode(true,2*ti.rectThick,ti.rectThick);
        //  else ipSave.setDotsMode(false,0,0);
        Rectangle rect=ti.getRect();
        ipSave.drawRect(rect.getLocation().x+oriPoint.x ,
                        rect.getLocation().y +oriPoint.y,
                        (int)rect.getWidth(),(int)rect.getHeight());
      }
      // 写文字
      ipSave.setColor(ti.colorText);
      ipSave.setFont(ti.font);
      FontMetrics fm;
      fm=ti.dialog.getFontMetrics(ti.font);
      ipSave.drawString(ti.text ,ti.point.x+oriPoint.x  , ti.point .y+oriPoint.y+fm.getLeading());
    }
  }


  public void mouseDragged(MouseEvent e){
    InfoText oldObject,newObject;
    oldObject=this.getElement(e.getPoint());
    if(oldObject==null) return;
    newObject=oldObject;
    newObject.adjust(e.getPoint());
    current.removeElement(oldObject);
    current.addElement(newObject);
  }

  public void writeExternal(ObjectOutput out) throws IOException {
       /**@todo Implement this java.io.Externalizable method*/
      try{
        InfoText it=new InfoText();
        int textNum;
        textNum=this.current.size();
        out.writeInt(textNum);
        for(int iii=1;iii<=textNum;iii++){
            it=(InfoText)this.current.elementAt(iii-1);
            out.writeObject(it);
        }
      }
      catch(IOException e){
        throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
      }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      // first init the layer
      this.initLayer();
      InfoText it=new InfoText();
      int textNum;
      textNum=in.readInt();
      for(int iii=1;iii<=textNum;iii++){
        it=(InfoText)in.readObject();
        it.ID=this.ID;
        // by wxs 20031003-2
        //this.addElement(it,false,"",1,true,-1);
        this.addElement(it,false,"Text",1,-1);
        this.ID++;
      }
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

}

