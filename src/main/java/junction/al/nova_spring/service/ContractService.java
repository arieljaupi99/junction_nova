package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.model.ContractRequest;

import java.util.List;

public interface ContractService {
    List<Contract> getAll();

    Contract findContractByUserId(String userId);

    Contract save(Contract contract);
}
