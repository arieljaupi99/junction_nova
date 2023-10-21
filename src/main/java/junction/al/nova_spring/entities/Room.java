package junction.al.nova_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import junction.al.nova_spring.security.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("room")
@Getter
@Setter
public class Room {
    @Id
    private String id;
    private String name;
    private String floorId;
    private boolean isOccupied;
    private boolean isAlarm;
    private List<String> residentId;
    private String area;
    private String description;

    public boolean isOccupied() {
        return this.residentId != null || this.residentId.isEmpty();
    }
}
