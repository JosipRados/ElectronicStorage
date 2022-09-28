package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectAvailabilityModel {
    long projectId;
    String projectName;
    List<ElementAvailabilityModel> projectElements = new ArrayList<ElementAvailabilityModel>();

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ElementAvailabilityModel> getProjectElements() {
        return projectElements;
    }

    public void setProjectElements(List<ElementAvailabilityModel> projectElements) {
        this.projectElements = projectElements;
    }
}
