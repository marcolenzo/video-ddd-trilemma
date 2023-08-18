package eu.marcolenzo.videodddtrilemma.model.entities;

import eu.marcolenzo.videodddtrilemma.exceptions.InvalidUsernameException;
import org.apache.commons.lang3.StringUtils;


public class User {

    private final String id;
    private String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void updateUsername(String newUsername)
            throws InvalidUsernameException {
        if(!StringUtils.isAlphanumeric(newUsername) || newUsername.length() < 8 || newUsername.length() > 24)
            throw new InvalidUsernameException("Username must be alphanumeric and have between 8 to 24 characters.");

        this.username = newUsername;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
