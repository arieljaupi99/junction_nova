package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepo extends MongoRepository<Resident, String> {
    Optional<Resident> findResidentByNameIsIgnoreCaseAndSurnameIsIgnoreCase(String name, String surname);

    Optional<Resident> findResidentById(String id);
}
