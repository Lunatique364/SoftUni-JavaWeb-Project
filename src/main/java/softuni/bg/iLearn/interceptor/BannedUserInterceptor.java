package softuni.bg.iLearn.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import softuni.bg.iLearn.model.User;
import softuni.bg.iLearn.service.UserService;

import java.security.Principal;

@Component
public class BannedUserInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(BannedUserInterceptor.class);

    @Autowired
    public BannedUserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception{

        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            String name = userPrincipal.getName();

            if (name != null) {
                if (isUserBanned(name)) {
                    logger.info("Banned user attempted to access: {}", request.getRequestURI());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isUserBanned(String username) {
        User user = userService.findByUsername(username).orElse(null);
        return user != null && user.getIsBanned();
    }
}
