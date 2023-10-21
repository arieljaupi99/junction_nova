package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequest;
import junction.al.nova_spring.model.RoomResponse;
import junction.al.nova_spring.repository.RoomRepo;
import junction.al.nova_spring.service.ResidentService;
import junction.al.nova_spring.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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
    public Room updateAlarm(String roomId, boolean alarm) {
        Room room = this.roomRepo.findById(roomId).orElse(null);
        if (room != null) {
            room.setAlarm(alarm);
            return this.roomRepo.save(room);
        }
        return null;
    }

    @Override
    public RoomResponse updateRoomResident(RoomRequest roomRequest) {
        RoomResponse response = new RoomResponse();
        Room room = this.roomRepo.findById(roomRequest.getRoomId()).orElse(null);
        if (room != null) {
            Resident resident = this.residentService.findResidentByNameAndSurname(roomRequest.getUserName(), roomRequest.getUserSurname());
            if (resident == null) {
                resident = this.residentService.save(Resident.generateFromRoomRequest(roomRequest));
                response.setNewResident(true);
            }
            if (resident.getRoomId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This resident has already a room!");
            }
            resident.setRoomId(roomRequest.getRoomId());
            residentService.update(resident);
            room.setResidentId(resident.getId());
            roomRepo.save(room);
            return response;
        }
        return null;
    }
}
