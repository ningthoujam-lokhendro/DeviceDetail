package com.ningzeta.deviceOUI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Define the Redis configuration bean and the serialization.
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	RedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisConnectionFactory.setHostName(environment.getRequiredProperty("redis.host"));
		jedisConnectionFactory.setPassword(environment.getProperty("redis.password"));
		jedisConnectionFactory.setPort(environment.getRequiredProperty("redis.port", Integer.class));
		jedisConnectionFactory.setUsePool(true);
		jedisPoolConfig.setMaxTotal(environment.getRequiredProperty("redis.pool.max-active", Integer.class));
		jedisPoolConfig.setMaxIdle(environment.getRequiredProperty("redis.pool.max-idle", Integer.class));
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		return jedisConnectionFactory;
	}

	@Bean
	Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
		return new Jackson2JsonRedisSerializer<>(Object.class);
	}

	@Bean
	StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setDefaultSerializer(stringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
		return redisTemplate;
	}
}
