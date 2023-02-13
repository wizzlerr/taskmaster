package com.example.taskmaster.app.config;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import java.net.UnknownHostException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.SocketUtils;

@AutoConfiguration(before = {MongoAutoConfiguration.class})
public class EmbeddedMongoConfig {

	@Bean(initMethod = "start", destroyMethod = "stop")
	public MongodExecutable mongodExecutable(ImmutableMongodConfig immutableMongodConfig) {
		MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
		return mongodStarter.prepare(immutableMongodConfig);
	}

	@Bean
	public ImmutableMongodConfig immutableMongodConfig() throws UnknownHostException {

		return MongodConfig.builder()
			.version(Version.Main.V4_2)
			.net(new Net("localhost", SocketUtils.findAvailableTcpPort(), Network.localhostIsIPv6()))
			.build();
	}
}
