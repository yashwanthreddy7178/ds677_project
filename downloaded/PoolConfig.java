/**
 * Jan 4, 2018
 */
package com.humin_mybatis.testRedis;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;


import redis.clients.jedis.JedisPoolConfig;

/** 
 * @ClassName: PoolConfig 
 * @Description: 
 * @author humin 
 * @date Jan 4, 2018 3:36:44 PM 
 *  
 */
public class PoolConfig extends JedisPoolConfig implements InitializingBean{
	@Override
	public void afterPropertiesSet() throws Exception {
			Properties properties = ConfigProperties.newInstance();
			if(properties != null){
//				if(StringUtils.isNotBlank(properties.getProperty("redis.maxTotal")))
//					setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal").trim()));
				if(StringUtils.isNotBlank(properties.getProperty("redis.maxIdle")))
				setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle").trim()));
				if(StringUtils.isNotBlank(properties.getProperty("redis.minIdle")))
				setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle").trim()));
//					if(StringUtils.isNotBlank(properties.getProperty("redis.maxWaitMillis")))
//					setMaxWaitMillis(Integer.parseInt(properties.getProperty("redis.maxWaitMillis").trim()));
				if(StringUtils.isNotBlank(properties.getProperty("redis.testOnBorrow")))
				setTestOnBorrow(Boolean.valueOf(properties.getProperty("redis.testOnBorrow").trim()));
			}
	}
}
