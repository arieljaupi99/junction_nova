package junction.al.nova_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResidentFromRoomRequest {
    private String residentId;
    private String roomId;
}
