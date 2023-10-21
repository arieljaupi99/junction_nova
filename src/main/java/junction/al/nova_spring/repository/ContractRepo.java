package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ContractRepo extends MongoRepository<Contract, String> {
    @Query(value = "{residentId :  ?0}")
    Optional<Contract> findContractByResidentId(String residentId);
}
