package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/resident/getAll")
    public ResponseEntity<List<Resident>> getAll() {
        return ResponseEntity.ok(this.residentService.getAll());
    }

   /*
    @PostMapping("/uploadContract")
    public void updateContract(
            @RequestBody RoomRequestForUpdateResidentsAndContract contractRequest
    ) {
        this.residentService.updateContractForResident(contractRequest);
    }
    */

    @PostMapping("/resident/save")
    public ResponseEntity<Resident> saveResident(
            @RequestBody Resident resident
    ) {
        return ResponseEntity.ok(this.residentService.save(resident));
    }
}
