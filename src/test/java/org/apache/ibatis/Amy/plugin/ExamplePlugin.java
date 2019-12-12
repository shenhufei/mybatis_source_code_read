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
@Intercepts({@Signature(
		  type= Executor.class,
		  method = "update",
		  args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor{
	private static final Log log = LogFactory.getLog(ExamplePlugin.class);
	private Properties properties = new Properties();
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.error("在具体方法之前执行-------------");
		Object returnObject = invocation.proceed();
		log.error("在具体方法之后执行-----------");
		return returnObject;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	  }
	
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	  }

}
