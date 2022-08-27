package electronicstorage.Repository;

import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.UI.Models.NewElementModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;

@Component
@RequiredArgsConstructor
public class ElementRepositoryImpl implements ElementRepository {
    public DataAccessImpl _dataAccessImpl = new DataAccessImpl();
    public final ErrorLogRepository _errorLogRepository;


    @Override
    public ResultSet GetAllElements(){
            CallableStatement newStatement;
            String procedure = "{call spGetElements}";
            try{
                newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
                newStatement.execute();
                return newStatement.getResultSet();
            }
            catch(Exception ex){
                _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
                return null;
            }
    }
    @Override
    public boolean CreateNewElement(NewElementModel element){
        CallableStatement newStatement;
        String procedure = "{call spSetNewElement (?, ?, ?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
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
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
            return false;
        }
    }
    @Override
    public boolean UpdateElement(ElementEntity element){
        CallableStatement newStatement;
        String procedure = "{call spUpdateElement (?, ?, ?, ?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, element.elementId);
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
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
            return false;
        }
    }
    @Override
    public boolean DeleteElement(long elementId){
        CallableStatement newStatement;
        String procedure = "{call spDeleteElement (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, elementId);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
            return false;
        }
    }
    @Override
    public ResultSet GetOneElement(long elementId){
        CallableStatement newStatement;
        String procedure = "{call spGetElementById (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, elementId);

            newStatement.execute();
            return newStatement.getResultSet();
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
            return null;
        }
    }
}
