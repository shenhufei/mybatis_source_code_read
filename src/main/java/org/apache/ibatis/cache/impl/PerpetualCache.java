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
package org.apache.ibatis.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

/**
 * @author Clinton Begin
 */
/**
 *   @Desc mybatis 存放缓存的地方；
 *   @author shenhufei
 *   @Date 2019年11月18日
 *    Perpetual  音标  pərˈpeCHo͞oəl
 */
public class PerpetualCache implements Cache {

  private final String id;

  private Map<Object, Object> cache = new HashMap<>();

  public PerpetualCache(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public int getSize() {
    return cache.size();
  }

  @Override
  public void putObject(Object key, Object value) {
    cache.put(key, value);
    System.out.println("缓存长度是："+cache.size());
  }

  /**
 *   @Desc 最终获取缓存的地方；
 *   TODO
 *   1.开启二级缓存的时候很奇怪，相同的查询key,在不同的调用路径中结果是不一样的，具体表现为：
 *   1.1从 TransactionalCache 过来的调用，都是为空；
 *   1.2直接调用本类getObject方法的时候是能够获取缓存信息的
 *   2.没有开启二级缓存的情况；没有开启二级缓存的时候，这个缓存是直接通过本类getObject来调用的，也就是不通过 TransactionalCache 这个
 *   缓存入口过来就能直接拿到缓存数据；
 *   @author shenhufei
 *   @Date 2019年11月18日
 */
@Override
  public Object getObject(Object key) {
	System.out.println("缓存长度是："+cache.size());
	  Object object = cache.get(key);
	  System.out.println(object);
      return object ;
    		
  }

  @Override
  public Object removeObject(Object key) {
	  //删除map缓存对象中的key对应的值
    return cache.remove(key);
  }

  @Override
  public void clear() {
    cache.clear();
  }

  @Override
  public boolean equals(Object o) {
    if (getId() == null) {
      throw new CacheException("Cache instances require an ID.");
    }
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cache)) {
      return false;
    }

    Cache otherCache = (Cache) o;
    return getId().equals(otherCache.getId());
  }

  @Override
  public int hashCode() {
    if (getId() == null) {
      throw new CacheException("Cache instances require an ID.");
    }
    return getId().hashCode();
  }

}
