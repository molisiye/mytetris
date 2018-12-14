/**
 * @Title: LayGame.java
 * @Package ui
 * @Description:
 * @author zhm
 * @date 2018年2月12日 下午2:38:13
 * @version
 */
package ui;

import config.GameConfig;
import entity.GameAct;

import java.awt.Graphics;
import java.awt.Point;


public class LayerGame extends Layer {

  /**
   * 左位移偏移量
   */
  private static int SIZE_ORL = GameConfig.getFrameConfig().getSizeRol();

  private static final int LEFT_SIDE = 0;

  // 右边距
  private static final int RIGHT_SIDE = GameConfig.getSystemCnfig().getMaxX();

  // 右边距
  private static final int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();
  
  public LayerGame(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    GameAct act = this.dto.getGameAct();
    if (act != null) {
      // 获得方块数组集合
      Point[] points = act.getActPoint();
      // 绘制阴影
      this.drawShadow(points, g);
      // 绘制活动方块
      this.drawMainAct(points, g);
    }
    // 绘制地图
    this.drawMap(g);
    // 暂停
    if(this.dto.isPause()) {
      this.drawImageAtCenter(Img.PAUSE, g);
    }
  }

  /**
   * 绘制活动方块
   * 
   * @param g
   */
  private void drawMainAct(Point[] points, Graphics g) {
    // 获得方块类型编号(0 ~ 6)
    int typeCode = this.dto.getGameAct().getTypeCode();
    // 绘制方块
    for (int i = 0; i < points.length; i++) {
      drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
    }

  }

  /**
   * 绘制地图
   * 
   * @param g
   */
  private void drawMap(Graphics g) {
    // 打印地图
    boolean[][] map = this.dto.getGameMap();
    // 计算当前堆积颜色
    int lv = this.dto.getNowLevel();
    int imgIdx = lv == 0 ? 0 : (lv - 1) % 7 + 1;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if (map[x][y]) {
          drawActByPoint(x, y, imgIdx, g);

        }
      }
    }
  }

  /**
   * 绘制阴影
   * 
   * @param points
   */
  private void drawShadow(Point[] points, Graphics g) {
    if (!this.dto.isShowShadow()) {
      return;
    }
    int leftX = RIGHT_SIDE;
    int rightX = LEFT_SIDE;
    for (Point p : points) {
      leftX = p.x < leftX ? p.x : leftX;
      rightX = p.x > rightX ? p.x : rightX;
    }
    g.drawImage(Img.SHADOW, this.x + BORDER + (leftX << SIZE_ORL), this.y + BORDER, (rightX - leftX + 1) << SIZE_ORL,
        this.h - (BORDER << 1), null);
  }

  private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
    imgIdx = this.dto.isStart() ? imgIdx : LOSE_IDX;
    g.drawImage(Img.ACT, this.x + (x << SIZE_ORL) + BORDER, this.y + (y << SIZE_ORL) + BORDER, this.x + (x + 1 << SIZE_ORL) + BORDER,
        this.y + (y + 1 << SIZE_ORL) + BORDER, imgIdx << SIZE_ORL, 0, (imgIdx + 1) << SIZE_ORL, 1 << SIZE_ORL, null);
  }
}
