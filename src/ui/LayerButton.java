/**   
 * @Title: LayButton.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午2:38:50 
 * @version  
 */
package ui;

import java.awt.Graphics;

public class LayerButton extends Layer {
  public LayerButton(int x, int y, int w, int h) {
    super(x, y, w, h);
  }
  
  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
  }
}
