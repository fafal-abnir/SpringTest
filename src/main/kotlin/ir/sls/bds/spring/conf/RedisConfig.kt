package ir.sls.bds.spring.conf

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.cache.RedisCacheConfiguration
import java.time.Duration

@Configuration
class RedisConfig {

    @Bean
    fun redisCacheManagerBuilderCustomizer() = RedisCacheManagerBuilderCustomizer { builder ->
        val configurationMap = HashMap<String, RedisCacheConfiguration>()
        configurationMap["AuthorRegion"] = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5))
        configurationMap["employee"] = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(2))
        builder.withInitialCacheConfigurations(configurationMap)
    }
}