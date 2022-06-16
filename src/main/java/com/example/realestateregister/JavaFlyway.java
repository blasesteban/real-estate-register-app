package com.example.realestateregister;

import org.flywaydb.core.Flyway;

public class JavaFlyway {
    public static void main(String[] args) {
        //implement code for api based migration here

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:realestateregister", "sa", "password").load();
        // Start the migration
        flyway.migrate();
    }
}