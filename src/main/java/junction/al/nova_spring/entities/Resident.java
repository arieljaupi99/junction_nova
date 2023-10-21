package junction.al.nova_spring.entities;

import junction.al.nova_spring.model.RoomRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("resident")
@Getter
@Setter
@Builder
@CompoundIndexes({
        @CompoundIndex(name = "name_surname", def = "{'name' : 1, 'surname': 1}" , unique = true),
})
public class Resident {
    @Id
    private String id;
    private String name;
    private String surname;
    private String roomId;
    private String contractId;

    public static Resident generateFromRoomRequest(RoomRequest roomRequest) {
        return Resident.builder()
                .name(roomRequest.getUserName())
                .surname(roomRequest.getUserSurname())
                .roomId(roomRequest.getRoomId())
                .build();
    }
}
