package com.eomcs.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import com.eomcs.driver.Statement;
import com.eomcs.pms.domain.Task;

public class TaskListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[작업 목록]");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,content,deadline,status,owner from pms_task order by no desc");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %s, [%s]\n", 
            rs.getInt("no"), 
            rs.getString("content"), 
            rs.getDate("deadline"),
            rs.getInt("status"),
            rs.getString("owner"));
      }
    }
    
  }

}
