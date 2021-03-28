package wns.ForFire.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.path.book}")
    private String uploadPathBook;

    @Value("${upload.path.soc}")
    private String uploadPathSoc;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file://"+uploadPath+"/")
                .addResourceLocations("file://" + uploadPathBook + "/")
                .addResourceLocations("file://" + uploadPathSoc + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


/*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("reg");
    }*/

}
