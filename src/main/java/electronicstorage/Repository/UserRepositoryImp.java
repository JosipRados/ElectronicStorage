package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.RoleDTO;
import electronicstorage.BussinesLogic.Models.StorageDTO;
import electronicstorage.BussinesLogic.Models.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

    @Autowired
    DataAccessImpl _dataAccessImpl;
    @Autowired
    ErrorLogRepository _errorLogRepository;

    @Override
    public List<RoleDTO> GetAllRoles(){
        CallableStatement newStatement = null;
        String procedure = "{call spGetRoles}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.execute();
            return ReturnRoleList(newStatement.getResultSet());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in UserRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public RoleDTO GetRole(long roleId){
        CallableStatement newStatement = null;
        String procedure = "{call spGetOneRole (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, roleId);
            newStatement.execute();
            return ReturnRoleList(newStatement.getResultSet()).get(0);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in UserRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO GetUser(String username){
        CallableStatement newStatement = null;
        String procedure = "{call spGetOneUser (?, ?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, username);
            newStatement.registerOutParameter(2, Types.NVARCHAR);
            newStatement.registerOutParameter(3, Types.BIT);
            newStatement.registerOutParameter(4, Types.BIGINT);
            newStatement.registerOutParameter(5, Types.BIGINT);
            newStatement.execute();

            String password = newStatement.getNString(2);
            Boolean enabled = newStatement.getBoolean(3);
            long userId = newStatement.getLong(4);
            long roleId = newStatement.getLong(5);

            return new UserDTO(userId, username, password, enabled, roleId);

            //return ReturnUser(newStatement.getResultSet());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in UserRepository", ex.getMessage());
            return null;
        }
    }

    private List<RoleDTO> ReturnRoleList(ResultSet result) throws SQLException {
        List<RoleDTO> allRoles = new ArrayList<RoleDTO>();

        while (result.next()) {
            RoleDTO currentRole = new RoleDTO();
            currentRole.setRoleId(result.getLong("URO_Id"));
            currentRole.setRoleName(result.getString("URO_Role"));
            allRoles.add(currentRole);
        }
        return allRoles;
    }

    private UserDTO ReturnUser(ResultSet result) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(result.getLong("USD_Id"));
        user.setUsername(result.getString("USD_Username"));
        user.setPassword(result.getString("USD_Password"));
        user.setEnabled(result.getBoolean("USD_Enabled"));
        user.setRoleId(result.getLong("USD_UserRole"));
        return user;
    }
}
