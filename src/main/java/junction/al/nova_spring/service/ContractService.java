package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> getAll();

    Contract findContractByResidentId(String residentId);

    Contract save(Contract contract);

    Contract findcontractById(String contractId);
}
