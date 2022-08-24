package repository;

import Models.ProjectEntity;
import Models.ProjectModel;

import java.sql.ResultSet;

public interface IProjectRepository {
    ResultSet GetAllProjects();

    boolean CreateNewProject(ProjectModel project);

    boolean UpdateProject(ProjectEntity project);
}
