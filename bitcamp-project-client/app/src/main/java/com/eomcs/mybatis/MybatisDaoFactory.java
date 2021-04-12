package com.eomcs.mybatis;

import java.lang.reflect.Proxy;

public class MybatisDaoFactory {

  // DAO 인터페이스를 구현한 객체를 만들어준다.
  @SuppressWarnings("unchecked")
  public <T> T createDao(Class<T> daoInterface) {
    DaoWorker daoWorker = new DaoWorker();
    
    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(), 
        new Class<?> [] {daoInterface}, 
        daoWorker);
  }
}
