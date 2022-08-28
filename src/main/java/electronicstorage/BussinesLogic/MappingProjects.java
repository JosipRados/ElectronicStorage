package electronicstorage.BussinesLogic;

import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.UI.Models.ProjectModel;

import java.util.List;

public interface MappingProjects {
    ProjectModel MappingProjectEntityToModel(ProjectEntity dbProject);

    List<ProjectModel> MappingProjectEntityToModel(List<ProjectEntity> dbProjects);

    ProjectEntity MappingProjectModelToEntity(ProjectModel project);

    List<ProjectEntity> MappingProjectModelToEntity(List<ProjectModel> projects);
}
