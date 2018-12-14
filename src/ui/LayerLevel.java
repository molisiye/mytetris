/**
 * @Title: LayLevel.java
 * @Package ui
 * @Description:
 * @author zhm
 * @date 2018年2月12日 下午2:39:19
 * @version
 */
package ui;

import java.awt.Graphics;

public class LayerLevel extends Layer {

  /**
   * 标题图片的宽度
   */
  private static final int IMG_LV_W = Img.LV.getWidth(null);
 
  public LayerLevel(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    // 窗口标题
    int centerX = this.w - IMG_LV_W >> 1;
    g.drawImage(Img.LV, this.x + centerX, this.y + PADDING, null);
    // 显示等级
    this.drawNumberLeftPad(centerX-10, 40, this.dto.getNowLevel(), 3, g);
  }
  
 
}
