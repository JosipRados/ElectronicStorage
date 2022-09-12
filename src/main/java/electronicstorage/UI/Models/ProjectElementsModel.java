package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProjectElementsModel {

    public ElementModel element = new ElementModel();
    public int elementQuantity;

    public ElementModel getElement() {
        return element;
    }

    public void setElement(ElementModel element) {
        this.element = element;
    }

    public int getElementQuantity() {
        return elementQuantity;
    }

    public void setElementQuantity(int elementQuantity) {
        this.elementQuantity = elementQuantity;
    }
}
