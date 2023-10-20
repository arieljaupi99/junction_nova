package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Floor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepo extends MongoRepository<Floor, String> {
    @Query(value = "{buildingId :  ?0}")
    public List<Floor> floorByBuildingIdIs(String buildingId);
}
