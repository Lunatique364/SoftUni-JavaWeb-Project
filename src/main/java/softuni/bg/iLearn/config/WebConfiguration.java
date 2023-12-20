package softuni.bg.iLearn.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.bg.iLearn.interceptor.BannedUserInterceptor;
import softuni.bg.iLearn.interceptor.MaintenanceInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final BannedUserInterceptor bannedUserInterceptor;
    private final MaintenanceInterceptor maintenanceInterceptor;

    @Autowired
    public WebConfiguration(BannedUserInterceptor bannedUserInterceptor, MaintenanceInterceptor maintenanceInterceptor) {
        this.bannedUserInterceptor = bannedUserInterceptor;
        this.maintenanceInterceptor = maintenanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bannedUserInterceptor);
        registry.addInterceptor(maintenanceInterceptor);
    }

}
