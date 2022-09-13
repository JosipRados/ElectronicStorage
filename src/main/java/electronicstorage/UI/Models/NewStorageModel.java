package electronicstorage.UI.Models;

public class NewStorageModel {
    long storageName;
    int quantity;
    String elementCode;

    public long getStorageName() {
        return storageName;
    }

    public void setStorageName(long storageName) {
        this.storageName = storageName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }
}
