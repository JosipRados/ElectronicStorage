package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.ElementDTO;
import electronicstorage.UI.Models.NewElementModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ElementRepositoryImpl implements ElementRepository {
    public DataAccessImpl _dataAccessImpl = new DataAccessImpl();
    public final ErrorLogRepository _errorLogRepository;


    @Override
    public List<ElementDTO> GetAllElements(){
            CallableStatement newStatement;
            String procedure = "{call spGetElements}";
            try{
                newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
                newStatement.execute();
                return ReturnElementList(newStatement.getResultSet());
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
    public boolean UpdateElement(ElementDTO element){
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
    public ElementDTO GetOneElement(long elementId){
        CallableStatement newStatement;
        String procedure = "{call spGetElementById (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, elementId);

            newStatement.execute();
            return ReturnElementList(newStatement.getResultSet()).get(0);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ElementRepository", ex.getMessage());
            return null;
        }
    }

    private List<ElementDTO> ReturnElementList(ResultSet result) throws SQLException {
        List<ElementDTO> allElements = new ArrayList<>();

        while(result.next()) {
            ElementDTO currentElement = new ElementDTO();
            currentElement.elementId = result.getLong("ELE_Id");
            currentElement.code = result.getString("ELE_Code");
            currentElement.value = result.getString("ELE_Value");
            currentElement.unit = result.getString("ELE_Unit");
            currentElement.type = result.getString("ELE_Type");
            currentElement.size = result.getString("ELE_Size");
            currentElement.comment = result.getString("ELE_Comment");
            allElements.add(currentElement);
        }

        return allElements;
    }
}
