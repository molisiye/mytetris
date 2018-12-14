/**
 * @Title: Control.java
 * @Package control
 * @Description:
 * @author zhm
 * @date 2018年2月14日 下午4:10:02
 * @version
 */
package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter {
  private GameControl gameControl;

  public PlayerControl(GameControl gameControl) {
    this.gameControl = gameControl;

  }

  /**
   * 键盘按下事件
   */
  @Override
  public void keyPressed(KeyEvent e) {
    this.gameControl.actionByKeyCode(e.getKeyCode());

    // 这样的枚举写法不好
    // switch case 新手专用，考试专用，高玩远离
//    switch (e.getKeyCode()) {
//      case KeyEvent.VK_E:
//        this.gameControl.keyUp();
//        break;
//      case KeyEvent.VK_D:
//        this.gameControl.keyDown();
//        break;
//      case KeyEvent.VK_S:
//        this.gameControl.keyLeft();
//        break;
//      case KeyEvent.VK_F:
//        this.gameControl.keyRight();
//        break;
//      case KeyEvent.VK_UP:
//        this.gameControl.testLevelUp();
//        break;
//      default:
//        break;
//    }
  }

}
