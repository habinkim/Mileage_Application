package com.habin.marketboro_mileage_task.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habin.marketboro_mileage_task.common.cache.SerializablePage;
import io.lettuce.core.RedisURI;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
@EnableCaching
public class RedisConfig {

	private final RedisProperties redisProperties;
//	private final CacheProperties cacheProperties;

	private ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), NON_FINAL, PROPERTY);
		return mapper;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisURI redisURI = RedisURI.builder()
				.withHost(redisProperties.getHost())
				.withPort(redisProperties.getPort())
				.withDatabase(redisProperties.getDatabase())
				.withAuthentication(redisProperties.getUsername(), redisProperties.getPassword())
				.build();
		RedisConfiguration redisConfiguration = LettuceConnectionFactory.createRedisConfiguration(redisURI);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration);
		lettuceConnectionFactory.afterPropertiesSet();
		return lettuceConnectionFactory;
	}

	private RedisCacheConfiguration redisCacheDefaultConfiguration() {
		return RedisCacheConfiguration
				.defaultCacheConfig()
				.disableCachingNullValues()
				.serializeKeysWith(fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper())))
				.entryTtl(Duration.ofHours(1));
	}

	private RedisCacheConfiguration redisCachePageConfiguration() {
		return RedisCacheConfiguration
				.defaultCacheConfig()
				.disableCachingNullValues()
				.serializeKeysWith(fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(fromSerializer(new Jackson2JsonRedisSerializer<>(SerializablePage.class)))
				.entryTtl(Duration.ofHours(1));
	}

//	/**
//	 CacheProperties에서 바인딩해서 가져온 캐시명과 TTL 값으로  RedisCacheConfiguration을 만들고
//	 Map에 넣어 반환한다.
//	 Map을 사용하는 이유는 캐시의 만료기간이 다른 여러개의 캐시매니저를 만들게 됨으로써 발생하는
//	 성능저하를 방지하기 위해 하나의 캐시매니저에 Map을 이용하여 캐시 이름별 만료기간을 다르게 사용하기 위함이다.
//	 */
//	private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
//		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//		for (Map.Entry<String, Long> cacheNameAndTimeout : cacheProperties.getTtl().entrySet()) {
//			cacheConfigurations
//					.put(cacheNameAndTimeout.getKey(), redisCacheDefaultConfiguration().entryTtl(
//							Duration.ofSeconds(cacheNameAndTimeout.getValue())));
//		}
//		return cacheConfigurations;
//	}

	@Bean(name = "cacheManager")
	public CacheManager redisCacheManager() {
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		cacheConfigurations.put("mileageList", redisCachePageConfiguration());

		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory())
				.cacheDefaults(redisCacheDefaultConfiguration())
				.withInitialCacheConfigurations(cacheConfigurations)
				.build();
	}

}
