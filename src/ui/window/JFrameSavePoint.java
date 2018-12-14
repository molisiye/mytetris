package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.GameControl;
import util.FrameUtil;

public class JFrameSavePoint extends JFrame {
  private JButton btnOk = null;
  private JLabel point;
  private JTextField txName;
  private JLabel errMsg;
  private GameControl gameControl;

  public JFrameSavePoint(GameControl gameControl) {
    this.gameControl = gameControl;
    this.setTitle("保存记录");
    this.setSize(265, 128);
    FrameUtil.setFrameCenter(this);
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.createCom();
    this.createAction();
  }

  /**
   * 显示窗口
   */
  public void show(int point) {
    this.point.setText("您的得分：" + point);
    this.setVisible(true);
  }

  /**
   * 创建事件监听
   */
  private void createAction() {
    this.btnOk.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO 还可以加入其它文字验证
        String name = txName.getText();
        if (name.length() > 16 || name == null || "".equals(name)) {
          errMsg.setText("请输入16位以下的名字");
        } else {
          gameControl.savePoint(name);
        }
      }
    });
  }

  /**
   * 初始化控件
   */
  private void createCom() {
    // 创建北部面板（流式布局）
    JPanel north = new JPanel();
    // 创建分数文字
    this.point = new JLabel();
    // 添加分数文字到北部面板
    north.add(point);
    ;
    // 创建错误信息控件
    this.errMsg = new JLabel();
    this.errMsg.setForeground(Color.RED);
    // 添加错误信息到北部面板
    north.add(this.errMsg);

    // 北部面板添加到主面板
    this.add(north, BorderLayout.NORTH);
    // 创建中部面板（流式布局）
    JPanel center = new JPanel();
    // 创建文本框
    this.txName = new JTextField(10);
    // 设置文字
    center.add(new JLabel("您的名字："));
    // 文本框添加到中部面板
    center.add(this.txName);
    // 中部面板添加到主面板
    this.add(center, BorderLayout.CENTER);


    // 创建南部面板（流式布局）
    JPanel south = new JPanel();
    // 创建确定按钮
    this.btnOk = new JButton("确定");
    // 按钮添加到面板
    south.add(btnOk);
    // 南部面板添加到主面板
    this.add(south, BorderLayout.SOUTH);
  }
}
