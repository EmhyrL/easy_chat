package com.EmhyrL.easyChat.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ServerApplication
{
    public static void main( String[] args ) {
        new SpringApplication(ServerApplication.class).run(args);
    }
}
