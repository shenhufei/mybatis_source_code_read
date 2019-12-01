package org.apache.ibatis.Amy;

import com.alibaba.fastjson.JSON;

/**
 *   @Desc 对象转字符串
 *   @author shenhufei
 *   @Date 2019年12月1日
 */
public class MethodTest
{
	public static String getSting(Object obj){
		return  JSON.toJSONString(obj);
	}
}
