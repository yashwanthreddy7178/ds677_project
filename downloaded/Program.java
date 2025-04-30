package dk.cphbusiness.dbd.sql;

import java.sql.*;
import java.util.Properties;

public class Program {
  public static void main(String[] args) throws Exception {
    String url = "jdbc:postgresql://localhost/rdb";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","superpostg");
    try (Connection conn = DriverManager.getConnection(url, props)) {
      String sql = "select * from ALL_PETS;";
      PreparedStatement statement = conn.prepareStatement(sql);
      try(ResultSet result = statement.executeQuery()) {
        while (result.next()) {
          String type = result.getString("species");

          int id = result.getInt("pet_id");
          String name = result.getString("name");
          int age = result.getInt("age");

          String barkpitch = result.getString("barkPitch");

          Integer lifeCount = result.getInt("liveCount"); //Returns 0 even if the value should be null





          System.out.println("id: " + id + ", Name: " + name + ", Age: " + age + ", Species: " + type + ", barkPitch: " + barkpitch + ", lifeCount: " + lifeCount);

          //  System.out.println(""+result.getInt(1)+" "+result.getString(2));
          }
        }
      }



    //String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
    //Connection conn = DriverManager.getConnection(url);
    }

}
