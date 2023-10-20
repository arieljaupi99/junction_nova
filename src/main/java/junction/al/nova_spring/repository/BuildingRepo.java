package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Building;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildingRepo extends MongoRepository<Building, String> {
}
