package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectElementDataModel {
    long projectId;
    String elementCode;
    int elementQuantity;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public int getElementQuantity() {
        return elementQuantity;
    }

    public void setElementQuantity(int elementQuantity) {
        this.elementQuantity = elementQuantity;
    }
}
