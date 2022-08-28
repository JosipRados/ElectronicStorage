package electronicstorage.UI;

import electronicstorage.BussinesLogic.ElementService;
import electronicstorage.BussinesLogic.ProjectService;
import electronicstorage.UI.Models.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService _projectService;
    private final ElementService _elementService;

    static List<ProjectModel> _allProjects = new ArrayList<ProjectModel>();

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
    model.addAttribute("project", project);

    return "ProjectDetails";
    }
}
