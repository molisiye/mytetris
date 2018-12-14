/**   
 * @Title: LayAbout.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午2:40:20 
 * @version  
 */
package ui;

import java.awt.Graphics;

public class LayerAbout extends Layer {

  
  public LayerAbout(int x, int y, int w, int h) {
    super(x, y, w, h);
  }
  
  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    this.drawImageAtCenter(Img.SIGN, g);
  }
}
