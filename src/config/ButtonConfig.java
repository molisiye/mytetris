package config;

import lombok.Getter;
import org.dom4j.Element;

@Getter
public class ButtonConfig {

  private final int buttonW;

  private final int buttonH;
  private final int startX;
  private final int startY;
  private final int useConfigX;
  private final int useConfigY;

  public ButtonConfig(Element button) {
    this.buttonW = Integer.parseInt(button.attributeValue("w"));
    this.buttonH = Integer.parseInt(button.attributeValue("h"));
    this.startX = Integer.parseInt(button.element("start").attributeValue("x"));
    this.startY = Integer.parseInt(button.element("start").attributeValue("y"));
    this.useConfigX = Integer.parseInt(button.element("config").attributeValue("x"));
    this.useConfigY = Integer.parseInt(button.element("config").attributeValue("y"));
  }
}
