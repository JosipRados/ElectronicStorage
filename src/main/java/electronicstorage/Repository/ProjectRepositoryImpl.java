package electronicstorage.Repository;

import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.UI.Models.NewProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {
    DataAccessImpl _dataAccessImpl;
    ErrorLogRepository _errorLogRepository;

    @Override
    public ResultSet GetAllProjects(){

        CallableStatement newStatement = null;
        String procedure = "{call spGetProjects}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.execute();
            return newStatement.getResultSet();
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet GetOneProject(long id){
        CallableStatement newStatement = null;
        String procedure = "{call spGetProjectById (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, id);
            newStatement.execute();
            return newStatement.getResultSet();
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean CreateNewProject(NewProjectModel project){
        CallableStatement newStatement = null;
        String procedure = "{call spSetNewProject (?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, project.name);
            newStatement.setString(2, project.company);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean UpdateProject(ProjectEntity project){
        CallableStatement newStatement;
        String procedure = "{call spUpdateElement (?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, project.id);
            newStatement.setString(2, project.name);
            newStatement.setString(3, project.company);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return false;
        }
    }

    @Override
    public ResultSet GetElementsOfProject(long projectId){
        CallableStatement newStatement;
        String procedure = "{call spGetElementsOfProject (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, projectId);
            newStatement.execute();
            return newStatement.getResultSet();
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }
}
