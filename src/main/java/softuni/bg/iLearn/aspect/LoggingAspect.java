package softuni.bg.iLearn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDate;

@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* softuni.bg.iLearn.service.UserService.deleteUserByUsername(String)) && args(username))")
    public void trackUserDeletion(String username) {

    }

    @Pointcut("execution(* softuni.bg.iLearn.service.UserService.banUserByUsername(String)) && args(username))")
    public void trackUserBanning(String username) {

    }

    @After(value = "trackUserDeletion(username)", argNames = "username")
    public void logUserDeletion(String username){
        log.info("Admin deleted user {} on {}", username, LocalDate.now());
    }

    @After(value = "trackUserBanning(username)", argNames = "username")
    public void logUserBanning(String username){
        log.info("Admin banned user {} on {}", username, LocalDate.now());
    }
}
