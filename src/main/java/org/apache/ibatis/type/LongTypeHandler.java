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
package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Clinton Begin
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {
	/**
	 *   @Desc 将java对象的数据类型转换成数据库对象的数据类型
	 *   @author shenhufei
	 *   @Date 2019年11月13日
	 */
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setLong(i, parameter);
  }

  /**
 *   @Desc
 *   @author shenhufei
 *   @Date 2019年11月13日
 */
@Override
  public Long getNullableResult(ResultSet rs, String columnName)
      throws SQLException {
	  //从resultset对象中获取从数据拿到的数据
    long result = rs.getLong(columnName);
    //进行赋值操作
    return result == 0 && rs.wasNull() ? null : result;
  }

  /**
 *   @Desc 从数据库返回值中拿到对应java的数据类型
 *   @author shenhufei
 *   @Date 2019年11月13日
 */
@Override
  public Long getNullableResult(ResultSet rs, int columnIndex)
      throws SQLException {
    long result = rs.getLong(columnIndex);
    return result == 0 && rs.wasNull() ? null : result;
  }
/**
 *   @Desc 从数据库返回值中拿到对应java的数据类型
 *   @author shenhufei
 *   @Date 2019年11月13日
 */
  @Override
  public Long getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    long result = cs.getLong(columnIndex);
    return result == 0 && cs.wasNull() ? null : result;
  }
}
