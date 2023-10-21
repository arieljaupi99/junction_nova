package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequest;
import junction.al.nova_spring.model.RoomResponse;
import junction.al.nova_spring.repository.RoomRepo;
import junction.al.nova_spring.service.ResidentService;
import junction.al.nova_spring.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final ResidentService residentService;

    public RoomServiceImpl(RoomRepo roomRepo, ResidentService residentService) {
        this.roomRepo = roomRepo;
        this.residentService = residentService;
    }

    @Override
    public List<Room> getAllRoomsPerFloor(String floorId) {
        return this.roomRepo.findRoomsByFloorIdIs(floorId);
    }

    @Override
    public Optional<Room> findRoomById(String roomId) {
        return this.roomRepo.findById(roomId);
    }

    @Override
    public void updateAlarm(String roomId, boolean alarm) {
        Room room = this.roomRepo.findById(roomId).orElse(null);
        if (room != null) {
            room.setAlarm(alarm);
            this.roomRepo.save(room);
        }
    }

    @Override
    public RoomResponse updateRoomResident(RoomRequest roomRequest) {
        Room room = this.roomRepo.findById(roomRequest.getRoomId()).orElse(null);
        if (room!=null){
            Resident resident = this.residentService.findResidentByNameAndSurname(roomRequest.getUserName(),roomRequest.getUserSurname());
            if (resident == null){
                resident = Resident.generateFromRoomRequest(roomRequest);
                this.residentService.save(resident);
            }
        }
        return null;
    }
}
