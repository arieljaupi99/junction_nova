package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ContractRepo extends MongoRepository<Contract, String> {
    Contract findContractByRoomIdIsIgnoreCase(String roomId);
    void deleteAllById(String id);
}
