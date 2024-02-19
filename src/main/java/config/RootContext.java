package config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.fundly.**.model", "com.**.dao"})
@PropertySource(value = {"/WEB-INF/config/db.properties", "/WEB-INF/config/mailPro.properties"})
@ComponentScan(basePackages = "com.fundly", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class ))
@EnableTransactionManagement
public class RootContext {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment env; //스프링 내장객체. 외부파일을 읽어주는 기능(properties)


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.jdbcUrl"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/com/fudly/**/model/*Mapper.xml"));

        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/com/fundly/**/model/*Mapper.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/**/*Mapper.xml"));


        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JavaMailSenderImpl mailSender() throws Exception {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//        javaMailSender.setHost("smtp.gmail.com");
//        javaMailSender.setPort(587);
//        javaMailSender.setUsername(env.getProperty("mail.id"));
//        javaMailSender.setPassword(env.getProperty("mail.pwd"));

//        Properties properties = new Properties();
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.debug", "false");
//        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");


        Properties properties = new Properties();
//        try (FileInputStream fis = new FileInputStream("src/main/webapp/WEB-INF/config/mailPro.properties")) {
//            properties.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(env.getProperty("PORTONE_BASE_URL")).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE).defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
