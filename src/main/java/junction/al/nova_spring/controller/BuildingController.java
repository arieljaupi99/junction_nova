package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Building;
import junction.al.nova_spring.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/building/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Building>> getAll() {
        return ResponseEntity.ok(this.buildingService.getAll());
    }
}
