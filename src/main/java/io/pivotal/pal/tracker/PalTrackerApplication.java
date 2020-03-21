package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration
public class PalTrackerApplication {
    @Autowired
    private DataSource dataSource;

    public static void main (String[] args )
    {
        SpringApplication.run(PalTrackerApplication.class,args);
    }
    @Bean
    public JdbcTimeEntryRepository getTimeEntryRepository(){
        //MysqlDataSource dataSource = new MysqlDataSource();
        //dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
        return new JdbcTimeEntryRepository(dataSource);
    }
}
