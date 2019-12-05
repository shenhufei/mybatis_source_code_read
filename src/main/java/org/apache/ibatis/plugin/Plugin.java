/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.jdbc.AbstractSQL;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.reflection.ExceptionUtil;

/**
 * @author Clinton Begin
 */
public class Plugin implements InvocationHandler {
	private static final Log log = LogFactory.getLog(AbstractSQL.class);


  private final Object target;
  private final Interceptor interceptor;
  private final Map<Class<?>, Set<Method>> signatureMap;

  private Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
    this.target = target;
    this.interceptor = interceptor;
    this.signatureMap = signatureMap;
  }

  /**
 *   @Desc 根据原对象就是被代理对象，通过jdk代理方式获取到代理对象。
 *   @author shenhufei
 *   @Date 2019年11月25日
 */
public static Object wrap(Object target, Interceptor interceptor) {
	//获取一个自定义插件对象下，@Signature数组中的数据
    Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
    Class<?> type = target.getClass();
    //根据目标对象，拿signatureMap对象，判断signatureMap的key是否包含了目标对象实现的接口对象；
    //并返回，signatureMap中包含的目标对象的接口对象；
    Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
  //大于0就说明需要被代理；
    if (interfaces.length > 0) { 	
    	//下面就是走的JDK代理；
      return Proxy.newProxyInstance(
    		  //获取被代理对象的类加载器
          type.getClassLoader(),
          //传入被代理对象的接口
          interfaces,
          //Plugin 是InvocationHandler 的一个实例，代理执行任何操作的时候都会去执行InvocationHandler 类的 handler
          new Plugin(target, interceptor, signatureMap));
    }
    //将 @Intercepts({@Signature(type= Executor.class, 注解中其实也就是被切入对象  Executor的代理对象返回
    return target;
  }

  /**
 *   @Desc 当有自定义插件对象的时候，executor 对象做的crud  就会调用这里invoke方法，invoke方法才会被执行；
 *   @author shenhufei
 *   @Date 2019年11月25日
 */
/**
 *   @Desc 只要 代理对象执行了任意一个方法，那么就都会走到这里来；然后再根据 InvocationHandler 对象中保存的
 *  那到 signatureMap中的需要具体做处理的方法名称；看传递过来的方法是否在这个集合中，如果在那么就做去调用
 *  插件的intercept方法；如果不在那么就走方法的正常流程；
 *   @author shenhufei
 *   @Date 2019年12月5日
 */
@Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
    	//拿到被拦截方法所在类（也就是被代理的类）的字节码对象，
    	//被代理的类的字节码对象从signatureMap获取到具体由哪些方法是需要做处理；
      Set<Method> methods = signatureMap.get(method.getDeclaringClass());
      //判断被拦截的方法是否在这个需要被处理的方法集合中。
      //如果是的：1.就去执行，这个插件对应中的 intercept方法；2.把拦截到的方法执行的参数列表也传递过去（可以有更多的
     // 功能操作）；
      if (methods != null && methods.contains(method)) {
        return interceptor.intercept(new Invocation(target, method, args));
      }
      //如果不在，那么这里就直接去执行被拦截的类的具体的方法；
      return method.invoke(target, args);
    } catch (Exception e) {
      throw ExceptionUtil.unwrapThrowable(e);
    }
  }

  private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) {
    Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
    // issue #251
    if (interceptsAnnotation == null) {
      throw new PluginException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
    }
    Signature[] sigs = interceptsAnnotation.value();  // 拿到@Intercepts 注解中 @Signature 注解数组对象
    //@Signature 数组所以这里就会对应一个map集合，自定插件中的一个方法可以切入位置是可以有多个地方的
    Map<Class<?>, Set<Method>> signatureMap = new HashMap<>();
    //解析@Signature数组中的插件的相关数据
    for (Signature sig : sigs) {
      Set<Method> methods = signatureMap.computeIfAbsent(sig.type(), k -> new HashSet<>());
      try {
    	  
        Method method = sig.type().getMethod(sig.method(), sig.args());
        methods.add(method);
      } catch (NoSuchMethodException e) {
        throw new PluginException("Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + e, e);
      }
    }
    return signatureMap;
  }

  private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
    Set<Class<?>> interfaces = new HashSet<>();
    while (type != null) {
      for (Class<?> c : type.getInterfaces()) {
        if (signatureMap.containsKey(c)) {
        	//把包含的对象就放入 interfaces集合中，如果包含，就说明有要做代理，
          interfaces.add(c);
        }
      }
      type = type.getSuperclass();
    }
    return interfaces.toArray(new Class<?>[interfaces.size()]);
  }

}
