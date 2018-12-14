/**   
 * @Title: Img.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年3月9日 下午11:31:26 
 * @version  
 */
package ui;

import config.GameConfig;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Img {
  /**
   * 图片路径
   */
  private static String GRAPHICS_PATH = "graphics/";

  private static final String DEFAULT_PATH = "defaults/";

  public static void setSkin(String path) {
    String skinPath = GRAPHICS_PATH + path;

    // 个人签名
    SIGN = new ImageIcon(skinPath + "string/sign.png").getImage();

    // * 窗口标题（分数）
    POINT = new ImageIcon(skinPath + "string/point.png").getImage();

    // * 窗口标题（消行）
    RMLINE = new ImageIcon(skinPath + "string/rmline.png").getImage();

    // * 矩形值槽
    RECT = new ImageIcon(skinPath + "window/rect.png").getImage();

    // * 数字图片 260 36
    NUMBER = new ImageIcon(skinPath + "string/num.png").getImage();

    // * 窗口图片
    WINDOW = new ImageIcon(skinPath + "window/window.png").getImage();

    // TODO 临时背景
    // public static Image GB_TEMP = new ImageIcon(skinPath +
    // "/background/bg01.jpg").getImage();
    // 数据库窗口标题
    DB = new ImageIcon(skinPath + "string/db.png").getImage();
    // 本地记录窗口标题
    DISK = new ImageIcon(skinPath + "string/disk.png").getImage();
    // 方块图片
    ACT = new ImageIcon(skinPath + "game/rect.png").getImage();

    // * 暂停图片
    PAUSE = new ImageIcon(skinPath + "string/pause.png").getImage();
    // * 标题图片
    LV = new ImageIcon(skinPath + "string/level.png").getImage();

    // * 阴影（消行）
    SHADOW = new ImageIcon(skinPath + "game/shadow.png").getImage();

    // * 开始按钮
    BTN_START = new ImageIcon(skinPath + "string/start.png");

    // * 设置按钮
    BTN_CONFIG = new ImageIcon(skinPath + "string/config.png");

    // 下一个方块图片
    NEXT_ACT = new Image[GameConfig.getSystemCnfig().getTypeConfig().size()];
    for (int i = 0; i < NEXT_ACT.length; i++) {
      NEXT_ACT[i] = new ImageIcon(skinPath + "game/" + i + ".png").getImage();
    }

    // 背景图片数组
    File dir = new File(skinPath + "background");
    File[] files = dir.listFiles();
    BG_LIST = new ArrayList<Image>();
    for (File file : files) {
      if (!file.isDirectory()) {
        // macos系统下需要处理 ".DS_Store" 文件
        if (".DS_Store".equals(file.getName())) {
          continue;
        }
        BG_LIST.add(new ImageIcon(file.getPath()).getImage());
      }
    }
  }

  private Img() {
  }

  /**
   * 个人签名
   */
  public static Image SIGN;

  /**
   * 窗口标题（分数）
   */
  public static Image POINT;

  /**
   * 窗口标题（消行）
   */
  public static Image RMLINE;

  /**
   * 矩形值槽
   */
  public static Image RECT;

  /**
   * 数字图片 260 36
   */
  public static Image NUMBER;

  /**
   * 窗口图片
   */
  public static Image WINDOW;

  public static Image DB;

  public static Image DISK;

  public static Image ACT;

  /**
   * 暂停图片
   */
  public static Image PAUSE;
  /**
   * 标题图片
   */
  public static Image LV;

  /**
   * 阴影（消行）
   */
  public static Image SHADOW;

  /**
   * 开始按钮
   */
  public static ImageIcon BTN_START;

  /**
   * 设置按钮
   */
  public static ImageIcon BTN_CONFIG;

  /**
   * 下一个图片数组
   */
  public static Image[] NEXT_ACT;

  /**
   * 背景
   */
  public static List<Image> BG_LIST;

  /**
   * 初始化图片
   * 
   * @param path
   */
  static {
    setSkin(DEFAULT_PATH);
  }

}
