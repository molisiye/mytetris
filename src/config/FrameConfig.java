package config;

import lombok.Getter;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FrameConfig {
/**
* 窗口宽度
*/
private int width;

/**
* 窗口高度
*/
private int height;

/**
* 边框内边距
*/
private int padding;

/**
* 边框尺寸
*/
private int border;

/**
* 窗口拔高
*/
private int windowUp;

/**
* 窗口标题
*/
private String title;

  private final int sizeRol;

  private final int loseIdx;

  /**
 * 图层属性
 */
private List<LayerConfig> layersConfig;

  /**
   * 按钮属性
   */
  private ButtonConfig buttonConfig;

  public FrameConfig(Element frame) {
 // 获取窗口宽度
    this.width = Integer.parseInt(frame.attributeValue("width"));
    // 获取窗口高度
    this.height = Integer.parseInt(frame.attributeValue("height"));
    // 获取边框内边距
    this.padding = Integer.parseInt(frame.attributeValue("padding"));
    // 获取边框粗细
    this.border  = Integer.parseInt(frame.attributeValue("border"));
    // 获取窗口拔高
    this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
    // 获取窗口标题
    this.title = frame.attributeValue("title");
    // 图块左位移偏移量
    this.sizeRol = Integer.parseInt(frame.attributeValue("sizeRol"));
    // 游戏失败图片
    this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));
    // 获取窗体属性
    @SuppressWarnings("unchecked")
    List<Element> layers = frame.elements("layer");
    layersConfig = new ArrayList<LayerConfig>();
    // 获取所有窗体属性
    for (Element layer : layers) {
      // 设置单个窗体属性
      LayerConfig lc = new LayerConfig(
          layer.attributeValue("class"),
          Integer.parseInt(layer.attributeValue("x")),
          Integer.parseInt(layer.attributeValue("y")),
          Integer.parseInt(layer.attributeValue("w")),
          Integer.parseInt(layer.attributeValue("h"))
      );
      layersConfig.add(lc);
    }

    // 初始化按钮属性
    buttonConfig = new ButtonConfig(frame.element("button"));
  }
}
