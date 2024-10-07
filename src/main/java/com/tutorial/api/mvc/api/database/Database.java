package com.tutorial.api.mvc.api.database;

import com.tutorial.api.mvc.api.repositories.ProductRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
docker run -d --rm --name mysql-tutorial
 -e MYSQL_ROOT_PASSWORD=abc123
 -e MYSQL_DATABASE=tutorial
 -e MYSQL_USER=linhdao22
 -e MYSQL_PASSWORD=Daolinh2002@
 -p 3306:3306
 --volume mysql-tutorial-volume:/var/lib/mysql
 mysql:latest

 docker run -d --rm --name mysql-tutorial -e MYSQL_ROOT_PASSWORD=abc123 -e MYSQL_DATABASE=tutorial -e MYSQL_USER=linhdao22 -e MYSQL_PASSWORD=Daolinh2002@ -p 3306:3306 --volume mysql-tutorial-volume:/var/lib/mysql mysql:latest

#connect to mysql
 mysql -h localhost -P 3306  --protocol=tcp -u linhdao22 -p

 */

@Configuration // khai báo đây là một class cấu hình, chứa các bean method được gọi ngay khi ứng dụng chạy
public class Database {
    //logger
    public static final Logger log = LoggerFactory.getLogger(Database.class);

    @Bean // khai báo đây là một bean method
    CommandLineRunner initDatabase(ProductRepositories productRepositories) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product product1 = new Product( "Iphone 12 Dao ngoc linh", 1000.0, "New Iphone 12");
//                Product product2 = new Product( "Iphone 13", 2000.0, "New Iphone 13");
//                log.info("insert data: " + productRepositories.save(product1));
//                log.info("insert data: " + productRepositories.save(product2));
            }
        };
    }
}
