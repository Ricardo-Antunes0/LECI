package Aula8;

public interface EmailValidator {

    static EmailValidator getInstance() {
        return null;
    }

    boolean isValid(String email);

}
