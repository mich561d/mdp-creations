package exceptions;

import java.io.Serializable;

public class ExceptionDTO implements Serializable {

    private int statusCode;
    private String reasonPhrase;

    ExceptionDTO(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }
}
