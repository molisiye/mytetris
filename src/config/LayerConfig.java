/**   
 * @Title: LayerConfig.java 
 * @Package config 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午4:48:48 
 * @version  
 */
package config;

import lombok.Getter;

@Getter
public class LayerConfig {
  private String className;
  private int x;
  private int y;
  private int w;
  private int h;
  
  
  /**
   * @param className
   * @param x
   * @param y
   * @param w
   * @param h
   */
  public LayerConfig(String className, int x, int y, int w, int h) {
    this.className = className;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

//  public String getClassName() {
//    return className;
//  }
//
//  public int getX() {
//    return x;
//  }
//
//  public int getY() {
//    return y;
//  }
//
//  public int getW() {
//    return w;
//  }
//
//  public int getH() {
//    return h;
//  }

  
}
