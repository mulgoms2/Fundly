package config;

import com.fundly.chat.validate.ChatInterceptor;
import com.fundly.project.validate.LoginInterceptor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com", "com.fundly"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class, Repository.class, Mapper.class})
)
public class ServletContext implements WebMvcConfigurer {

    Path root = Paths.get(System.getProperty("user.name"));

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.tiles()
                .viewClass(TilesView.class);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[]{"/WEB-INF/tiles.xml"});
        configurer.setCheckRefresh(true);
        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");

        registry.addResourceHandler("/project/img/*")
                .addResourceLocations("file:///C:/Users/lemon/fundly/img/", "file:/Users/dobigulbi/fundly/img/");

//        registry.addResourceHandler("/img/*").addResourceLocations("file:///C:/Users/fundly/img/");

        registry.addResourceHandler("/user/img/*")
                .addResourceLocations("file:///C:/Users/" + root + "/fundly/img/", "file:/Users/" + root + "/fundly/img/");

        registry.addResourceHandler("/chat/img/*")
                .addResourceLocations("file:/Users/dobigulbi/fundly/chat/img/");

        registry.addResourceHandler("/fundly/project/img/*")
                .addResourceLocations("file:///C:/Users/" + root + "/fundly/project/img/", "file:/Users/" + root + "/fundly/project/img/");
//        registry.addResourceHandler("/user/img/*").addResourceLocations("file:////fundly/img/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new ChatInterceptor())
                .addPathPatterns("/chat*");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/project/editor/*");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("message.messages");
        ms.addBasenames("message.project_message");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
