package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.model.ContractRequest;
import junction.al.nova_spring.repository.ResidentRepo;
import junction.al.nova_spring.service.ContractService;
import junction.al.nova_spring.service.FileService;
import junction.al.nova_spring.service.ResidentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepo residentRepo;
    private final ContractService contractService;
    private final FileService fileService;

    public ResidentServiceImpl(ResidentRepo residentRepo, ContractService contractService, FileService fileService) {
        this.residentRepo = residentRepo;
        this.contractService = contractService;
        this.fileService = fileService;
    }

    @Override
    public List<Resident> getAll() {
        return residentRepo.findAll();
    }

    @Override
    public Resident findResidentByNameAndSurname(String userName, String userSurname) {
        return this.residentRepo.findResidentByNameIsIgnoreCaseAndSurnameIsIgnoreCase(userName, userSurname).orElse(null);
    }

    @Override
    public Resident save(Resident resident) {
        boolean present = this.residentRepo.findResidentByNameIsIgnoreCaseAndSurnameIsIgnoreCase(resident.getName(), resident.getSurname()).isPresent();
        if (present) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This resident already exists");
        }
        return this.residentRepo.save(resident);
    }

    @Override
    public void update(Resident resident) {
        this.residentRepo.findResidentById(resident.getId()).ifPresent(residentById -> this.residentRepo.save(resident));
    }

    @Override
    public void updateContractForResident(ContractRequest contractRequest) {
        log.info("DEBUG");
        Resident resident = residentRepo.findResidentById(contractRequest.getResidentId()).orElse(null);
        if (resident != null) {
            Contract contract;
            String pdfPath = this.fileService.saveAndReturnPath(contractRequest.getResidentId(), contractRequest.getBase64String(), contractRequest.getType());
            if (resident.getContractId() != null && !resident.getContractId().isEmpty()) {
                //Update the contract
                contract = this.contractService.findcontractById(resident.getContractId());
                contract.setPdfPath(pdfPath);
                this.contractService.save(contract);
            } else {
                //Create
                contract = Contract.generateForResident(contractRequest);
                contract.setPdfPath(pdfPath);
                String id = this.contractService.save(contract).getId();
                contract.setId(id);
            }

            resident.setContractId(contract.getId());
            this.residentRepo.save(resident);
        }
    }
}
