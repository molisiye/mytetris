/**   
 * @Title: Lay.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月10日 下午11:29:06 
 * @version  
 */
package ui;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 绘制窗口
 */
public abstract class Layer {
  /**
   * 内边距
   */
  protected static final int PADDING;
  
  /**
   * 边框宽度
   */
  protected static final int BORDER;
  
  static {
    // 获得游戏配置
    FrameConfig fCfg = GameConfig.getFrameConfig();
    PADDING = fCfg.getPadding();
    BORDER = fCfg.getBorder();
  }
  
  private static int WINDOW_WIDTH = Img.WINDOW.getWidth(null);
  private static int WINDOW_HEIGHT = Img.WINDOW.getHeight(null);
  

  /**
   * 数字切片的宽度
   */
  protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null) / 10;
  
  /**
   * 数字切片的高度
   */
  private static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);

  
  /**
   * 窗口左上角x坐标
   */
  protected int x;
  /**
   * 窗口左上角y坐标
   */
  protected int y;
  /**
   * 窗口宽度
   */
  protected int w;
  /**
   * 窗口高度
   */
  protected int h;
  

  /**
   * 矩形值槽高度
   */
  protected static final int IMG_RECT_H= Img.RECT.getHeight(null);
  
  /**
   * 矩形值槽图片宽度
   */
  private static final int IMG_RECT_W = Img.RECT.getWidth(null);
  
  /**
   * 矩形值槽宽度
   */
  private final int rectW;
  /**
   * 字体
   */
  private static final Font DEF_FONT = new Font("苹方", Font.BOLD, 18);
  
  // 游戏数据
  protected GameDto dto = null;
  
  protected Layer(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.rectW = this.w - (PADDING << 1);
  }
  
  /**
   * 绘制窗口
   * @Title: createWindow
   * @Description: void
   */
  protected void createWindow(Graphics g) {
    // 左上
    g.drawImage(Img.WINDOW, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER, null);
    // 中上
    g.drawImage(Img.WINDOW, x + BORDER, y, x + w - BORDER, y + BORDER, BORDER, 0, WINDOW_WIDTH - BORDER, BORDER, null);
    // 右上
    g.drawImage(Img.WINDOW, x + w - BORDER, y, x + w, y + BORDER, WINDOW_WIDTH - BORDER, 0, WINDOW_WIDTH, BORDER, null);
    // 左中
    g.drawImage(Img.WINDOW, x, y + BORDER, x + BORDER, y + h - BORDER, 0, BORDER, BORDER, WINDOW_HEIGHT - BORDER, null);
    // 中
    g.drawImage(Img.WINDOW, x + BORDER, y + BORDER, x + w - BORDER, y + h - BORDER, BORDER, BORDER, WINDOW_WIDTH - BORDER, WINDOW_HEIGHT - BORDER,  null);
    // 右中
    g.drawImage(Img.WINDOW, x + w - BORDER, y + BORDER, x + w, y + h - BORDER, WINDOW_WIDTH - BORDER, BORDER, WINDOW_WIDTH,WINDOW_HEIGHT - BORDER, null);
    // 左下
    g.drawImage(Img.WINDOW, x, y + h - BORDER, x + BORDER, y + h, 0,  WINDOW_HEIGHT - BORDER, BORDER, WINDOW_HEIGHT, null);
    // 中下
    g.drawImage(Img.WINDOW, x + BORDER, y + h - BORDER, x + w - BORDER, y +h, BORDER, WINDOW_HEIGHT - BORDER, WINDOW_WIDTH - BORDER, WINDOW_HEIGHT, null);
    // 右下
    g.drawImage(Img.WINDOW, x + w - BORDER, y + h - BORDER, x + w, y +h, WINDOW_WIDTH - BORDER, WINDOW_HEIGHT - BORDER, WINDOW_WIDTH, WINDOW_HEIGHT, null);
  
  }
 
  /**
   * 刷新游戏具体内容
   * @Title: paint
   * @Description:
   * @param g void
   */
  abstract public void paint(Graphics g);

  public void setDto(GameDto dto) {
    this.dto = dto;
  }
  
  
  /**
   * 显示数字
   * 
   * @Title: drawNumber
   * @Description:
   * @param x 左上角x坐标
   * @param y 左上角y坐标
   * @param num 要显示的数字
   * @param g void 画笔对象
   */
  protected void drawNumberLeftPad(int x, int y, int num, int maxBit, Graphics g) {
    // 把要打印的数字转换成字符串
    String strNum = Integer.toString(num);
    // 循环绘制数字右对齐
    for (int i = 0; i < maxBit; i++) {
      // 判断是否满足绘制条件
      if (maxBit - i <= strNum.length()) {
        // 获得数字在字符串中的下标
        int idx = i - maxBit +strNum.length();
        // 把数字num中的每一位取出
        int bit = strNum.charAt(idx) - '0';
        // 绘制数字
        g.drawImage(Img.NUMBER, this.x + x + IMG_NUMBER_W * i, this.y + y, this.x + x + IMG_NUMBER_W * (i + 1),
            this.y + y + IMG_NUMBER_H,
            bit * IMG_NUMBER_W, 0, (bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
      }
    }
  }
  
  /**
   * 绘制值槽
   * @Title: drawRect
   * @Description:
   * @param title
   * @param number
   * @param g void
   */
  protected void drawRect(int y, String title, String number, double percent, Graphics g) {
    // 各种值的初始化
    int rect_x = this.x + PADDING;
    int rect_y = this.y + y;
    // 绘制背景
    g.setColor(Color.BLACK);
    g.fillRect(rect_x, rect_y, this.rectW, IMG_RECT_H+ 4);
    g.setColor(Color.WHITE);
    g.fillRect(rect_x + 1, rect_y + 1, this.rectW - 2, IMG_RECT_H+ 2 );
    g.setColor(Color.BLACK);
    g.fillRect(rect_x + 2, rect_y + 2, this.rectW - 4, IMG_RECT_H);


    // 求出宽度
    int w = (int)(percent * (this.rectW - 4));
    // 求出颜色
    int subIdx = (int)(percent* IMG_RECT_W) - 1;
    // 绘制值槽
    g.drawImage(Img.RECT, rect_x + 2, rect_y + 2, 
        rect_x + 2 + w, rect_y + 2 + IMG_RECT_H,
        subIdx, 0, subIdx + 1, IMG_RECT_H, null);
    
    g.setColor(Color.WHITE);
    g.setFont(DEF_FONT);
    g.drawString(title, rect_x + 4, rect_y + 22);
    if (number != null) {
      g.drawString(number, rect_x + 232, rect_y + 22);
    }
  }
  
  /** 
   * 正中绘图
   * 
   * @Title: drawImageAtCenter
   * @Description:
   * @param image
   * @param g void
   */
  protected void drawImageAtCenter(Image image, Graphics g) {
    int imgW = image.getWidth(null);
    int imgH = image.getHeight(null);
    g.drawImage(image, this.x + (this.w - imgW >> 1), this.y + (this.h - imgH >> 1), null);
  }
  
}
