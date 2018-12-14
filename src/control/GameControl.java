/**   
 * 接受玩家键盘事件
 * 控制画面
 * 控制游戏逻辑
 * 
 * @Title: GameControl.java 
 * @Package control 
 * @Description: 
 * @author zhm
 * @date 2018年2月14日 下午4:27:30 
 * @version  
 */
package control;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.IData;
import dto.GameDto;
import dto.Player;
import service.GameService;
import service.GameTetris;
import ui.window.JFrameConfig;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameControl {

  private final GameDto dto;
  /**
   * 数据访问接口A
   */
  private IData dataA;

  /**
   * 数据访问接口B
   */
  private IData dataB;

  /**
   * 游戏配置层
   */
  private JPanelGame panelGame;

  /**
   * 游戏逻辑层
   */
  private GameService gameService;

  /**
   * 游戏控制设置窗口
   */
  private JFrameConfig frameConfig;

  /**
   * 保存分数窗口
   */
  private JFrameSavePoint frameSavePoint;
  /**
   * 游戏行为控制
   */
  private Map<Integer, Method> actionList;

  /**
   * 游戏线程
   */
  private Thread gameThread = null;



  public GameControl() {
    // 创建游戏数据源
    this.dto = new GameDto();
    // 创建游戏逻辑快（连接游戏数据源）
    this.gameService = new GameTetris(dto);
    // 从数据接口A获得数据库记录
    this.dataA = createDataObject(GameConfig.getDataConfig().getDataA());
    // 设置数据库记录到游戏
    this.dto.setDbRecord(dataA.loadData());
    // 从数据接口B获得本地磁盘记录
    this.dataB = createDataObject(GameConfig.getDataConfig().getDataB());

    // 设置本地磁盘记录到游戏
    this.dto.setDiskRecord(dataB.loadData());


    // 创建游戏面板
    this.panelGame = new JPanelGame(this, dto);
    // 初始化游戏行为
//    actionList = new HashMap<>();

    // 读取用户控制设置
    setControlConfig();

    // 初始化用户配置窗口
    this.frameConfig = new JFrameConfig(this);
    // 初始化保存分数窗口
    this.frameSavePoint = new JFrameSavePoint(this);
    // 创建游戏靠窗口，安装游戏面板
    new JFrameGame(this.panelGame);




  }

  /**
   * 读取用户控制设置
   */
  private void setControlConfig() {
    // 创建键盘码与方法名的映射数组
    this.actionList = new HashMap<Integer, Method>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"))) {
      HashMap<Integer, String> cfgSet = (HashMap) ois.readObject();
      Set<Map.Entry<Integer, String>> entrySet =cfgSet.entrySet();
      for (Map.Entry<Integer, String> entry : entrySet) {
        actionList.put(entry.getKey(), this.gameService.getClass().getMethod(entry.getValue()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

    /**
   * 创建数据对象
   * @param cfg
   * @return
   */
  private IData createDataObject(DataInterfaceConfig cfg) {
    try {
      // 获得类对象
      Class<?> cls = Class.forName(cfg.getClassName());
      // 获得构造器
      Constructor<?> ctr = cls.getConstructor(HashMap.class);
      // 创建对象
      return (IData) ctr.newInstance(cfg.getParam());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  /**
   * 根据玩家控制来决定行为
   * @param keyCode
   */
  public void actionByKeyCode(int keyCode) {
    try {
      if(this.actionList.containsKey(keyCode)) {
        this.actionList.get(keyCode).invoke(this.gameService);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.panelGame.repaint();
  }

  /**
   * 显示玩家控制窗口
   */
  public void showUserConfig() {
    this.frameConfig.setVisible(true);
  }

  /**
   * 子窗口关闭事件
   */
  public void setOver() {
    this.panelGame.repaint();
    this.setControlConfig();
  }
     
  /**
   * 开始按钮事件
   */
  public void start() {
    // 面板按钮设置为不可点击
    this.panelGame.buttonSwitch(false);
    // 关闭窗口
    this.frameConfig.setVisible(false);
    this.frameSavePoint.setVisible(false);
    // 游戏数据初始化
    this.gameService.startGame();
    // 创建线程对象
    this.gameThread = new MainThread();

    // 启动线程
    this.gameThread.start();
    // 刷新画面
    this.panelGame.repaint();
  }

  /**
   * 失败之后的处理
   */
  public void afterlose() {
    if(!this.dto.isCheat()) {
      // 显示保存得分窗口
      this.frameSavePoint.show(this.dto.getNowPoint());
    }
    // 使按钮可以点击
    this.panelGame.buttonSwitch(true);
  }

  /**
   * 保存分数
   * @param name
   */
  public void savePoint(String name) {
    Player pla = new Player(name, this.dto.getNowPoint());
    // 保存记录到数据库
    this.dataA.saveData(pla);
    // 保存记录到本地磁盘
    this.dataB.saveData(pla);

    // 设置数据库记录到游戏
    this.dto.setDbRecord(dataA.loadData());
    // 设置磁盘记录到游戏
    this.dto.setDiskRecord(dataB.loadData());
  }

  private class MainThread extends Thread {
    @Override
    public void run() {
      // 刷新画面
      panelGame.repaint();
      // 主循环
      while(dto.isStart()) {
        try {
          // 线程睡眠
          Thread.sleep(dto.getSleepTime());
          // 如果暂停，那么不执行主行为
          if(dto.isPause()) {
            continue;
          }
          // 游戏主行为
        gameService.mainAction();
          // 刷新画面
        panelGame.repaint();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      afterlose();
    }
  }
}
