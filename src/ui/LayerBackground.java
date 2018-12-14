/**   
 * @Title: LayBackground.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午3:07:31 
 * @version  
 */
package ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {

  /**
   * @param x
   * @param y
   * @param w
   * @param h
   */
  public LayerBackground(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  /* (non-Javadoc)
   * @see ui.Lay#paint(java.awt.Graphics)
   */
  @Override
  public void paint(Graphics g) {
    int bgIdx = this.dto.getNowLevel() % Img.BG_LIST.size();
    g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0, 1162, 654, null);

  }

}
