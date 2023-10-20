package junction.al.nova_spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import junction.al.nova_spring.entities.Floor;
import junction.al.nova_spring.service.FloorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("floor")
@Tag(name = "Floor")
public class FloorController {
    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/forBuilding")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Floor>> floorPerBuilding(@RequestParam("buildingId") String buildingId) {
        return ResponseEntity.ok(this.floorService.floorPerBuilding(buildingId));
    }
}
