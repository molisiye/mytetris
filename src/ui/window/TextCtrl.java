package ui.window;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextCtrl extends JTextField {
  private int keyCode;
  private final String methodName;

  public TextCtrl(int x, int y, int w, int h, String methodName) {
    // 设置文本框位置
    this.setBounds(x, y, w, h);
    // 初始化keycode
//    this.keyCode = keyCode;
//    this.setText(KeyEvent.getKeyText(this.keyCode));
    // 初始化方法名
    this.methodName = methodName;
    // 增加事件监听
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        setKeyCode(e.getKeyCode());
      }
    });
  }

  public void setKeyCode(int keyCode) {
    this.keyCode = keyCode;
    this.setText(KeyEvent.getKeyText(this.keyCode));
  }
}
