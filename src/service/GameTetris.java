/**
 * @Title: GameTetris.java
 * @Package service
 * @Description:
 * @author zhm
 * @date 2018年2月14日 下午4:32:41
 * @version
 */
package service;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

public class GameTetris implements GameService {
  /**
   *
   */
  private GameDto dto;
  /*
   * 随机数生成器
   */
  private Random random = new Random();
  /**
   * 方块种类个数
   */
  private static final int MAX_TYPE = GameConfig.getSystemCnfig().getTypeConfig().size() - 1;

  /**
   * 升级行数
   * 
   * @param dto
   */
  private static final int LEVEL_UP = GameConfig.getSystemCnfig().getLevelUp();

  private static final Map<Integer, Integer> PLUS_POINT = GameConfig.getSystemCnfig().getPlusPoint();

  public GameTetris(GameDto dto) {
    this.dto = dto;
  }

  /**
   * 控制器方向键（上）
   *
   * @Title: keyUp
   * @Description: void
   */
  public boolean keyUp() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    // if (this.canMove(0, -1)) {
    // this.dto.getGameAct().move(0, -1);
    // }
    synchronized(this.dto) {
      this.dto.getGameAct().round(this.dto.getGameMap());
    }
    return true;
  }

  /**
   * 方块操作（下）
   *
   * @Title: keyDown
   * @Description: void
   */
  public boolean keyDown() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    synchronized(this.dto) {
      // 方块向下移动，并判断是否移动成功
      if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
        return false;
      }

      // 获得游戏地图对象
      boolean[][] map = this.dto.getGameMap();
      // 获得方块对象
      Point[] act = this.dto.getGameAct().getActPoint();
      // 将方块堆积到地图数组
      for (int i = 0; i < act.length; i++) {
        map[act[i].x][act[i].y] = true;
      }
      // 判断消行，并计算获得的经验值
      int exp = this.plusExp();
      // 如果加分消行
      if (exp > 0) {
        // 增加经验值
        this.plusPoint(exp);
      }
      // 创建下一个方块
      this.dto.getGameAct().init(this.dto.getNext());
      // 随机生成再下一个方块
      this.dto.setNext(random.nextInt(MAX_TYPE));
      // 检查游戏是否失败
      if (this.isLose()) {
        // 结束游戏
        this.dto.setStart(false);
      }
    }
    return true;
  }


  private boolean isLose() {
    // 获得现在的活动方块
    Point[] actPoints = this.dto.getGameAct().getActPoint();
    // 获得现在的游戏地图
    boolean[][] map = this.dto.getGameMap();
    for(int i = 0; i < actPoints.length; i++) {
      if(map[actPoints[i].x][actPoints[i].y]) {
        return true;
      }
    }
    return false;
  }

  /**
   * 加分升级操作
   * 
   * @param exp
   */
  private void plusPoint(int exp) {
    int level = this.dto.getNowLevel();
    int rmline = this.dto.getNowRemoveLine();
    int point = this.dto.getNowPoint();
    if (rmline % LEVEL_UP + exp >= 20) {
      this.dto.setNowLevel(++level);
    }
    this.dto.setNowRemoveLine(rmline + exp);
    this.dto.setNowPoint(point + PLUS_POINT.get(exp));
  }

  /**
   * 消行操作
   */
  private int plusExp() {
    // 获得游戏地图
    boolean[][] map = this.dto.getGameMap();
    int exp = 0;
    // 扫描游戏地图，查看是否有可消行
    for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
      // 判断是否可消行
      if (isCanRemoveLine(y, map)) {
        // 如果可消行，就执行
        this.removeLine(y, map);
        // 增加经验值
        exp++;
      }
    }
    return exp;
  }

  /**
   * 消行处理
   * 
   * @param row
   * @param map
   */
  private void removeLine(int row, boolean[][] map) {
    for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
      for (int y = row; y > 0; y--) {
        map[x][y] = map[x][y - 1];
      }
      map[x][0] = false;
    }
  }

  /**
   * 判断某一行是否可消除
   * 
   * @param y
   * @param map
   * @return
   */
  private boolean isCanRemoveLine(int y, boolean[][] map) {
    // 单行内对每一个单元格进行扫描
    for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
      if (!map[x][y]) {
        // 如果有一个方格为false则直接到下一行
        return false;
      }
    }
    return true;
  }

  /**
   * 控制器方向键（左）
   *
   * @Title: keyLeft
   * @Description: void
   */
  public boolean keyLeft() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    synchronized(this.dto) {
      this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
    }
    return true;
  }

  /**
   * 控制器方向键（右）
   *
   * @Title: keyRight
   * @Description: void
   */
  public boolean keyRight() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    synchronized(this.dto) {
      this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
    }
    return true;
  }

  /**
   * 作弊
   */
  @Override
  public boolean keyFunUp() {
    this.dto.setCheat(true);
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    this.plusPoint(4);
//    int point = this.dto.getNowPoint();
//    int rmline = this.dto.getNowRemoveLine();
//    int lv = this.dto.getNowLevel();
//    point += 10;
//    rmline += 1;
//    if (rmline % 20 == 0) {
//      lv += 1;
//    }
//    this.dto.setNowPoint(point);
//    this.dto.setNowLevel(lv);
//    this.dto.setNowRemoveLine(rmline);
    return true;
  }

  /**
   * 瞬间下落
   */
  @Override
  public boolean keyFunDown() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    while(!this.keyDown());
    return true;
  }

  @Override
  public boolean keyFunLeft() {
    if(this.dto.isPause()) {
      return true;
    }
    if(!this.dto.isStart()) {
      return true;
    }
    // 阴影开关
    this.dto.setShowShadow();
    return true;
  }

  @Override
  public boolean keyFunRight() {
    // 暂停
//    if(this.dto.isPause()) {
    if(!this.dto.isStart()) {
      return true;
    }
      this.dto.changePause();
//    }

    return true;
  }

//  public void setDbRecord(List<Player> players) {
//    this.dto.setDbRecord(players);
//  }
//
//  public void setDiskRecord(List<Player> players) {
//    this.dto.setDiskRecord(players);
//  }

  @Override
  public void startGame() {
    // 随机生成下一个方块
    this.dto.setNext(random.nextInt(MAX_TYPE));
    // 随机生成现在方块
    this.dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
    // 把游戏状态设为开始    
    this.dto.setStart(true);
    // dto初始化
    this.dto.dtoInit();
  }

  @Override
  public void mainAction() {
    this.keyDown();
  }


}
