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
package org.apache.ibatis.cache.decorators;

import org.apache.ibatis.cache.Cache;

/**
 * @author Clinton Begin
 */
public class SynchronizedCache implements Cache {

  private final Cache delegate;

  public SynchronizedCache(Cache delegate) {
    this.delegate = delegate;
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  @Override
  public synchronized int getSize() {
    return delegate.getSize();
  }

  @Override
  public synchronized void putObject(Object key, Object object) {
    delegate.putObject(key, object);
  }

  /**
 *   @Desc 注意从缓存中拿数据的时候，这个位置是加了 synchronized锁；这段逻辑只有获取二级缓存的时候才会走
 *   @author shenhufei
 *   @Date 2019年11月19日
 */
@Override
  public synchronized Object getObject(Object key) {
    return delegate.getObject(key);
  }

  @Override
  public synchronized Object removeObject(Object key) {
    return delegate.removeObject(key);
  }

  @Override
  public synchronized void clear() {
    delegate.clear();
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return delegate.equals(obj);
  }

}
