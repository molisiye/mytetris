/**   
 * @Title: LayDataBase.java 
 * @Package ui 
 * @Description: 
 * @author zhm
 * @date 2018年2月12日 下午2:26:46 
 * @version  
 */
package ui;

import java.awt.Graphics;

public class LayerDataBase extends LayerData {
  
  public LayerDataBase(int x, int y, int w, int h) {
    super(x, y, w, h);
    
  }
  
  @Override
  public void paint(Graphics g) {
    this.createWindow(g);
    this.showData(Img.DB, this.dto.getDbRecord(), g);
    
  }
}
