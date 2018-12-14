package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import dto.Player;

public class DataDisk implements IData {

  private final String filePath;

  public DataDisk(HashMap<String, String> param) {
    this.filePath = param.get("path");
  }
  @Override
  public List<Player> loadData() {
    ObjectInputStream ois = null;
    List<Player> players = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(filePath));
      try {
        players = (List<Player>) ois.readObject();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
//      try {
//        ois.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
    }
    return players;
  }

  @Override
  public void saveData(Player pla) {
    // 先取出数据
    List<Player> players = this.loadData();

    // TODO 判断新纪录是否超过5条，若超过，则去掉分数最低的。如果不处理则本地文件会无限增加

    // 追加新纪录
    players.add(pla);
    // 重新写入
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream("save/record.dat"));
      oos.writeObject(players);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        oos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }



}
