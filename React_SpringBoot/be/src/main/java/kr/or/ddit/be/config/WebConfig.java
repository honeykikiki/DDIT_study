package kr.or.ddit.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 요청을 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
                .allowCredentials(true) // Allow credentials (cookies, authorization headers, etc.)
        ;
    }
}
