package easygel.image;

import java.awt.*;

//定义由fillCell函数所返回的参数值
//m_mask 代表返回的填充掩码数组
//m_height,m_width则代表掩码数组的宽 高 ；宽用
//m_filled代表填充了多少个点，这也是轮廓的面积，即细胞的面积。
public class CellFillReturn {
  public short[][] m_mask;
  public Rectangle m_rect;
  public int m_filled;
  public CellFillReturn(short[][]mask,Rectangle rect,int nfilled) {
    m_mask=mask;
    m_rect=rect;
    m_filled=nfilled;
  }
}