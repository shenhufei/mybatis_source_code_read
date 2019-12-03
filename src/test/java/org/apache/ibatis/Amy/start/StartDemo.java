package org.apache.ibatis.Amy.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.Amy.User;
import org.apache.ibatis.Amy.plugin.ConfigPlugin;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.Configuration;
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
		ConfigPlugin config =  new ConfigPlugin();
		Properties properties = config.getProperties();
		String  url = (String) properties.get("url");
//        log.error("plugin中自己设置的参数的值是："+url);
//		//List<Object> selectList = session.selectList("org.apache.ibatis.Amy.UserMapper.selectAll");
//		//log.error(selectList.toString());
//		//log.error(user.toString());
//        Configuration  configuration  = new Configuration();
//        configuration.setVariables(properties);
//        SqlSessionFactoryBuilder  sqlSessionFactoryBuilder = new  SqlSessionFactoryBuilder();
//        // 可以把Properties 对象中获得到数据  组装  SqlSessionFactoryBuilder  对象中自己个性化的数据，重新来构建 mybatis的环境；
//        SqlSessionFactory build = sqlSessionFactoryBuilder.build(configuration);
//        SqlSession openSession = build.openSession();
//        User user2 = openSession.selectOne("org.apache.ibatis.Amy.UserMapper.selectUser",1L);
//		System.out.print("");
	}

}
