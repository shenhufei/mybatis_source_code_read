package org.apache.ibatis.Amy;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *   @Desc mybatis 事物的demo
 *   @author shenhufei
 *   @Date 2019年11月16日
 */
public class TransactionDemo {
	private static final Log log = LogFactory.getLog(StartDemo.class);
	public static void main(String[] args) throws IOException {
		String resource = "org/apache/ibatis/Amy/mybatis-config.xml";//"mybatis-config.xml"
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		int user = session.update("org.apache.ibatis.Amy.UserMapper.update","fankuaisss33333");
		session.commit();
		int i= user/0;
	   //事物已经提交了，再调用回滚的方法已经没有用了，
		session.rollback();
		System.out.print("执行成功"+i);
	}


}
