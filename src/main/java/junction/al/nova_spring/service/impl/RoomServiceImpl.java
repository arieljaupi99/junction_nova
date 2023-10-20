package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.repository.RoomRepo;
import junction.al.nova_spring.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public List<Room> getAllRoomsPerFloor(String floorId) {
        return this.roomRepo.findRoomsByFloorNumberIs(floorId);
    }

    @Override
    public Optional<Room> findRoomById(String roomId) {
        return this.roomRepo.findById(roomId);
    }
}
