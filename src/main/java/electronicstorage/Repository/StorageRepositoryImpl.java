package electronicstorage.Repository;

import com.nimbusds.openid.connect.sdk.assurance.Procedure;
import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.ProjectDTO;
import electronicstorage.BussinesLogic.Models.StorageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StorageRepositoryImpl implements StorageRepository {
    DataAccessImpl _dataAccessImpl;
    ErrorLogRepository _errorLogRepository;

    @Override
    public List<StorageDTO> GetAllStorage(){

        CallableStatement newStatement = null;
        String procedure = "{call spGetStorage}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.execute();
            return ReturnStorageList(newStatement.getResultSet());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in StorageRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public StorageDTO GetOneStorage(long storageId){
        CallableStatement newStatement = null;
        String procedure = "{call spGetOneStorage (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, storageId);
            newStatement.execute();
            return ReturnStorageList(newStatement.getResultSet()).get(0);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in StorageRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public ProcedureResponseDTO UpdateStorageData(StorageDTO storage){
        CallableStatement newStatement = null;
        String procedure = "{call spUpdateStorage (?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, storage.getStorageId());
            newStatement.setInt(2,storage.getQuantity());
            newStatement.registerOutParameter(3, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(3);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in StorageRepository", ex.getMessage());
            return new ProcedureResponseDTO(false, ex.getMessage());
        }
    }

    @Override
    public ProcedureResponseDTO AddNewStorage(StorageDTO storage){
        CallableStatement newStatement = null;
        String procedure = "{call spSetNewStorage (?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setInt(1,storage.getQuantity());
            newStatement.setString(2,storage.getElementCode());
            newStatement.setLong(3, storage.getStorageNumber());
            newStatement.registerOutParameter(4, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(4);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in StorageRepository", ex.getMessage());
            return new ProcedureResponseDTO(false, ex.getMessage());
        }
    }

    @Override
    public ProcedureResponseDTO DeleteStorage(long storageId){
        CallableStatement newStatement = null;
        String procedure = "{call spDeleteStorage (?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, storageId);
            newStatement.registerOutParameter(2, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(2);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in StorageRepository", ex.getMessage());
            return new ProcedureResponseDTO(false, ex.getMessage());
        }
    }

}
