package electronicstorage.BussinesLogic;

import electronicstorage.UI.Models.ProjectElementsModel;
import electronicstorage.UI.Models.ProjectModel;

import java.util.List;

public interface ProjectService {
    List<ProjectModel> GetAllProjects();

    ProjectModel GetOneProject(long projectId);

    ProjectElementsModel GetProjectElements(long projectId);
}
