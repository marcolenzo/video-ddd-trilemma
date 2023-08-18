package eu.marcolenzo.videodddtrilemma.controllers;

import eu.marcolenzo.videodddtrilemma.exceptions.InvalidUsernameException;
import eu.marcolenzo.videodddtrilemma.exceptions.UsernameConflictException;
import eu.marcolenzo.videodddtrilemma.exceptions.UsernameNotFoundException;
import eu.marcolenzo.videodddtrilemma.model.entities.User;
import eu.marcolenzo.videodddtrilemma.model.services.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}/actions/updateUsername")
    public User updateUsername(@PathVariable String id, @RequestBody String newUsername)
            throws UsernameNotFoundException, InvalidUsernameException, UsernameConflictException {

        return userService.updateUsername(id, newUsername);

    }
}
