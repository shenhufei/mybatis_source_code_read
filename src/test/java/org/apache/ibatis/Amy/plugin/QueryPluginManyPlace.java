package org.apache.ibatis.Amy.plugin;

import java.util.Properties;

import org.apache.ibatis.Amy.start.StartDemo;
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

/**
 *   @Desc   配置多个 @Signature 让自定义的插件在不同的地方产生作用
 *   @author shenhufei
 *   @Date 2019年12月3日
 */
@Intercepts({@Signature(
		  type= Executor.class,
		  method = "query",
		  args = {MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class}),
	@Signature(
			  type= Executor.class,
			  method = "update",
			  args = {MappedStatement.class,Object.class})})
public class QueryPluginManyPlace  implements Interceptor{
	private static final Log log = LogFactory.getLog(QueryPluginManyPlace.class);
	private Properties properties = new Properties();
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		 log.error("在具体方法之前执行");
		Object returnObject = invocation.proceed();
		log.error("在具体方法之后执行");      // 在做update操作的时候，方法执行之后的逻辑为啥没有执行，这个是个疑问；
	    return returnObject;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	  }
	
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	  }

}
