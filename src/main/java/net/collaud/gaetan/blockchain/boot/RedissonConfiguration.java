package net.collaud.gaetan.blockchain.boot;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfiguration {

	@Bean(destroyMethod = "shutdown")
	RedissonClient redisson() throws IOException {
		Config config = new Config();
		config.useSingleServer()
				.setAddress("redis://collaud6.collaud.me:6379");
//		config.useClusterServers()
//				.addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
		return Redisson.create(config);
	}
}
