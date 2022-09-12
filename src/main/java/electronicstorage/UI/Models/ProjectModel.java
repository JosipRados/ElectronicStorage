package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    public ProjectDetailsModel projectDetails = new ProjectDetailsModel();
    public List<ProjectElementsModel> projectElements = new ArrayList<ProjectElementsModel>();

    public ProjectDetailsModel getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetailsModel projectDetails) {
        this.projectDetails = projectDetails;
    }

    public List<ProjectElementsModel> getProjectElements() {
        return projectElements;
    }

    public void setProjectElements(List<ProjectElementsModel> projectElements) {
        this.projectElements = projectElements;
    }
}
