package electronicstorage.Repository;

import electronicstorage.Models.ProjectEntity;
import electronicstorage.Models.ProjectModel;

import java.sql.ResultSet;

public interface IProjectRepository {
    ResultSet GetAllProjects();

    boolean CreateNewProject(ProjectModel project);

    boolean UpdateProject(ProjectEntity project);
}
