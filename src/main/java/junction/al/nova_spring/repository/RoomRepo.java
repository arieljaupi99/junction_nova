package junction.al.nova_spring.repository;

import junction.al.nova_spring.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepo extends MongoRepository<Room, String> {
    @Query(value = "{floorNumber :  ?0}")
    List<Room> findRoomsByFloorNumberIs(String floorId);
}
