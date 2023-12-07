package kq.practice.ssf19.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import kq.practice.ssf19.model.Employee;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Value("${redis.username}")
    private String redisUsername;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.database}")
    private String redisDatabase;

    @Bean
    public JedisConnectionFactory jedisConnFactory() {

        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration(redisHost, redisPort);

        if (redisUsername != null && !redisUsername.isEmpty()) {
            rsc.setUsername(redisUsername);
        }

        if (redisPassword != null && !redisPassword.isEmpty()) {
            rsc.setUsername(redisPassword);
        }

        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(rsc, jedisClient);
        jedisFac.afterPropertiesSet();

        return jedisFac;
    }

    @Bean
    public RedisTemplate<String, Object> redisObjectTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Employee> redisEmployeeTemplate() {

        RedisTemplate<String, Employee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
