package repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static repository.DataAccess.databaseConnection;

public class ElementRepository {
    static List<String> sids = new ArrayList<String>();
    static List<String> lids = new ArrayList<String>();
    public static void GetAllElements(){
        try {

            Statement sqlStatement = databaseConnection.createStatement();
            ResultSet result = null;
            result=sqlStatement.executeQuery("SELECT * FROM Elements");
            System.out.println(result);
            while (result.next()) {
                sids.add(result.getString(2));
                lids.add(result.getString(3));
            }
            System.out.println(sids);
            System.out.println(lids);
            result.close();
        }
        catch(Exception ex){
            System.out.printf("ERROR ELEMENTS");
        }
    }
}
