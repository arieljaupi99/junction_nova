package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequest;
import junction.al.nova_spring.model.RoomResponse;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAllRoomsPerFloor(String floorId);

    Optional<Room> findRoomById(String roomId);

    void updateAlarm(String roomId,boolean alarm);

    RoomResponse updateRoomResident(RoomRequest roomRequest);
}
