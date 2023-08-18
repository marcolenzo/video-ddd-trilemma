package eu.marcolenzo.videodddtrilemma.exceptions;

import java.io.Serial;

public class UsernameNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 9189982423527440644L;

    public UsernameNotFoundException(String message) {
        super(message);
    }

}


