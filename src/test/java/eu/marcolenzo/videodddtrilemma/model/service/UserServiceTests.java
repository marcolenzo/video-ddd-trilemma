package eu.marcolenzo.videodddtrilemma.model.service;

import eu.marcolenzo.videodddtrilemma.exceptions.UsernameConflictException;
import eu.marcolenzo.videodddtrilemma.model.entities.User;
import eu.marcolenzo.videodddtrilemma.model.repositories.UserRepository;
import eu.marcolenzo.videodddtrilemma.model.services.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void updateUsername_success() throws Exception {
        String id = "xyz";
        String currentUsername = "marcolenzo";
        String newUsername = "lenzomarco";

        given(this.userRepository.findById(id))
                .willReturn(Optional.of(new User(id, currentUsername)));

        given(this.userRepository.findByUsername(newUsername))
                .willReturn(Optional.empty());

        User updatedUser = this.userService.updateUsername(id, newUsername);
        assertThat(updatedUser.getId()).isEqualTo(id);
        assertThat(updatedUser.getUsername()).isEqualTo(newUsername);
    }

    @Test
    public void updateUsername_conflict() throws Exception {
        String id = "xyz";
        String currentUsername = "marcolenzo";
        String newUsername = "marcolenzo";

        given(this.userRepository.findById(id))
                .willReturn(Optional.of(new User(id, currentUsername)));

        given(this.userRepository.findByUsername(newUsername))
                .willReturn(Optional.of(new User(id, currentUsername)));

        Exception exception = assertThrows(UsernameConflictException.class, () -> {
            this.userService.updateUsername(id, newUsername);
        });
    }

}
