package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.service.MemberService;

public class MemberListHandler implements Command {

  // 핸들러가 사용할 DAO : 의존 객체(dependency)
  MemberService memberService;

  // DAO 객체는 이 클래스가 작업하는데 필수 객체이기 때문에
  // 생성자를 통해 반드시 주입 받도록 한다.
  public MemberListHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 목록]");

    List<Member> list = memberService.list();

    for (Member m : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), 
          m.getName(), 
          m.getEmail(),
          m.getPhoto(),
          m.getTel());
    }
  }
}






