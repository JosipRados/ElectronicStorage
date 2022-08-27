package electronicstorage.Repository;

import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.UI.Models.ProjectModel;

import java.sql.ResultSet;

public interface ProjectRepository {
    ResultSet GetAllProjects();

    boolean CreateNewProject(ProjectModel project);

    boolean UpdateProject(ProjectEntity project);
}
