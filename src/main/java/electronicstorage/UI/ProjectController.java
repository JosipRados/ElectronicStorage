package electronicstorage.UI;

import electronicstorage.BussinesLogic.ElementService;
import electronicstorage.BussinesLogic.ProjectService;
import electronicstorage.UI.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService _projectService;
    private final ElementService _elementService;

    static List<ProjectDetailsModel> _allProjects = new ArrayList<ProjectDetailsModel>();
    static String errorMessage = "";
    static boolean isSuccess = true;

    @GetMapping("/projects")
    public String listProjects(Model model){
        _allProjects = _projectService.GetAllProjects();
        model.addAttribute("Projects", _allProjects);
        return "Projects";
    }

    @GetMapping("/projects/{id}")
    public String detailsProject(Model model,
                                 @PathVariable("id") long id){
        ProjectModel project = _projectService.GetOneProject(id);
        String tempErrorMessage = "";
        if(!isSuccess){
            tempErrorMessage = errorMessage;
            errorMessage = "";
            isSuccess = true;
        }
        model.addAttribute("project",project);
        model.addAttribute("projectElement", project.projectElements);
        model.addAttribute("errorMessage", tempErrorMessage);

        return "ProjectDetails";
    }

    @PostMapping("/projects/newProjectElement")
    public String addNewProjectElement(ProjectElementDataModel newElement,
                                       Model model){
        RepositoryResponseModel response = _projectService.SaveElementToProject(newElement);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/projects/" + newElement.getProjectId();
    }

    @PostMapping("/projects/updateElement")
    public String updateElementQuantity(ProjectElementDataModel newElement){
        RepositoryResponseModel response = _projectService.UpdateElementOfProject(newElement);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/projects/" + newElement.getProjectId();
    }

    @PostMapping("/projects/deleteElement")
    public String deleteProjectElement(ProjectElementDataModel element){
        RepositoryResponseModel response = _projectService.DeleteElementOfProject(element);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/projects/" + element.getProjectId();
    }

    @PostMapping("/projects/newProject")
    public String addNewProject(NewProjectModel newProject){
        RepositoryResponseModel response = _projectService.AddNewProject(newProject);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/projects";
    }
}
