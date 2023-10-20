package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

}
