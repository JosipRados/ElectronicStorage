package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.*;
import electronicstorage.UI.Models.NewProjectModel;

import java.util.List;

public interface ProjectRepository {
    List<ProjectDTO> GetAllProjects();

    ProjectDTO GetOneProject(long id);

    boolean CreateNewProject(NewProjectModel project);

    boolean UpdateProject(ProjectDTO project);

    List<ProjectElementsDTO> GetElementsOfProject(long id);

    ProcedureResponseDTO CheckExistingElement(String elementCode);

    ProcedureResponseDTO AddNewElementToProject(ProjectElementDataDTO newElement);

    ProcedureResponseDTO UpdateProjectElement(ProjectElementDataDTO currentElement);

    ProcedureResponseDTO DeleteProjectElement(ProjectElementDataDTO element);

    ProcedureResponseDTO AddNewProject(ProjectDTO newProject);

    ElementAvailabilityDTO GetAvailableQuantityOfElement(String elementCode);
}
