package junction.al.nova_spring.security.repo;

import junction.al.nova_spring.security.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
