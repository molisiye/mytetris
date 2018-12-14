/**   
 * @Title: LayPoint.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午2:40:05 
 * @version  
 */
package ui;

import config.GameConfig;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

public class LayerPoint extends Layer {

  /**
   * 分数 y 坐标
   */
  private final int pointY;

  /**
   * 消行 y 坐标
   */
  private final int rmlineY;
  
  /**
   * 分数最大位数
   */
  private static final int POINT_BIT = 5;
//  private final int pointX;

  /**
   * 共同的 x 坐标
   */
  private final int comX;

  /**
   * 经验值 y 坐标
   */
  private final int expY;

  /**
   * 经验值宽度
   */
  private final int expW;

  /**
   * 升级行数
   */
  private static final int LEVEL_UP = GameConfig.getSystemCnfig().getLevelUp();




  public LayerPoint(int x, int y, int w, int h) {
    super(x, y, w, h);
    // 初始化共同的 X 坐标
    this.comX = IMG_NUMBER_W * POINT_BIT - PADDING;
    // 初始化分数显示的 y 坐标
    this.pointY = PADDING;
    // 初始化消行显示的 y 坐标
    this.rmlineY = this.pointY + Img.POINT.getHeight(null) + PADDING;
    // 初始化经验值显示的 y 坐标
    this.expY = this.rmlineY + Img.RMLINE.getHeight(null) + PADDING;
    // 初始化经验值槽的宽度
    this.expW = this.w - (PADDING << 1);
  }
  
  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    // 窗口标题（分数）
    g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
    // 显示分数
    this.drawNumberLeftPad(comX-50, pointY-20, this.dto.getNowPoint(), POINT_BIT, g);
    // 窗口标题（消行）
    g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY, null);
    // 显示消行
    this.drawNumberLeftPad(comX-50, rmlineY-20, this.dto.getNowRemoveLine(), POINT_BIT, g);
    // 绘制值槽(经验值)
    int rmline = this.dto.getNowRemoveLine();
    this.drawRect(expY, "下一级", null, (double)(rmline % LEVEL_UP) / (double)LEVEL_UP, g);

  }
  
  
  
  @Deprecated
  private Color getNowColor(double hp, double maxHp) {
    int colorR = 0;
    int colorG = 255;
    int colorB = 0;
    
    double hpHalf = maxHp / 2;
    
    if (hp > hpHalf) {
      colorR = 255 - (int)((hp - hpHalf) / (maxHp / 2) * 255);
      colorG = 255;
    } else {
      colorR = 255;
      colorG = (int)(hp / (maxHp / 2) * 255);
    }
    return new Color(colorR, colorG, colorB);
  }
}
