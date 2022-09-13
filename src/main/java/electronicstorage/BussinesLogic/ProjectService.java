package electronicstorage.BussinesLogic;

import electronicstorage.UI.Models.*;

import java.util.List;

public interface ProjectService {
    List<ProjectDetailsModel> GetAllProjects();

    ProjectModel GetOneProject(long projectId);

    RepositoryResponseModel SaveElementToProject(ProjectElementDataModel newElement);

    RepositoryResponseModel UpdateElementOfProject(ProjectElementDataModel currentElement);

    RepositoryResponseModel DeleteElementOfProject(ProjectElementDataModel element);

    RepositoryResponseModel AddNewProject(NewProjectModel newProject);
}
