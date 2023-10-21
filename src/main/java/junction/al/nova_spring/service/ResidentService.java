package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.model.ContractRequest;

import java.util.List;

public interface ResidentService {
    List<Resident> getAll();

    Resident findResidentByNameAndSurname(String userName, String userSurname);

    Resident save(Resident resident);

    void  update(Resident resident);

    void updateContractForResident(ContractRequest contractRequest);
}
