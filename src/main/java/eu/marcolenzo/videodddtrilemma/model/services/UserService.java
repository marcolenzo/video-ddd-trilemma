package eu.marcolenzo.videodddtrilemma.model.services;

import eu.marcolenzo.videodddtrilemma.exceptions.InvalidUsernameException;
import eu.marcolenzo.videodddtrilemma.exceptions.UsernameConflictException;
import eu.marcolenzo.videodddtrilemma.exceptions.UsernameNotFoundException;
import eu.marcolenzo.videodddtrilemma.model.entities.User;
import eu.marcolenzo.videodddtrilemma.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUsername(String id, String newUsername)
            throws UsernameNotFoundException, InvalidUsernameException, UsernameConflictException {

        // Find user to be updated
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid user id."));

        // Verify there is no username conflict.
        // In a real world scenario we would rely on unique indexes on the DB and this check would not be necessary.
        if(userRepository.findByUsername(newUsername).isPresent())
            throw new UsernameConflictException("Username already in use.");

        user.updateUsername(newUsername);

        userRepository.save(user);

        return user;
    }
}
