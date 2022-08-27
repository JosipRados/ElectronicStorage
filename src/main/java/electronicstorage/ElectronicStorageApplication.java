package electronicstorage;

import electronicstorage.Repository.DataAccessImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ElectronicStorageApplication  {


    public static void main(String[] args) {

        SpringApplication.run(ElectronicStorageApplication.class, args);
        DataAccessImpl dataAccessImpl = new DataAccessImpl();
        dataAccessImpl.ConnectToDatabase();
    }



}
