package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataTest implements IData {

  public DataTest(HashMap<String, String> param) {
  }
    
  @Override
  public List<Player> loadData() {
    List<Player> players = new ArrayList<>();
    players.add(new Player("ming", 100));
    players.add(new Player("ming", 1000));
    players.add(new Player("ming", 2000));
    players.add(new Player("ming", 3000));
    players.add(new Player("ming", 4000));
    return players;
  }

  @Override
  public void saveData(Player players) {

  }

}
