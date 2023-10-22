package junction.al.nova_spring.controller;


import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.service.ContractService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contract/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Contract>> getAll() {
        return ResponseEntity.ok(this.contractService.getAll());
    }

    @GetMapping("/contract/byId")
    public ResponseEntity<Contract> getById(
            @RequestParam("contractId")String contractId
    ){
        return ResponseEntity.ok(this.contractService.findById(contractId));
    }


    @GetMapping("/contract/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam String pdfPath) throws IOException {
        Path file = Paths.get(pdfPath);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName().toString())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.toFile().length())
                .body(resource);
    }

    @PostMapping("/contract/delete/{contractId}")
    public void deleteContract(
            @PathVariable("contractId")String contractId
    ){
        this.contractService.deleteById(contractId);
    }

}
