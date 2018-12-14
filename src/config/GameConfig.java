/**
 * @Title: GameConfig.java
 * @Package config
 * @Description:
 * @author zhm
 * @date 2018年2月12日 下午4:46:47
 * @version
 */
package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏配置器
 * @author zhm
 */

//@Getter
public class GameConfig {
//  /**
//   * 窗口宽度
//   */
//  private int width;
//
//  /**
//   * 窗口高度
//   */
//  private int height;
//
//  /**
//   * 边框内边距
//   */
//  private int padding;
//
//  /**
//   * 边框尺寸
//   */
//  private int windowSize;
//  
//  /**
//   * 窗口拔高
//   */
//  private int windowUp;
//  
//  /**
//   * 窗口标题
//   */
//  private String title;

  private static FrameConfig FRAME_CONFIG= null;
  
  private static SystemConfig SYSTEM_CONFIG = null;
  
  private static DataConfig DATA_CONFIG = null;
  
  static {
    // 创建xml读取器
    SAXReader reader = new SAXReader();
    // 读取xml文件
    Document doc =  null;
    try {
      doc = reader.read("data/cfg.xml");
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    // 获得xml文件的根节点
    Element game = doc.getRootElement();
    // 创建界面配置对象
    FRAME_CONFIG = new FrameConfig(game.element("frame"));
    // 创建系统对象
    SYSTEM_CONFIG = new SystemConfig(game.element("system"));
    // 创建数据访问配置对象
    DATA_CONFIG = new DataConfig(game.element("data"));
  }
  

  /**
   * 构造函数私有化
   * 
   */
  private GameConfig() {}

  /**
   * 获得窗口配置
   * @return
   */
  public static FrameConfig getFrameConfig() {
    return FRAME_CONFIG;
  }

  /**
   * 获得系统配置
   * @return
   */
  public static SystemConfig getSystemCnfig() {
    return SYSTEM_CONFIG;
  }
  
  /**
   * 获得数据访问配置
   * @return
   */
  public static DataConfig getDataConfig() {
    return DATA_CONFIG;
  }
}
