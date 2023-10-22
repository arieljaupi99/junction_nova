package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequestForUpdateResidentsAndContract;
import junction.al.nova_spring.model.RoomRequestSingleUserUpdate;
import junction.al.nova_spring.model.RoomResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAllRoomsPerFloor(String floorId);

    Optional<Room> findRoomById(String roomId);

    Room updateAlarm(String roomId,boolean alarm);

    RoomResponse updateRoomResident(RoomRequestSingleUserUpdate roomRequestSingleUserUpdate);


    Boolean updateResidentAndContract(RoomRequestForUpdateResidentsAndContract request);

    void deleteResidentFromRoom(String residentId,String roomId);

    void addResidentFromRoom(String residentId, String roomId);
}
