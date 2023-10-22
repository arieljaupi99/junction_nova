package junction.al.nova_spring.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("room")
@Getter
@Setter
public class Room {
    @Id
    private String id;
    private String price;
    private String name;
    private String floorId;
    private boolean isOccupied;
    private boolean isAlarm;
    private List<String> residentId;
    private String area;
    private String description;
    private String contractId;
    private String capacity;

    public void setSingleResidentId(String residentIdList) {
        List<String> currentList = this.getResidentId();
        if (currentList == null) {
            this.residentId = new ArrayList<>();
        }
        this.getResidentId().add(residentIdList);
    }


    public boolean isOccupied() {
        return !((this.getResidentId() == null) || (this.getResidentId().isEmpty()));
    }
}
