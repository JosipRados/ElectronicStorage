package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ElementAvailabilityModel {
    long elementId;
    String elementCode;
    long elementAvailableQuantity;
    long elementRequiredQuantity;
    boolean isEnough;

    public long getElementId() {
        return elementId;
    }

    public void setElementId(long elementId) {
        this.elementId = elementId;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public long getElementAvailableQuantity() {
        return elementAvailableQuantity;
    }

    public void setElementAvailableQuantity(long elementAvailableQuantity) {
        this.elementAvailableQuantity = elementAvailableQuantity;
    }

    public long getElementRequiredQuantity() {
        return elementRequiredQuantity;
    }

    public void setElementRequiredQuantity(long elementRequiredQuantity) {
        this.elementRequiredQuantity = elementRequiredQuantity;
    }

    public boolean isEnough() {
        return isEnough;
    }

    public void setEnough(boolean enough) {
        isEnough = enough;
    }
}
