package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.UpdateResidentFromRoomRequest;
import junction.al.nova_spring.model.RoomRequestForUpdateResidentsAndContract;
import junction.al.nova_spring.model.RoomRequestSingleUserUpdate;
import junction.al.nova_spring.model.RoomResponse;
import junction.al.nova_spring.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("api")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/room/perFloor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Room>> roomsPerFloor(@RequestParam("floorId") String floorId) {
        return ResponseEntity.ok(this.roomService.getAllRoomsPerFloor(floorId));
    }

    @PostMapping("/room/{roomId}/updateAlarm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Room> updateRoomAlarm(
            @PathVariable("roomId") String roomId,
            @RequestParam("alarm") boolean alarm
    ) {
        return ResponseEntity.ok(this.roomService.updateAlarm(roomId, alarm));
    }

    @PostMapping("/room/updateResident")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponse> updateRoomResident(
            @RequestBody RoomRequestSingleUserUpdate roomRequestSingleUserUpdate
    ) {
        return ResponseEntity.ok(this.roomService.updateRoomResident(roomRequestSingleUserUpdate));
    }

    @PostMapping("/room/updateRoomContract")
    public ResponseEntity<Boolean> updateRoomContract(
            @RequestBody RoomRequestForUpdateResidentsAndContract request
            ){
        return ResponseEntity.ok(this.roomService.updateResidentAndContract(request));
    }

    @PostMapping("/room/deleteResident")
    public void deleteResident(
            @RequestBody UpdateResidentFromRoomRequest request
    ){
        this.roomService.deleteResidentFromRoom(request.getResidentId(), request.getRoomId());
    }

    @PostMapping("/room/addResident")
    public void addResident(
            @RequestBody UpdateResidentFromRoomRequest request
    ){
        this.roomService.addResidentFromRoom(request.getResidentId(), request.getRoomId());
    }

}
