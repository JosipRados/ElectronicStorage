package electronicstorage.Repository;

import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.UI.Models.NewProjectModel;

import java.sql.ResultSet;

public interface ProjectRepository {
    ResultSet GetAllProjects();

    ResultSet GetOneProject(long id);

    boolean CreateNewProject(NewProjectModel project);

    boolean UpdateProject(ProjectEntity project);

    ResultSet GetElementsOfProject(long id);
}
