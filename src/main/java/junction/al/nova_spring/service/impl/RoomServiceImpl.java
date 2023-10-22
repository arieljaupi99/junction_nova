package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Contract;
import junction.al.nova_spring.entities.Resident;
import junction.al.nova_spring.entities.Room;
import junction.al.nova_spring.model.RoomRequestForUpdateResidentsAndContract;
import junction.al.nova_spring.model.RoomRequestSingleUserUpdate;
import junction.al.nova_spring.model.RoomResponse;
import junction.al.nova_spring.repository.RoomRepo;
import junction.al.nova_spring.service.ContractService;
import junction.al.nova_spring.service.FileService;
import junction.al.nova_spring.service.ResidentService;
import junction.al.nova_spring.service.RoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final ResidentService residentService;
    private final FileService fileService;
    private final ContractService contractService;

    public RoomServiceImpl(RoomRepo roomRepo, ResidentService residentService, FileService fileService, ContractService contractService) {
        this.roomRepo = roomRepo;
        this.residentService = residentService;
        this.fileService = fileService;
        this.contractService = contractService;
    }

    @Override
    public List<Room> getAllRoomsPerFloor(String floorId) {
        return this.roomRepo.findRoomsByFloorIdIs(floorId);
    }

    @Override
    public Optional<Room> findRoomById(String roomId) {
        return this.roomRepo.findById(roomId);
    }

    @Override
    @Transactional
    public Room updateAlarm(String roomId, boolean alarm) {
        Room room = this.roomRepo.findById(roomId).orElse(null);
        if (room != null) {
            room.setAlarm(alarm);
            return this.roomRepo.save(room);
        }
        return null;
    }

    @Override
    @Transactional
    public RoomResponse updateRoomResident(RoomRequestSingleUserUpdate roomRequestSingleUserUpdate) {
        log.info("DEBUG");
        RoomResponse response = new RoomResponse();
        Room room = this.roomRepo.findById(roomRequestSingleUserUpdate.getRoomId()).orElse(null);
        if (room != null) {
            Resident resident = this.residentService.findResidentByNameAndSurname(roomRequestSingleUserUpdate.getName(), roomRequestSingleUserUpdate.getSurname());
            if (resident == null) {
                resident = this.residentService.save(Resident.generateFromRoomRequest(roomRequestSingleUserUpdate));
                response.setNewResident(true);
            } else if (resident.getRoomId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This resident has already a room!");
            }
            resident.setRoomId(roomRequestSingleUserUpdate.getRoomId());
            residentService.update(resident);
            room.setSingleResidentId(resident.getId());
            roomRepo.save(room);
            response.setResidentId(resident.getId());
            return response;
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean updateResidentAndContract(RoomRequestForUpdateResidentsAndContract request) {
        log.info("DEBUG");
        //Ruaj pdf ne server dhe kthe path(uniqe per room)
        String pdfPath = this.fileService.saveAndReturnPath(request.getRoomId(), request.getBase64String(), request.getType());
        Contract contract = Contract.generateContractFromRequest(request, pdfPath);
        Contract saved = this.contractService.save(contract);
        Room room = this.roomRepo.findById(request.getRoomId()).orElse(null);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The room with id : " + request.getRoomId() + " does not exists");
        }
        if (room.getResidentId() == null || room.getResidentId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The room with id : " + request.getRoomId() + " should have at least one resident!");
        }
        room.setContractId(contract.getId());
        List<String> residents = room.getResidentId();
        this.roomRepo.save(room);
        //Merr cdo resident dhe kontrollo nese ka contract apo jo
        for (String residentId : residents) {
            Resident residentById = this.residentService.findResidentById(residentId);
            if (residentById == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resident with id : " + residentId + " does not exists!");
            }
            residentById.setContractId(saved.getId());
            residentById.setRoomId(request.getRoomId());
            this.residentService.saveForUpdate(residentById);
        }
        return true;
    }

    @Override
    @Transactional
    public void deleteResidentFromRoom(String residentId, String roomId) {
        Room byId = this.roomRepo.findById(roomId).orElse(null);
        if (byId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This room Id: " + roomId + " does not exists");
        }
        List<String> residentIdList = byId.getResidentId();
        List<String> filteredList = residentIdList.stream().filter(id -> !id.equals(residentId)).toList();
        byId.setResidentId(filteredList);
        this.roomRepo.save(byId);
    }

    @Override
    public void addResidentFromRoom(String residentId, String roomId) {
        log.info("DEBUG");
        Room byId = this.roomRepo.findById(roomId).orElse(null);
        int capacity = Integer.parseInt(byId.getCapacity());
            if (byId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This room Id: " + roomId + " does not exists");
            }
        List<String> listId = byId.getResidentId();
        boolean shouldAdd = true;
        for (String id : listId) {
            if (id.equals(residentId)) {
                shouldAdd = false;
                break;
            }
        }
        if (listId.size()+1 > capacity){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Maximum capacity is occured for room with id :" +roomId);
        }
        if (shouldAdd) {
            byId.setSingleResidentId(residentId);
            this.roomRepo.save(byId);
        }
    }
}
