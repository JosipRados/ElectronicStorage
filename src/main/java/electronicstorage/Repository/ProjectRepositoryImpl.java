package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.ProjectElementDataDTO;
import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.ProjectDTO;
import electronicstorage.BussinesLogic.Models.ProjectElementsDTO;
import electronicstorage.UI.Models.NewProjectModel;
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
public class ProjectRepositoryImpl implements ProjectRepository {
    DataAccessImpl _dataAccessImpl;
    ErrorLogRepository _errorLogRepository;

    @Override
    public List<ProjectDTO> GetAllProjects(){

        CallableStatement newStatement = null;
        String procedure = "{call spGetProjects}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.execute();
            return ReturnProjectList(newStatement.getResultSet());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    @Override
    public ProjectDTO GetOneProject(long id){
        CallableStatement newStatement = null;
        String procedure = "{call spGetProjectById (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, id);
            newStatement.execute();
            return ReturnProjectList(newStatement.getResultSet()).get(0);
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
    public boolean UpdateProject(ProjectDTO project){
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
    public List<ProjectElementsDTO> GetElementsOfProject(long projectId){
        CallableStatement newStatement;
        String procedure = "{call spGetElementsOfProject (?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, projectId);
            newStatement.execute();
            return ReturnProjectElementsList(newStatement.getResultSet());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    public ProcedureResponseDTO CheckExistingElement(String elementCode){
        CallableStatement newStatement;
        String procedure = "{call spCheckExistingElement (?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, elementCode);
            newStatement.registerOutParameter(2, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(2);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);

        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }
    public ProcedureResponseDTO AddNewElementToProject(ProjectElementDataDTO newElement){
        CallableStatement newStatement;
        String procedure = "{call spAddProjectElement (?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, newElement.getElementCode());
            newStatement.setLong(2, newElement.getProjectId());
            newStatement.setInt(3, newElement.getElementQuantity());
            newStatement.registerOutParameter(4, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(4);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);

        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    public ProcedureResponseDTO UpdateProjectElement(ProjectElementDataDTO currentElement){
        CallableStatement newStatement;
        String procedure = "{call spUpdateProjectElement (?, ?, ?, ?)}";
        try{
            newStatement = _dataAccessImpl.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, currentElement.getElementCode());
            newStatement.setLong(2, currentElement.getProjectId());
            newStatement.setInt(3, currentElement.getElementQuantity());
            newStatement.registerOutParameter(4, Types.NVARCHAR);
            newStatement.execute();
            String errorMessage = newStatement.getNString(4);
            if(errorMessage == null)
                return new ProcedureResponseDTO(true, "");
            else
                return new ProcedureResponseDTO(false, errorMessage);

        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("Couldn't execute " + procedure + " in ProjectRepository", ex.getMessage());
            return null;
        }
    }

    private List<ProjectDTO> ReturnProjectList(ResultSet result) throws SQLException {
        List<ProjectDTO> allProjects = new ArrayList<ProjectDTO>();

        while(result.next()) {
            ProjectDTO currentProject = new ProjectDTO();
            currentProject.id = result.getLong("PRO_Id");
            currentProject.name = result.getString("PRO_Name");
            currentProject.company = result.getString("PRO_Company");
            currentProject.timeStamp = result.getTime("PRO_TimeStamp");
            allProjects.add(currentProject);
        }

        return allProjects;
    }

    private List<ProjectElementsDTO> ReturnProjectElementsList(ResultSet result) throws SQLException {
        List<ProjectElementsDTO> projectElements = new ArrayList<ProjectElementsDTO>();

        while(result.next()) {
            ProjectElementsDTO projectElement = new ProjectElementsDTO();
            projectElement.setElementId(result.getLong("ElementId"));
            projectElement.setQuantity(result.getInt("ElementQuantity"));
            projectElements.add(projectElement);
        }

        return projectElements;
    }


}
