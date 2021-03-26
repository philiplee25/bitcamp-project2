package com.eomcs.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.eomcs.driver.Statement;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskAddHandler implements Command {

  MemberValidator memberValidator;

  public TaskAddHandler(MemberValidator memberValidator) {
    this.memberValidator = memberValidator;
  }


  @Override
  public void service() throws Exception {
    System.out.println("[작업 등록]");

    
    Task t = new Task();
    t.setContent(Prompt.inputString("내용? "));
    t.setDeadline(Prompt.inputDate("마감일? "));
    t.setStatus(Prompt.inputInt("상태?\n0: 신규\n1: 진행중\n2: 완료\n> "));

    t.setOwner(memberValidator.inputMember("담당자?(취소: 빈 문자열) "));
    if (t.getOwner() == null) {
      System.out.println("작업 등록을 취소하였습니다.");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into pms_task(content,deadline,status,owner)"
                + " values(?,?,?,?)");) {

      stmt.setString(1, t.getContent());
      stmt.setDate(2, t.getDeadline());
      stmt.setInt(3, t.getStatus());
      stmt.setString(4, t.getOwner());
      stmt.executeUpdate();

      System.out.println("프로젝트를 등록했습니다.");
    }
  }
}
