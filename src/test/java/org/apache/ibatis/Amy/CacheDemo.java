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
 *   @Desc mybatis 启动的demo
 *   @author shenhufei
 *   @Date 2019年11月16日
 */
public class CacheDemo {
	private static final Log log = LogFactory.getLog(CacheDemo.class);
	public static void main(String[] args) throws IOException {
		String resource = "org/apache/ibatis/Amy/mybatis-config.xml";//"mybatis-config.xml"
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("org.apache.ibatis.Amy.UserMapper.selectUser",1L);
		int is = session.update("org.apache.ibatis.Amy.UserMapper.update","fankuaisss33333");
		session.commit();
		log.error(user.toString());
		System.out.print("");
	}

}
