package config;

import lombok.Getter;
import org.dom4j.Element;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class SystemConfig {
  
  private final int maxX;
  private final int maxY;
  private final int minX;
  private final int minY;
  private final int levelUp;
  private final List<Point[]> typeConfig;

  private final List<Boolean> typeRound;
  
  private final Map<Integer, Integer> plusPoint;

  public SystemConfig(Element system) {
    this.maxX=Integer.parseInt(system.attributeValue("maxX"));
    this.maxY=Integer.parseInt(system.attributeValue("maxY"));
    this.minX=Integer.parseInt(system.attributeValue("minX"));
    this.minY=Integer.parseInt(system.attributeValue("minY"));
    this.levelUp = Integer.parseInt(system.attributeValue("levelUp"));
    this.plusPoint = new HashMap<>();
    @SuppressWarnings("unchecked")
    List<Element> rects = system.elements("rect");
    typeConfig = new ArrayList<Point[]>(rects.size());
    typeRound = new ArrayList<Boolean>(rects.size());
    for (Element element : rects) {
      // 是否旋转
      this.typeRound.add(Boolean.parseBoolean(element.attributeValue("round")));
      // 获得坐标对象
      @SuppressWarnings("unchecked")
      List<Element> pointConfig = element.elements("point");
      // 创建point对象数组
      Point[] points = new Point[pointConfig.size()];
      // 初始化Point对象数组
      for(int i = 0; i < points.length; i++) {
        int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
        int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
        points[i] = new Point(x, y);
      }
      // 把Point对象数组放到typeConfig中
      typeConfig.add(points);
    }
    List<Element> plusPointCfg = system.elements("plusPoint");
    for (Element element : plusPointCfg) {
     int rm = Integer.parseInt(element.attributeValue("rm")); 
     int point = Integer.parseInt(element.attributeValue("point")); 
     this.plusPoint.put(rm, point);
    }
  }
}
