package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequestForUpdateResidentsAndContract;
import junction.al.nova_spring.model.RoomRequestSingleUserUpdate;
import junction.al.nova_spring.model.RoomResponse;
import junction.al.nova_spring.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/perFloor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Room>> roomsPerFloor(@RequestParam("floorId") String floorId) {
        return ResponseEntity.ok(this.roomService.getAllRoomsPerFloor(floorId));
    }

    @PostMapping("{roomId}/updateAlarm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Room> updateRoomAlarm(
            @PathVariable("roomId") String roomId,
            @RequestParam("alarm") boolean alarm
    ) {
        return ResponseEntity.ok(this.roomService.updateAlarm(roomId, alarm));
    }

    @PostMapping("/updateResident")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomResponse> updateRoomUser(
            @RequestBody RoomRequestSingleUserUpdate roomRequestSingleUserUpdate
    ) {
        return ResponseEntity.ok(this.roomService.updateRoomResident(roomRequestSingleUserUpdate));
    }

    @PostMapping("/updateRoom")
    public ResponseEntity<Boolean> updateRoom(
            @RequestBody RoomRequestForUpdateResidentsAndContract request
            ){
        return ResponseEntity.ok(this.roomService.updateResidentAndContract(request));
    }

    @PostMapping("/deleteResident")
    public void deleteResident(
            @RequestParam("residentId") String residentId,
            @RequestParam("roomId") String roomId
    ){
        this.roomService.deleteResidentFromRoom(residentId,roomId);
    }

}
