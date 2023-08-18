package eu.marcolenzo.videodddtrilemma.controllers;

import eu.marcolenzo.videodddtrilemma.exceptions.UsernameConflictException;
import eu.marcolenzo.videodddtrilemma.model.entities.User;
import eu.marcolenzo.videodddtrilemma.model.services.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void updateUsername_success() throws Exception {
        String id = "xyz";
        String newUsername = "marcol";
        String url = "/users/".concat(id).concat("/actions/updateUsername");

        given(this.userService.updateUsername(id, newUsername))
                .willReturn(new User(id, newUsername));

        this.mvc.perform(post(url).content(newUsername).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("username").value(newUsername));
    }

}
