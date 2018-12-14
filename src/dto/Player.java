/**   
 * @Title: Player.java 
 * @Package dto 
 * @Description: 
 * @author zhm
 * @date 2018年2月14日 下午4:57:40 
 * @version  
 */
package dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Player implements Comparable<Player>, Serializable {
  private String name;
  private int point;

  public Player(String name, int point) {
    super();
    this.name = name;
    this.point = point;
  }

  @Override
  public int compareTo(Player pla) {
    return pla.point - this.point;
  }

}
