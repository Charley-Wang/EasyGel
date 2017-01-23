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

public class Settings implements Externalizable{
  public String type;
  public boolean paraBoolean;
  public String paraString;
  public int paraInt;
  public double paraDouble;
  public Settings() {
    paraBoolean=false;
    paraString="";
    paraInt=0;
    paraDouble=0.0;
  }
  public void writeExternal(ObjectOutput out) throws IOException {
       /**@todo Implement this java.io.Externalizable method*/
      try{
         out.writeObject(type);
         out.writeBoolean(paraBoolean);
         out.writeObject(paraString);
         out.writeInt(paraInt);
         out.writeDouble(paraDouble);
      }
      catch(IOException e){
        throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
      }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      type=(String)in.readObject();
      paraBoolean=in.readBoolean();
      paraString=(String)in.readObject();
      paraInt=in.readInt();
      paraDouble=in.readDouble();
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }
}