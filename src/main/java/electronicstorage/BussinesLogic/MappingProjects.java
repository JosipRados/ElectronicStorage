package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ProjectElementDataDTO;
import electronicstorage.BussinesLogic.Models.ProjectDTO;
import electronicstorage.UI.Models.NewProjectModel;
import electronicstorage.UI.Models.ProjectElementDataModel;
import electronicstorage.UI.Models.ProjectDetailsModel;

import java.util.List;

public interface MappingProjects {
    ProjectDetailsModel MappingProjectEntityToModel(ProjectDTO dbProject);

    List<ProjectDetailsModel> MappingProjectEntityToModel(List<ProjectDTO> dbProjects);

    ProjectDTO MappingProjectModelToEntity(ProjectDetailsModel project);

    List<ProjectDTO> MappingProjectModelToEntity(List<ProjectDetailsModel> projects);

    ProjectElementDataModel MappingProjectElementDataDTOToModel(ProjectElementDataDTO newElementDTO);

    ProjectElementDataDTO MappingProjectElementDataModelToDTO(ProjectElementDataModel newElement);

    ProjectDTO MappingNewProjectModelToProjectDTO(NewProjectModel newProject);
}
