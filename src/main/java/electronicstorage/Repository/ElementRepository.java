package electronicstorage.Repository;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Component
public class ElementRepository implements IElementRepository {
    public DataAccess _dataAccess = new DataAccess();


    @Override
    public ResultSet GetAllElements(){
            CallableStatement newStatement;
            String procedure = "{call spGetElements}";
            try{
                newStatement = _dataAccess.databaseConnection.prepareCall(procedure);
                newStatement.execute();
                return newStatement.getResultSet();
            }
            catch(Exception ex){
                System.out.println("Couldn't execute " + procedure + ", ERR: " + ex.getMessage());
                return null;
            }
    }
    @Override
    public boolean CreateNewElement(ElementModel element){
        CallableStatement newStatement;
        String procedure = "{call spSetNewElement (?, ?, ?, ?, ?, ?)}";
        try{
            newStatement = _dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, element.code);
            newStatement.setString(2, element.value);
            newStatement.setString(3, element.unit);
            newStatement.setString(4, element.type);
            newStatement.setString(5, element.size);
            newStatement.setString(6, element.comment);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    @Override
    public boolean UpdateElement(ElementEntity element){
        CallableStatement newStatement;
        String procedure = "{call spUpdateElement (?, ?, ?, ?, ?, ?, ?)}";
        try{
            newStatement = _dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, element.id);
            newStatement.setString(2, element.code);
            newStatement.setString(3, element.value);
            newStatement.setString(4, element.unit);
            newStatement.setString(5, element.type);
            newStatement.setString(6, element.size);
            newStatement.setString(7, element.comment);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    @Override
    public boolean DeleteElement(long elementId){
        CallableStatement newStatement;
        String procedure = "{call spDeleteElement (?)}";
        try{
            newStatement = _dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, elementId);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
