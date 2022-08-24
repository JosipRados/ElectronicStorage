package electronicstorage;

import bussineslogic.Start;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ElectronicStorageApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicStorageApplication.class, args);
        IElementRepository elementRepository = new ElementRepository();
        IProjectRepository projectRepository = new ProjectRepository();
        Start start = new Start(elementRepository, projectRepository);
        start.StartProgram();


    }



}
