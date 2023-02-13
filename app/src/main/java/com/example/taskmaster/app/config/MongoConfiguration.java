package com.example.taskmaster.app.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {

	private final ImmutableMongodConfig immutableMongodConfig;

	@Autowired
	public MongoConfiguration(ImmutableMongodConfig immutableMongodConfig) {
		this.immutableMongodConfig = immutableMongodConfig;
	}

	@Override
	protected String getDatabaseName() {
		return "example";
	}

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create(String.format("mongodb://localhost:%d/%s", immutableMongodConfig.net().getPort(),
			getDatabaseName()));
	}
}
