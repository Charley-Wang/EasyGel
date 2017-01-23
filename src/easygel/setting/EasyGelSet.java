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

public class EasyGelSet implements Externalizable{
  public int waitSeconds;
  public int iconSize;
  public String imageDir;
  public String paraPre;

  public EasyGelSet() {
  }

  public void writeExternal(ObjectOutput out) throws IOException{
    /**@todo Implement this java.io.Externalizable method*/
    try{
      out.writeInt(waitSeconds);
      out.writeInt(iconSize);
      out.writeObject(imageDir);
      out.writeObject(paraPre);
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      waitSeconds=in.readInt();
      iconSize=in.readInt();
      imageDir=(String)in.readObject();
      paraPre=(String)in.readObject();
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

}
