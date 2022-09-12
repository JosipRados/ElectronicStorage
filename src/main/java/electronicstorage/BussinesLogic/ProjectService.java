package electronicstorage.BussinesLogic;

import electronicstorage.UI.Models.ProjectElementDataModel;
import electronicstorage.UI.Models.ProjectDetailsModel;
import electronicstorage.UI.Models.ProjectModel;
import electronicstorage.UI.Models.RepositoryResponseModel;

import java.util.List;

public interface ProjectService {
    List<ProjectDetailsModel> GetAllProjects();

    ProjectModel GetOneProject(long projectId);

    RepositoryResponseModel SaveElementToProject(ProjectElementDataModel newElement);

    RepositoryResponseModel UpdateElementOfProject(ProjectElementDataModel currentElement);
}
