package junction.al.nova_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("floor")
@Getter
@Setter
public class Floor {
    @Id
    private String id;
    private String floorNumber;
    private String buildingId;
}
