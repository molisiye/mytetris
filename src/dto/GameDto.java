/**   
 * @Title: GameDto.java 
 * @Package dto 
 * @Description: 
 * @author zhm
 * @date 2018年2月14日 下午4:51:39 
 * @version  
 */
package dto;

import config.GameConfig;
import entity.GameAct;
import lombok.Data;
import util.GameFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class GameDto {

  public static final int GAMEZONE_W = GameConfig.getSystemCnfig().getMaxX() + 1;

  public static final int GAMEZONE_H = GameConfig.getSystemCnfig().getMaxY() + 1;
  ;
  /**
   * 数据库记录
   */
  private List<Player> dbRecord;
  /**
   * 本地记录
   */
  private List<Player> diskRecord;
  /**
   * 游戏地图
   */
  private boolean[][] gameMap;
  /**
   * 下落方块
   */
  private GameAct gameAct;
  /**
   * 下一个方块
   */
  private int next;
  /**
   * 等级
   */
  private int level;
  
  private int nowLevel;
  
  /**
   * 分数
   */
  private int nowPoint;
  /**
   * 消行
   */
  private int nowRemoveLine;
  
  /**
   * 游戏开始标记
   */
  private boolean start = false;

  /**
   * 是否显示阴影
   */
  private boolean showShadow;

  /**
   * 是否使用作弊
   */
  private boolean cheat;

  /**
   * 线程等待时间
   */
  private long sleepTime;

  public void changePause() {
    this.pause = !this.pause;
  }

  /**
   * 暂停
   */
  private boolean pause = false;
  
  public GameDto() {
    dtoInit();
  }
  
  public void dtoInit() {
    this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
    this.nowLevel = 1;
    this.nowPoint = 0;
    this.nowRemoveLine = 0;
    this.pause = false;
    this.cheat = false;
    this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowLevel);
  }
  
//  public List<Player> getDbRecord() {
//    return dbRecord;
//  }
  public void setDbRecord(List<Player> dbRecord) {
    this.dbRecord = setFillRecord(dbRecord);
  }
//  public List<Player> getDiskRecord() {
//    return diskRecord;
//  }
  public void setDiskRecord(List<Player> diskRecord) {
    this.diskRecord = setFillRecord(diskRecord);
  }
  
  private List<Player> setFillRecord(List<Player> players) {
    // 如果进来的是空，就创建
    if(dbRecord == null) {
      dbRecord = new ArrayList<>();
    }
//    如果记录数小于5，name就加到5条为止
    while(dbRecord.size() < 5) {
      dbRecord.add(new Player("No data", 0));
    }
    Collections.sort(players);
    return players;
  }

  public void setNowLevel(int nowLevel) {
    this.nowLevel = nowLevel;
    // 计算线程睡眠时间（下落速度）
    this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowLevel);
  }

  public void setShowShadow() {
    this.showShadow = !this.showShadow;
  }
//  public boolean[][] getGameMap() {
//    return gameMap;
//  }
//  public void setGameMap(boolean[][] gameMap) {
//    this.gameMap = gameMap;
//  }
//  public GameAct getGameAct() {
//    return gameAct;
//  }
//  public void setGameAct(GameAct gameAct) {
//    this.gameAct = gameAct;
//  }
//  public int getNext() {
//    return next;
//  }
//  public void setNext(int next) {
//    this.next = next;
//  }
//  public int getLevel() {
//    return level;
//  }
//  public void setLevel(int level) {
//    this.level = level;
//  }
//  public int getNowPoint() {
//    return nowPoint;
//  }
//  public void setNowPoint(int nowPoint) {
//    this.nowPoint = nowPoint;
//  }
//  public int getNowRemoveLine() {
//    return nowRemoveLine;
//  }
//  public void setNowRemoveLine(int nowRemoveLine) {
//    this.nowRemoveLine = nowRemoveLine;
//  }
//
//  public int getNowLevel() {
//    return nowLevel;
//  }
//
//  public void setNowLevel(int nowLevel) {
//    this.nowLevel = nowLevel;
//  }


}
