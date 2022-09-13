package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.ProjectElementsDTO;
import electronicstorage.Repository.ElementRepository;
import electronicstorage.Repository.ErrorLogRepository;
import electronicstorage.Repository.ProjectRepository;
import electronicstorage.UI.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository _projectRepository;
    private final MappingProjects _mappingProjects;
    private final ErrorLogRepository _errorLogRepository;
    private final ElementRepository _elementRepository;
    private final MappingElements _mappingElements;

    @Override
    public List<ProjectDetailsModel> GetAllProjects(){
        try{
            return _mappingProjects.MappingProjectEntityToModel(_projectRepository.GetAllProjects());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetAllProjects in ProjectService", ex.getMessage());
            return null;
        }
    }

    @Override
    public ProjectModel GetOneProject(long projectId){
        ProjectModel project = new ProjectModel();
        List<ProjectElementsModel> projectElements = new ArrayList<ProjectElementsModel>();
        try{
            project.setProjectDetails(_mappingProjects.MappingProjectEntityToModel(_projectRepository.GetOneProject(projectId)));

            List<ProjectElementsDTO> elementsList = _projectRepository.GetElementsOfProject(projectId);

            for(ProjectElementsDTO element : elementsList){
                ProjectElementsModel currentElement = new ProjectElementsModel();
                currentElement.setElement(_mappingElements.MappingElementEntityToModel(_elementRepository.GetOneElement(element.getElementId())));
                currentElement.setElementQuantity(element.getQuantity());
                projectElements.add(currentElement);
            }

            project.setProjectElements(projectElements);

            return project;
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetOneProject in ProjectService", ex.getMessage());
            return null;
        }
    }

    @Override
    public RepositoryResponseModel SaveElementToProject(ProjectElementDataModel newElement){
        ProcedureResponseDTO repositoryResponse = _projectRepository.CheckExistingElement(newElement.getElementCode());

        if(!repositoryResponse.isSuccess()){
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
        }

        repositoryResponse = _projectRepository.AddNewElementToProject(_mappingProjects.MappingProjectElementDataModelToDTO(newElement));

        if(!repositoryResponse.isSuccess()){
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
        }

        return new RepositoryResponseModel(true, "");
    }

    @Override
    public RepositoryResponseModel UpdateElementOfProject(ProjectElementDataModel currentElement){
        ProcedureResponseDTO repositoryResponse = _projectRepository.UpdateProjectElement(
                _mappingProjects.MappingProjectElementDataModelToDTO(currentElement));
        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());

    }

    @Override
    public RepositoryResponseModel DeleteElementOfProject(ProjectElementDataModel element){
        ProcedureResponseDTO repositoryResponse = _projectRepository.DeleteProjectElement(
                _mappingProjects.MappingProjectElementDataModelToDTO(element));
        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
    }

    @Override
    public RepositoryResponseModel AddNewProject(NewProjectModel newProject){
        ProcedureResponseDTO repositoryResponse = _projectRepository.AddNewProject(
                _mappingProjects.MappingNewProjectModelToProjectDTO(newProject));
        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
    }


}
