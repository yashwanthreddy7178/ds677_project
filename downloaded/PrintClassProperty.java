/*
 * Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
 *
 * 注意：本内容仅限于深圳市归一科技研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
 */

package com.gy.hsxt.uc.ent.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @Package: com.gy.hsxt.uc.ent.test
 * @ClassName: PrintClassProperty
 * @Description: TODO
 * 
 * @author: huanggaoyang
 * @date: 2015-10-21 下午8:26:25
 * @version V1.0
 */
public class PrintClassProperty {
	public static void printProerty(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		System.out.println(o.getClass().getName()
				+ ":------------------------------------------");
		for (int i = 0; i < fields.length; i++) {
			if ("serialVersionUID".equals(fields[i].getName())) {
				continue;
			}
			System.out.println(String.format("%s:----%s", fields[i].getName(),
					getFieldValueByName(fields[i].getName(), o)));
		}
	}

	/**
	 * 根据属性名获取属性值
	 * */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
