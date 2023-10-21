package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.repository.ContractRepo;
import junction.al.nova_spring.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepo contractRepo;

    public ContractServiceImpl(ContractRepo contractRepo) {
        this.contractRepo = contractRepo;
    }

    @Override
    public List<Contract> getAll() {
        return this.contractRepo.findAll();
    }


    @Override
    public Contract save(Contract contract) {
        Contract contractByRoomIdIsIgnoreCase = this.contractRepo.findContractByRoomIdIsIgnoreCase(contract.getRoomId());
        if (contractByRoomIdIsIgnoreCase != null){
            this.contractRepo.delete(contractByRoomIdIsIgnoreCase);
        }
        return this.contractRepo.save(contract);
    }


}
