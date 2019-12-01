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
 *   @Desc   一级缓存二级缓存，其实没有必要具体去纠结这个缓存存放在哪里，就是PerpetualCache中，也就是一个map集合中
 *   一级缓存，二级缓存的最大区别就是，就是这个缓存的生命周期的不同，一级缓存就是生命周期，就是一次会话，
 *   一次会话结束那么这个缓存就会被失效，也就是你在这个会话中反复重复的去查询某个sql，这个都是会走缓存的；
 *   二级缓存就是，不仅仅是当前这次会话可以使用这个缓存，其他在相同mapper.XML中的查询SQL也会使用到这个缓存，只要没有修改
 *   语句 <update> <insert> <delete> 语句，那么都会使用这个缓存；
 *   
 *   其实没有必要去纠结一级缓存，二级缓存这个名词，主要就是缓存的生命周期不同；
 *   每次查询的时候都会去从缓存的中拿数据，然后再按照缓存的配置给缓存做刷新操作；
 *   @author shenhufei
 *   @Date 2019年11月18日
 */
public class CacheLruDemo {
	private static final Log log = LogFactory.getLog(AbstractSQL.class);

	public static void main(String[] args) throws IOException {
	String resource = "org/apache/ibatis/Amy/mybatis-config.xml";//"mybatis-config.xml"
	InputStream inputStream = Resources.getResourceAsStream(resource);
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	SqlSession session = sqlSessionFactory.openSession();
	int i;
	//实际测试，在xml文件没有开启二级缓存的时候，即使是循环中去查询，也是不走二级缓存的；
	for(i =0; i<10; i++){
		User user = session.selectOne("org.apache.ibatis.Amy.UserMapper.selectUser",1L);
		System.out.println(user);
	}
	
	}

}










