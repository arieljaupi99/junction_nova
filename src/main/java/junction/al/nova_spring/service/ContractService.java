package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> getAll();


    Contract save(Contract contract);

    Contract findById(String contractId);

    void deleteById(String contractId);
}
