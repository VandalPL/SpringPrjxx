package edu.uph.ii.platformy.validators;

import org.springframework.beans.factory.annotation.Autowired;
import edu.uph.ii.platformy.services.UserService;
import edu.uph.ii.platformy.validators.annotations.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    public void initialize(UniqueUsername constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        //w RepositoryInitializer userService jeszcze nie ma dlatego userService == null.
        return userService == null || (login != null && userService.isUniqueLogin(login));
    }

}