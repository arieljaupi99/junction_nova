package junction.al.nova_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import junction.al.nova_spring.security.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("room")
@Getter
@Setter
public class Room {
    @Id
    private String id;
    private String floorNumber;
    private boolean isOccupied;
    private boolean isAlarm;
    @JsonIgnore
    private User user;

    public boolean isOccupied() {
        return this.user != null;
    }
}
