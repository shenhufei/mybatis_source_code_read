package org.apache.ibatis.Amy.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class ConfigPlugin implements Interceptor{
	private static final Log log = LogFactory.getLog(ExamplePlugin.class);
	public  Properties properties = new Properties();
	public Properties getProperties() {
		return properties;
	}

	/**
	 *   @Desc InvocationHandler的实例中，的handler方法会调用该方法，
	 *   @author shenhufei
	 *   @Date 2019年12月5日
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		log.error("在具体方法之前执行-----------------");
		Object returnObject = invocation.proceed();
		log.error("在具体方法之后执行----------------");
		return returnObject;
	}
	
	/**
	 *   @Desc 该放在mybatis 初始化每个 Interceptor 插件的实例的时候，都会调用该方法， 
	 *   plugin 标签中 的 property 的值， 标签属性中的值，如果不是由特殊操作，这些并没有任何意义，
	 *   这些值，可能是 某些配置文件；
	 *   @author shenhufei
	 *   @Date 2019年12月3日
	 */
	public void setProperties(Properties properties) {
		// 通过下面 打印日志，可以得出，setProperties 方法是可以去改变，config.xml配置文件中的
		//<plugin 标签中 的 property 的值，
	
		log.error("properties的数据是："+properties.toString());
		log.error("properties中url是："+properties.getProperty("url"));
		properties.setProperty("name", "我是谁");
		
		log.error("properties中url是："+properties.getProperty("url"));
		this.properties = properties;
	  }
	
	/**
	 *   @Desc 做代理的植入操作
	 *   @author shenhufei
	 *   @Date 2019年12月5日
	 */
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	  }

}
