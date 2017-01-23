package easygel.setting;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class SettingsInfo implements Externalizable{
  //public int no;
  //public boolean isSelected;
  public String name;
  public String user;
  public String date;
  //public String fileName;

  public SettingsInfo() {
  }

  public void writeExternal(ObjectOutput out) throws IOException {
     /**@todo Implement this java.io.Externalizable method*/
    try{
       //out.writeInt(no);
       //out.writeBoolean(isSelected);
       out.writeObject(name);
       out.writeObject(user);
       out.writeObject(date);
       //out.writeObject(fileName);
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      //no=in.readInt();
      //isSelected=in.readBoolean();
      name=(String)in.readObject();
      user=(String)in.readObject();
      date=(String)in.readObject();
      //fileName=(String)in.readObject();
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

}
