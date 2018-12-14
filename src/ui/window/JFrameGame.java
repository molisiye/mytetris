/**   
 * @Title: FrameGame.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月7日 下午9:41:13 
 * @version  
 */
package ui.window;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;

public class JFrameGame extends JFrame {
//  private GameDto dto = new GameDto();

  public JFrameGame(JPanelGame panelGame) {
    // 获得游戏配置
    FrameConfig fCfg = GameConfig.getFrameConfig();
    // 设置标题
    this.setTitle(fCfg.getTitle());
    // 设计默认关闭属性（程雪结束）
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 设置窗口大小
    this.setSize(fCfg.getWidth(), fCfg.getHeight());
    // 不允许用户改变窗口大小
    this.setResizable(false);
    // 居中
    FrameUtil.setFrameCenter(this);
    // 设计默认的Panel
    this.setContentPane(panelGame);
    this.setVisible(true);
  }
}
