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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.jdbc.AbstractSQL;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * @author Clinton Begin
 */
/** 
 *   @Desc 封装了所有的  插件对象，并且存储在 list集合中；
 *   @author shenhufei
 *   @Date 2019年12月3日
 */
public class InterceptorChain {
	private static final Log log = LogFactory.getLog(AbstractSQL.class);


  /**
 *  初始化的时候，已经加载了所有自定义插件对象
 */
private final List<Interceptor> interceptors = new ArrayList<>();

  /**
 *   @Desc 没有自定义插件的时候，返回去的对象就是原对象，如果有自定义插件的时候，那么这里的对象就是一个代理对象；
 *   比如说：入参是CachingExecutor,如果有自定义插件，那么这里就应该返回的是 CachingExecutor的代理对象
 *   @author shenhufei
 *   @Date 2019年11月25日
 */
public Object pluginAll(Object target) {
	//遍历所有的插件对象，可能多个插件会针对同一个对象的同一个方法都做了操作
    for (Interceptor interceptor : interceptors) {
      target = interceptor.plugin(target);
    }
    return target;
  }

  public void addInterceptor(Interceptor interceptor) {
    interceptors.add(interceptor);
  }

  public List<Interceptor> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }

}
