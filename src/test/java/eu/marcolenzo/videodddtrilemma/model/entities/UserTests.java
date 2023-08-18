package eu.marcolenzo.videodddtrilemma.model.entities;

import eu.marcolenzo.videodddtrilemma.exceptions.InvalidUsernameException;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTests {

    @Test
    public void updateUsername_success() throws Exception {
        String id = "xyz";
        String currentUsername = "marcolenzo";
        String newUsername = "lenzomarco";

        User user = new User(id, currentUsername);

        user.updateUsername(newUsername);

        assertThat(user.getUsername()).isEqualTo(newUsername);
    }

    @Test
    public void updateUsername_invalid_minChar() throws Exception {
        String id = "xyz";
        String currentUsername = "marcolenzo";
        String newUsername = "marco";

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            User user = new User(id, currentUsername);
            user.updateUsername(newUsername);
        });
    }

    @Test
    public void updateUsername_invalid_maxChar() throws Exception {
        String id = "xyz";
        String currentUsername = "marcolenzo";
        String newUsername = "marcolenzo111111111111111111111111111111111111111111111";

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            User user = new User(id, currentUsername);
            user.updateUsername(newUsername);
        });
    }

}
