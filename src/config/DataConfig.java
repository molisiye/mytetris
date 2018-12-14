package config;

import org.dom4j.Element;

import lombok.Getter;

@Getter
public class DataConfig {
  private final int maxRow;
  private final DataInterfaceConfig dataA;
  private final DataInterfaceConfig dataB;
  

  public DataConfig(Element data) {
    this.maxRow = Integer.valueOf(data.attributeValue("maxRow"));
    dataA = new DataInterfaceConfig(data.element("dataA"));
    dataB = new DataInterfaceConfig(data.element("dataB"));
  }
}
