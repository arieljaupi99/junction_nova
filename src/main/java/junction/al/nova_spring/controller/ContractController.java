package junction.al.nova_spring.controller;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contract")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Contract>> getAll() {
        return ResponseEntity.ok(this.contractService.getAll());
    }

    @GetMapping("/forUser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contract> findContractPerUser(
            @RequestParam("userId")String userId
    ){
        return ResponseEntity.ok(this.contractService.findContractByUserId(userId));
    }

}
