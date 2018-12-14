package dao;


import java.util.List;

import dto.Player;

/**
 * 数据持久层接口
 * @author zhm
 *
 */
public interface IData {

  /**
   * 获得数据
   * @return
   */
 public List<Player> loadData();
 
 /**
  * 存储数据
  * @param players
  */
 public void saveData(Player players);
}
