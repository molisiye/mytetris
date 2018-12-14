package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Buffer;

import dto.Player;

public class DataBase implements IData {

  private final String driver;

  private final String dbUri;

  private final String dbUser;

  private final String dbPwd;

  private static String LOAD_SQL = "select user_name, point from user_point order by point desc limit 5";

  private static String SAVE_SQL = "insert into user_point(user_name, point, type_id) values(?,?,?)";
//  static {
//    try {
//      Class.forName(DRIVE);
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//  }
  
  public DataBase(HashMap<String, String> param) {
    this.driver = param.get("driver");
    this.dbUri = param.get("dbUri");
    this.dbUser = param.get("dbUser");
    this.dbPwd = param.get("dbPwd");
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<Player> loadData() {
    List<Player> players = new ArrayList<Player>();
    try (Connection conn = DriverManager.getConnection(dbUri, dbUser, dbPwd);
        PreparedStatement stmt = conn.prepareStatement(LOAD_SQL);
        ResultSet rs = stmt.executeQuery();) {

      while (rs.next()) {
        players.add(new Player(rs.getString(1), rs.getInt(2)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return players;
  }

  @Override
  public void saveData(Player players) {
    try (Connection conn = DriverManager.getConnection(dbUri, dbUser, dbPwd);
        PreparedStatement stmt = conn.prepareStatement(SAVE_SQL)) {
      stmt.setObject(1, players.getName());
      stmt.setObject(2, players.getPoint());
      stmt.setObject(3, 1);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {

  }

}
