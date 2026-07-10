package com.example.Selenium.tests;

import com.example.Selenium.data.OrderFactory;
import com.example.Selenium.data.Orderbuilder;
import com.example.Selenium.repository.OrderRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers(disabledWithoutDocker = true)
 class OrderTestIT {
     @Container
     static MySQLContainer mySQL = new MySQLContainer(DockerImageName.parse("mysql:8.0"))
             .withDatabaseName("retail_test")
             .withUsername("root")
             .withPassword("Cipi@0971");

     static OrderRepository repository;
     static OrderFactory factory;

     @BeforeAll
     static void migrateSchema(){
         Flyway.configure()
                 .dataSource(mySQL.getJdbcUrl(), mySQL.getUsername(), mySQL.getPassword())
                 .locations("classpath:db/migration")
                 .load()
                 .migrate();

         repository = new OrderRepository(mySQL.getJdbcUrl(), mySQL.getUsername(), mySQL.getPassword());
         factory = new OrderFactory(repository);
     }

     @BeforeEach
     void reset(){
         repository.resetMutableTables();
     }



     @Test
     void flywaySeedingReferenceDataButNoPerTestOrders(){
         assertEquals(4,repository.referenceStatusCount());
         assertEquals(0,repository.count());
     }

     @Test
    void persistedBuilderDataAgainstIsolatedMysql(){
         long id = factory.persisted(Orderbuilder.newOrder().withQuantity(3));

         assertTrue(id>0);
         assertEquals(1,repository.count());
     }

    @Test
    void countsOnlyPersistedTestOrders(){
         factory.persisted(Orderbuilder.newOrder());
         factory.persisted(Orderbuilder.newOrder().withName("SKU-2").withQuantity(2));

         assertEquals(0,repository.count());
    }

    @Disabled
    @Test
    void resetMakesTestOrderIndependent(){
         assertEquals(0,repository.count());
         factory.persisted(Orderbuilder.newOrder().withRefunded());

         assertEquals(1,repository.count());
         assertEquals(1,repository.countByStatus("REFUNDED"));
    }

    @Test
    void makeATestBroken() throws Exception {
         repository.makeBroke();
    }

}
