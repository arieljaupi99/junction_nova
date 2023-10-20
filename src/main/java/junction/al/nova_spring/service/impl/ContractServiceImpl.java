package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.repository.ContractRepo;
import junction.al.nova_spring.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Contract findContractByUserId(String userId) {
        return this.contractRepo.findContractByUserIdIs(userId).orElseGet(null);
    }
}
