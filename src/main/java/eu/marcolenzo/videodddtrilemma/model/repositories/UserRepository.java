package eu.marcolenzo.videodddtrilemma.model.repositories;

import eu.marcolenzo.videodddtrilemma.model.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

}
