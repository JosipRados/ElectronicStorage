package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProjectElementsModel {
    long ElementId;
    String ElementQuantity;
    String ElementCode;

    public long getElementId() {
        return ElementId;
    }

    public void setElementId(long elementId) {
        ElementId = elementId;
    }

    public String getElementQuantity() {
        return ElementQuantity;
    }

    public void setElementQuantity(String elementQuantity) {
        ElementQuantity = elementQuantity;
    }

    public String getElementCode() {
        return ElementCode;
    }

    public void setElementCode(String elementCode) {
        ElementCode = elementCode;
    }

    @Override
    public String toString() {
        return "ProjectElementsModel{" +
                "ElementId=" + ElementId +
                ", ElementQuantity='" + ElementQuantity + '\'' +
                ", ElementCode='" + ElementCode + '\'' +
                '}';
    }
}
