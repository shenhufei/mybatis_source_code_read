package org.apache.ibatis.Amy.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
@Intercepts({@Signature(
		  type= Executor.class,
		  method = "update",
		  args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//通过debug 展示，这里额外的处理逻辑需要在写在 Object returnObject = invocation.proceed(); 前面才会有效；
		System.out.println("前");
		Object returnObject = invocation.proceed();
		System.out.println("后");
	    return returnObject;
	}

}
