package org.apache.ibatis.Amy.cache;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.Amy.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.AbstractSQL;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** 
 *   @Desc   缓存执行流程
 *   @author shenhufei
 *   @Date 2019年11月18日
 */
public class CacheDemo {
	private static final Log log = LogFactory.getLog(AbstractSQL.class);

	public static void main(String[] args) throws IOException {
	String resource = "org/apache/ibatis/Amy/mybatis-config.xml";//"mybatis-config.xml"
	InputStream inputStream = Resources.getResourceAsStream(resource);
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	SqlSession session = sqlSessionFactory.openSession();
	User user = session.selectOne("org.apache.ibatis.Amy.UserMapper.selectUser",1L);
	
	System.out.print(session);
	}

}