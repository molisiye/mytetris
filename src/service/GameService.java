package service;

public interface GameService {
  /**
   * 方向键上
   */
  public boolean keyUp();
  /**
   * 方向键下
   */
  public boolean keyDown();
  /**
   * 方向键左
   */
  public boolean keyLeft();
  /**
   * 方向键右
   */
  public boolean keyRight();
  /**
   * 三角
   */
  public boolean keyFunUp();
  /**
   * 大叉
   */
  public boolean keyFunDown();

  /**
   * 方块
   */
  public boolean keyFunLeft();

  /**
   * 圆圈
   */
  public boolean keyFunRight();

  /**
   * 设置数据库对象
   * @param players
   */
//  public void setDbRecord(List<Player> players);

  /**
   * 设置本地数据对象
   * @param players
   */
//  public void setDiskRecord(List<Player> players);
  
  /**
   * 启动主线程，开始游戏
   */
  public void startGame();

  /**
   * 游戏主要行为
   */
  public void mainAction();
}
