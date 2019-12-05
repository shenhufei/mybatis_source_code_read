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

/**
 * @Desc 在@Intercepts 注解中，只配置一个 @Signature，插件中的方法只会在一个位置生效
 * @author shenhufei
 * @Date 2019年12月3日
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class QueryPlugin implements Interceptor {
	private static final Log log = LogFactory.getLog(QueryPlugin.class);
	private Properties properties = new Properties();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.error("在具体方法之前执行");
		Object returnObject = invocation.proceed();
		log.error("在具体方法之后执行");
		return returnObject;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

}
