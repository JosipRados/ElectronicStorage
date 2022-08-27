package electronicstorage.Repository;

import org.springframework.stereotype.Component;

import java.sql.CallableStatement;

@Component
public class ErrorLogRepositoryImpl implements ErrorLogRepository {
    DataAccessImpl dataAccessImpl;

    @Override
    public void WriteLog(String method, String error){
        CallableStatement newStatement = null;
        String procedure = "{call spSetErrorLog (?, ?)}";
        try{
            newStatement = dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, method);
            newStatement.setString(2, error);
            newStatement.execute();
        }
        catch(Exception ex){
            System.out.println("Couldn't execute error log, ERR: " + ex.getMessage());
        }
    }
}
