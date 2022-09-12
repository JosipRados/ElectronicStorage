package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.ProjectElementDataDTO;
import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.ProjectDTO;
import electronicstorage.BussinesLogic.Models.ProjectElementsDTO;
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
}
