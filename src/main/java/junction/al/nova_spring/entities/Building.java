package junction.al.nova_spring.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("building")
public class Building {
    @Id
    private String id;
}
