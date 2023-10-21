package junction.al.nova_spring.entities;

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
    @Getter
    private List<String> residentId;
    private String area;
    private String description;

    public void setResidentId(String residentId) {
        this.getResidentId().add(residentId);
    }

    public boolean isOccupied() {
        return  !((this.getResidentId() == null) || (this.getResidentId().isEmpty()));
    }
}
