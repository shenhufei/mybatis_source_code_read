package org.apache.ibatis.Amy.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.Amy.User;
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
public class StartDemo {
	private static final Log log = LogFactory.getLog(StartDemo.class);
	public static void main(String[] args) throws IOException {
		String resource = "org/apache/ibatis/Amy/mybatis-config.xml";//"mybatis-config.xml"
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("org.apache.ibatis.Amy.UserMapper.selectUser",1L);
		List<Object> selectList = session.selectList("org.apache.ibatis.Amy.UserMapper.selectAll");
		log.error(selectList.toString());
		log.error(user.toString());
		System.out.print("");
	}

}
