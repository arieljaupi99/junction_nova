package junction.al.nova_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"junction.al.nova_spring"})
public class NovaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovaSpringApplication.class, args);
    }

}
