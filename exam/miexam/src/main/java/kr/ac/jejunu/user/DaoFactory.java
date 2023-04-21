package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

    @Value("${db.className}")
    private String className;
    @Value("${db.userName}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;

    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        return new UserDao(jdbcContext());
    }

    @Bean
    public JdbcContext jdbcContext() throws ClassNotFoundException {
        return new JdbcContext(dataSource());
    }

    @Bean
    DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        simpleDriverDataSource.setUrl(url);
        simpleDriverDataSource.setPassword(password);
        simpleDriverDataSource.setUsername(userName);
        return simpleDriverDataSource;
    }

}
