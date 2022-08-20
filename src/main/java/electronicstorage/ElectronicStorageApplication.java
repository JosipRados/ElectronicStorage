package electronicstorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.DataAccess;
import repository.ElementRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static repository.DataAccess.databaseConnection;

@SpringBootApplication
public class ElectronicStorageApplication  {



    public static void main(String[] args) {
        SpringApplication.run(ElectronicStorageApplication.class, args);
        try{
        DataAccess.ConnectToDatabase();
        ElementRepository.GetAllElements();
        databaseConnection.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }



}
