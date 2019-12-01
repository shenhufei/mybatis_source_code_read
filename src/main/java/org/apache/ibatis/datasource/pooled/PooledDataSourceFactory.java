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
package org.apache.ibatis.datasource.pooled;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

/**
 * @author Clinton Begin
 */
/**
 * 
 *   @Desc 连接池工厂类生成连接池
 *   @author shenhufei
 *   @Date 2019年11月4日
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DefaultSqlSession.class);


  public PooledDataSourceFactory() {
    this.dataSource = new PooledDataSource();
  }

}
