package softuni.bg.iLearn.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import softuni.bg.iLearn.service.UserService;


public class UsernameConstraintValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;
    private String message;


    public UsernameConstraintValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (!userService.isUniqueUsername(username)) {
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }
}
