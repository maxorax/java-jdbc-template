package com.epam.izh.rd.online.autcion;

import com.epam.izh.rd.online.autcion.configuration.JdbcTemplateConfiguration;
import com.epam.izh.rd.online.autcion.entity.Bid;
import com.epam.izh.rd.online.autcion.repository.JdbcTemplatePublicAuction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class Main{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

