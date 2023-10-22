package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.repository.ContractRepo;
import junction.al.nova_spring.repository.RoomRepo;
import junction.al.nova_spring.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepo contractRepo;
    private final RoomRepo roomRepo;

    public ContractServiceImpl(ContractRepo contractRepo, RoomRepo roomRepo) {
        this.contractRepo = contractRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public List<Contract> getAll() {
        return this.contractRepo.findAll();
    }


    @Override
    public Contract save(Contract contract) {
        Contract contractByRoomIdIsIgnoreCase = this.contractRepo.findContractByRoomIdIsIgnoreCase(contract.getRoomId());
        if (contractByRoomIdIsIgnoreCase != null) {
            this.contractRepo.delete(contractByRoomIdIsIgnoreCase);
        }
        return this.contractRepo.save(contract);
    }

    @Override
    public Contract findById(String contractId) {
        return this.contractRepo.findById(contractId).orElse(null);
    }

    @Override
    public void deleteById(String contractId) {
        Room room = this.roomRepo.findRoomByContractIdIgnoreCase(contractId);
        room.setContractId("");
        this.roomRepo.save(room);
        this.contractRepo.deleteAllById(contractId);
    }


}
