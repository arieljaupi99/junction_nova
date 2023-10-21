package junction.al.nova_spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import junction.al.nova_spring.entities.Building;
import junction.al.nova_spring.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("building")
@Tag(name = "Building")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Building>> getAll() {
        return ResponseEntity.ok(this.buildingService.getAll());
    }
}
