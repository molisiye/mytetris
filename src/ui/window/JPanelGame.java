/**
 * @Title: PanelGame.java
 * @Package ui
 * @Description:
 * @author zhm
 * @date 2018年2月10日 下午10:10:23
 * @version
 */
package ui.window;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import lombok.Setter;
import ui.Img;
import ui.Layer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class JPanelGame extends JPanel {
  private ArrayList<Layer> layers = null;
  private JButton btnStart;
  private JButton btnConfig;
  @Setter
  private GameControl gameControl = null;
  private PlayerControl playerControl = null;

  private static final int BTN_SIZE_W = GameConfig.getFrameConfig().getButtonConfig().getButtonW();
  private static final int BTN_SIZE_H = GameConfig.getFrameConfig().getButtonConfig().getButtonH();

  public JPanelGame(GameControl gameControl, GameDto dto) {
    // 链接游戏控制器
    this.gameControl = gameControl;
    // 设置布局管理器为自由布局
    this.setLayout(null);
//    this.setPlayerControl(playerControl);
//    this.setGameControl(gameControl);
    // 初始化组件
    this.initComponent();
    // 初始化层
    this.initLayer(dto);
    // 安装键盘监听器
    this.addKeyListener(new PlayerControl(gameControl));

  }

  /**
   * 初始化按钮
   */
//  private void initButton() {

//  }

  // lays = n w Layer[] {
  // new LayerBackground(0, 0, 0, 0),
  // new LayDataBase(40, 32, 334, 279),
  // new LayDisk(40, 343, 334, 279),
  // new LayerGame(414, 32, 334, 590),
  // new LayerButton(788, 32, 334, 124),
  // new LayerLevel(788, 188, 176, 148),
  // new LayerNext(964, 188, 158, 148),
  // new LayerPoint(788, 368, 334, 200)
  // };
  
  /**
   * 安装玩家控制器
   */
//  public void setPlayerControl(PlayerControl control) {
//    this.addKeyListener(control);
//  }
  
  /**
   * 初始化组件
   * @Title: initComponent
   * @Description: void
   */
  private void initComponent() {
    // 创建确定按钮
    this.btnStart = new JButton(Img.BTN_START);
    // 设置开始按钮位置
    btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig().getStartX(),
            GameConfig.getFrameConfig().getButtonConfig().getStartY(),
            BTN_SIZE_W, BTN_SIZE_H);
    // 给开始按钮添加事件监听
    btnStart.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        gameControl.start();
      }
      
    });
    // 添加开始按钮到面板
    this.add(btnStart);
    // 创建设置按钮
    this.btnConfig = new JButton(Img.BTN_CONFIG);
    // 设置设置按钮位置
    btnConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig().getUseConfigX(),
            GameConfig.getFrameConfig().getButtonConfig().getUseConfigY(),
            BTN_SIZE_W, BTN_SIZE_H);

    this.btnConfig.addActionListener(e -> gameControl.showUserConfig());
    // 添加设置按钮到面板
    this.add(btnConfig);
  }

  /**
   * 初始化层
   * @Title: initLayer
   * @Description: void
   */
  private void initLayer(GameDto dto) {
 // 获得游戏配置
    FrameConfig fCfg = GameConfig.getFrameConfig();
    // 获得层配置
    List<LayerConfig> layersCfg = fCfg.getLayersConfig();
    // 创建游戏层数组
    layers = new ArrayList<Layer>(layersCfg.size());
    // 创建所有层对象 
    for (LayerConfig layerCfg : layersCfg) {
      try {
        // 获得类对象
        Class<?> cls = Class.forName(layerCfg.getClassName());
        // 获得构造函数
        Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
        // 调用构造函数创建对象
        Layer l = (Layer)ctr.newInstance(
            layerCfg.getX(),
            layerCfg.getY(),
            layerCfg.getW(),
            layerCfg.getH()
            );
        // 设置游戏数据对象
        l.setDto(dto);
        // 把创建的Layer对象放入集合
        layers.add(l);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    // 调用基本方法
    super.paintComponent(g);
    // 绘制游戏界面
    for (int i = 0; i < layers.size(); layers.get(i++).paint(g));
    
    // 返回焦点
    this.requestFocus();

    // Image img = new ImageIcon("graphics/window/window.png").getImage();
    // g.drawImage(img, 0, 0, null);
    // int x =32;
    // int y = 32;
    // int w = 256;
    // int h = 128;
    // int size = 7;
    // int imgWidth = img.getWidth(null);
    // int imgHeight = img.getHeight(null);
    // // 左上
    // g.drawImage(img, x, y, x + size, y + size, 0, 0, size, size, null);
    // // 中上
    // g.drawImage(img, x + size, y, x + w - size, y + size, size, 0, imgWidth - size, size, null);
    // // 右上
    // g.drawImage(img, x + w - size, y, x + w, y + size, imgWidth - size, 0, imgWidth, size, null);
    // // 左中
    // g.drawImage(img, x, y + size, x + size, y + h - size, 0, size, size, imgHeight - size, null);
    // // 中
    // g.drawImage(img, x + size, y + size, x + w - size, y + h - size, size, size, imgWidth - size, imgHeight - size,
    // null);
    // // 右中
    // g.drawImage(img, x + w - size, y + size, x + w, y + h - size, imgWidth - size, size, imgWidth,imgHeight - size,
    // null);
    // // 左下
    // g.drawImage(img, x, y + h - size, x + size, y + h, 0, imgHeight - size, size, imgHeight, null);
    // // 中下
    // g.drawImage(img, x + size, y + h - size, x + w - size, y +h, size, imgHeight - size, imgWidth - size, imgHeight,
    // null);
    // // 右下
    // g.drawImage(img, x + w - size, y + h - size, x + w, y +h, imgWidth - size, imgHeight - size, imgWidth, imgHeight,
    // null);
  }
  
  /**
   * 控制按钮是否可点击
   */
  public void buttonSwitch(boolean onOff) {
    this.btnConfig.setEnabled(onOff);
    this.btnStart.setEnabled(onOff);
  }
}
