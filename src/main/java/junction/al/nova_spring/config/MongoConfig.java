package junction.al.nova_spring.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import junction.al.nova_spring.converter.ZonedDateTimeReadConverter;
import junction.al.nova_spring.converter.ZonedDateTimeWriteConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMongoRepositories(basePackages = {"junction.al.nova_spring"})
@Log4j2
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.database}")
    private String db;

    @Override
    protected String getDatabaseName() {
        return this.db;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        log.debug("mongo connection : {} with db: {} ", this.uri, this.db);

        return MongoClients.create(uri);
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        try {
            return new MongoTemplate(mongoClient(), db);
        } catch (Exception e) {
            log.error("Cannot instanziate MongoTemplate");
            throw e;
        }
    }

    private final List<Converter<?, ?>> converters = new ArrayList<>();

    @Override
    @Bean
    public MongoCustomConversions customConversions() {
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
