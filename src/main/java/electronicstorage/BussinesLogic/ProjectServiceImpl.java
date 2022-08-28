package electronicstorage.BussinesLogic;

import electronicstorage.Repository.ErrorLogRepository;
import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.Repository.ProjectRepository;
import electronicstorage.UI.Models.ProjectElementsModel;
import electronicstorage.UI.Models.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository _projectRepository;
    private final MappingProjects _mappingProjects;
    private final ErrorLogRepository _errorLogRepository;

    @Override
    public List<ProjectModel> GetAllProjects(){
        try{
            List<ProjectEntity> dbProjects = ReturnProjectList(_projectRepository.GetAllProjects());
            return _mappingProjects.MappingProjectEntityToModel(dbProjects);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetAllProjects in ProjectService", ex.getMessage());
            return null;
        }
    }

    public ProjectModel GetOneProject(long projectId){
        try{
            List<ProjectEntity> project = ReturnProjectList(_projectRepository.GetOneProject(projectId));
            return _mappingProjects.MappingProjectEntityToModel(project.get(0));
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetOneProject in ProjectService", ex.getMessage());
            return null;
        }
    }

    /*public ProjectElementsModel GetProjectElements(long projectId){
        try{
            List<ProjectElementsModel> projectElements = new ArrayList<ProjectElementsModel>();
        }
        catch(Exception ex){

        }

    }*/

    private List<ProjectEntity> ReturnProjectList(ResultSet result) throws SQLException {
        List<ProjectEntity> allProjects = new ArrayList<ProjectEntity>();

        while(result.next()) {
            ProjectEntity currentProject = new ProjectEntity();
            currentProject.id = result.getLong("PRO_Id");
            currentProject.name = result.getString("PRO_Name");
            currentProject.company = result.getString("PRO_Company");
            currentProject.timeStamp = result.getTime("PRO_TimeStamp");
            allProjects.add(currentProject);
        }

        return allProjects;
    }

    private List<ProjectElementsModel>
}
