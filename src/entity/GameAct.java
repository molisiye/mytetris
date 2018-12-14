/**
 * @Title: GameAct.java
 * @Package entity
 * @Description:
 * @author zhm
 * @date 2018年2月14日 下午4:58:00
 * @version
 */
package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;
import lombok.Getter;

@Getter
public class GameAct {
  /**
   * 方块数组
   */
  private Point[] actPoint = null;

  private int typeCode;
  
  private final static int MIN_X = GameConfig.getSystemCnfig().getMinX();
  private final static int MAX_X = GameConfig.getSystemCnfig().getMaxX();
  private final static int MIN_Y = GameConfig.getSystemCnfig().getMinY();
  private final static int MAX_Y = GameConfig.getSystemCnfig().getMaxY();
  
  private final static List<Point[]> TYPE_CONFIG = GameConfig.getSystemCnfig().getTypeConfig();

  private final static List<Boolean> TYPE_ROUND = GameConfig.getSystemCnfig().getTypeRound();

  public GameAct(int typeCode) {
    // TODO 配置文件
    this.init(typeCode);
  }
  
  public void init(int typeCode) {
    this.typeCode = typeCode;
    Point[] points = TYPE_CONFIG.get(typeCode);
    actPoint = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      actPoint[i] = new Point(points[i].x, points[i].y);
    }
  }

  /** 
   * @Title: getActPoint
   * @Description: void
   */
//  public Point[] getActPoint() {
//    return this.actPoint;
//  }

  /**
   * 方块移动
   * 
   * @Title: move
   * @Description:
   * @param moveX x轴偏移量
   * @param moveY y轴偏移量
   */
  public boolean move(int moveX, int moveY, boolean[][] gameMap) {
    // 移动处理
    for (int i = 0; i < actPoint.length; i++) {
      int newX = actPoint[i].x + moveX;
      int newY = actPoint[i].y + moveY;
      if (this.isOverZone(newX, newY, gameMap)) {
        return false;
      }
    }
    for (int i = 0; i < actPoint.length; i++) {
      actPoint[i].x += moveX;
      actPoint[i].y += moveY;
    }
    
    return true;
  }

  /**
   * 方块旋转
   * 
   * 顺时针（与笛卡尔坐标系相反）：
   * A.x = O.y + O.x - B.y
   * A.y = O.y - O.x + B.x
   */
  public void round(boolean[][] gameMap) {
    if (!TYPE_ROUND.get(this.typeCode)) {
      return;
    }
    for (int i = 1; i < actPoint.length; i++) {
      int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
      int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
      // TODO 判断是否可以旋转
      if (this.isOverZone(newX, newY, gameMap)) {
        return;
      }
    }

    for (int i = 1; i < actPoint.length; i++) {
      int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
      int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
      actPoint[i].x = newX;
      actPoint[i].y = newY;
    }
  }

  /**
   * 判斷是否超出边界
   * 
   * @Title: isOverMap
   * @Description:
   * @param x
   * @param y
   * @return boolean
   */
  private boolean isOverZone(int x, int y, boolean[][] gameMap) {
    return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || gameMap[x][y];
  }

  /**
   * 获得方块类型编码
   * @Title: getTypeCode
   * @Description:
   * @return int
   */
//  public int getTypeCode() {
//    return typeCode;
//  }
  
  
}
