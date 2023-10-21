package junction.al.nova_spring.service;

import junction.al.nova_spring.entities.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getAll();

    Resident findResidentByNameAndSurname(String userName, String userSurname);

    Resident save(Resident resident);

    void  update(Resident resident);

    Resident findResidentById(String residentId);

    void saveForUpdate(Resident residentById);

    //void updateContractForResident(RoomRequestForUpdateResidentsAndContract contractRequest);
}
