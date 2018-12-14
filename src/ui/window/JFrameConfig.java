package ui.window;


import control.GameControl;
import util.FrameUtil;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JFrameConfig extends JFrame {
  private JButton btnOk = new JButton("确定");
  private JButton btnCancel = new JButton("取消");
  private JButton btnUse = new JButton("应用");
  //  private TextCtrl test = new TextCtrl(0, 50, 64, 20);
  private TextCtrl[] keyTexts = new TextCtrl[8];
  private JLabel errorMsg = new JLabel();
  private final static Image IMG_PSP = new ImageIcon("data/imgpsp.jpg").getImage();
  private final String PATH = "data/control.dat";

  private GameControl gameControl;

  private final static String[] METHOD_NAMES = {
          "keyRight", "keyUp", "keyLeft", "keyDown",
          "keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown"
  };
  private JList skinList = null;
  private DefaultListModel skinData = null;
  private JPanel skinView = null;

  public JFrameConfig(GameControl gameControl) {
    // 获得游戏控制器对象
    this.gameControl = gameControl;
    // 设置布局管理器为"边界布局"
    this.setLayout(new BorderLayout());
    // 设置标题
    this.setTitle("用户设置");
    // 初始化按键输入框
    this.initKeyText();
    // 添加按钮面板
    this.add(this.createButtonPanel(), BorderLayout.SOUTH);

    // 添加主面板
    this.add(this.createMainPanel(), BorderLayout.CENTER);

    this.setSize(645, 350);

    // 居中
    FrameUtil.setFrameCenter(this);
    // TODO p>测试用
//    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(false);
  }

  /**
   * 初始化按键输入框
   */
  private void initKeyText() {
    int x = 20;
    int y = 52;
    int w = 64;
    int h = 20;


    for (int i = 0; i < 4; i++) {
      keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
      y += 28;
    }

    x = 560;
    y = 52;
    for (int i = 4; i < 8; i++) {
      keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);

      y += 28;
    }


    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
      HashMap<Integer, String> cfgSet = (HashMap) ois.readObject();
      Set<Map.Entry<Integer, String>> entrySet = cfgSet.entrySet();
      for (Map.Entry<Integer, String> entry : entrySet) {
        for (TextCtrl keyText : keyTexts) {
          if (keyText.getMethodName().equals(entry.getValue())) {
            keyText.setKeyCode(entry.getKey());
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    ;
  }

  /**
   * 创建选项卡面板
   * @return
   */
  private JTabbedPane createMainPanel() {
    JTabbedPane jtp = new JTabbedPane();
    jtp.addTab("控制设置", this.createControlPanel());
    jtp.addTab("皮肤设置", this.createSkinPanel());
    return jtp;
  }

  /**
   * 玩家皮肤面板
   * @return
   */
  private JPanel createSkinPanel() {
    JPanel panel = new JPanel(new BorderLayout());

//    this.skinData.addElement("1111");
//    this.skinData.addElement("111");
//    this.skinData.addElement("11");
//    this.skinData.addElement("1");
    this.skinList = new JList();
    this.skinView = new JPanel(){
      @Override
      public void paintComponent(Graphics g) {
//        Image tem_skingImg = null;
//        g.drawImage(tem_skingImg, 0, 0, null);
      }
    };
    panel.add(new JScrollPane(this.skinList), BorderLayout.WEST);
    panel.add(this.skinView, BorderLayout.CENTER);
    return panel;
  }

  /**
   * 玩家控制设置面板
   * @return
   */
  private JPanel createButtonPanel() {
    // 创建按钮面板，流式布局
    JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // 给确定按钮增加事件监听
    this.btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (writeConfig()) {
          setVisible(false);
          gameControl.setOver();
        }
      }
    });

    this.errorMsg.setForeground(Color.red);
    jp.add(this.errorMsg);
    jp.add(this.btnOk);
    // 给取消按钮增加事件监听
    this.btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }


    });
    jp.add(this.btnCancel);

    // 给应用按钮增加事件监听
    this.btnUse.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        writeConfig();
      }
    });
    jp.add(this.btnUse);
    return jp;
  }

  /**
   * 玩家控制器设置面板
   */
  private JPanel createControlPanel() {
    JPanel jp = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        g.drawImage(IMG_PSP, 0, 0, null);
      }
    };
    // 设置布局管理器
    jp.setLayout(null);
    for (int i = 0; i < this.keyTexts.length; i++) {
      jp.add(this.keyTexts[i]);
    }
    return jp;
  }


  /**
   * 写入游戏配置
   */
  private boolean writeConfig() {
    HashMap<Integer, String> keySet = new HashMap<>();
    for (TextCtrl keyText : this.keyTexts) {
      if (keyText.getKeyCode() == 0) {
        this.errorMsg.setText("错误按键");
        return false;
      }
      keySet.put(keyText.getKeyCode(), keyText.getMethodName());
    }

    if (keySet.size() != 8) {
      this.errorMsg.setText("重复按键");
      return false;
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
      oos.writeObject(keySet);
//      System.out.println("写入成功");
    } catch (Exception e) {
      this.errorMsg.setText(e.getMessage());
      return false;
    }
    this.errorMsg.setText(null);
    return true;
  }


}
