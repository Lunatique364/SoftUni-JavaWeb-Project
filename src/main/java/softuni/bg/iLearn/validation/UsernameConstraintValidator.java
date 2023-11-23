package softuni.bg.iLearn.validation;

import com.mysql.cj.xdevapi.Result;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import softuni.bg.iLearn.service.UserService;

import java.util.Arrays;

import static org.attoparser.ParseException.message;

public class UsernameConstraintValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;


    public UsernameConstraintValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername username) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (userService.isUniqueUsername(username)) {
            return true;

        }
        //TODO

        return false;
    }
}
