package com.learningfullstack.server;

import com.learningfullstack.server.enumeration.Status;
import com.learningfullstack.server.model.Server;
import com.learningfullstack.server.repo.ServerRepo;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
    @Bean
    CommandLineRunner run(ServerRepo serverRepo){
        return args -> {
            serverRepo.save(new Server(null,"192.168.1.168","Ubuntu Linux","16GB",
                    "Personal Pc","http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serverRepo.save(new Server(null,"192.168.1.58","Fedora Linux","16GB",
                    "Dell  Tower","http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
            serverRepo.save(new Server(null,"192.168.1.21","MS 2008","32GB",
                    "web server","http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
            serverRepo.save(new Server(null,"192.168.1.14","Red Hat Enterprise Linux","64GB",
                    "Mail server","http://localhost:8080/server/image/server4.jpg", Status.SERVER_DOWN));

        };

    }

}
