/**   
 * @Title: LayNext.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午2:39:46 
 * @version  
 */
package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer {
  private static Image[] NEXT_ACT;
  
  static {
    NEXT_ACT = new Image[7];
    for (int i = 0; i < NEXT_ACT.length; i++) {
      NEXT_ACT[i] = new ImageIcon("graphics/game/" + i + ".png").getImage();
    }
  }
  public LayerNext(int x, int y, int w, int h) {
    super(x, y, w, h);
  }
  
  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    // 如果是开始状态才绘制下一个方块
    if(this.dto.isStart()) {
    this.drawImageAtCenter(NEXT_ACT[this.dto.getNext()], g);
    }
  }


}
