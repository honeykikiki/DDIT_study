package kr.or.ddit.be.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("요기가 실행되었는지 check?");
        // /ys/aa/oracle.jpg
        registry.addResourceHandler("/ys/**") // 웹 접근 경로
                .addResourceLocations("file:///Users/heoseongjin/Documents/GitHub/ddit/ys/");  // 서버내 실제 경로
    }
}
