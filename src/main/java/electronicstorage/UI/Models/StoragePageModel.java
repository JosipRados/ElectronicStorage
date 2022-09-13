package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class StoragePageModel {
    public List<StorageModel> pageElements = new ArrayList<StorageModel>();
    public boolean noNext;
    public boolean noPrevious;

    public List<StorageModel> getPageElements() {
        return pageElements;
    }

    public void setPageElements(List<StorageModel> pageElements) {
        this.pageElements = pageElements;
    }

    public boolean isNoNext() {
        return noNext;
    }

    public void setNoNext(boolean noNext) {
        this.noNext = noNext;
    }

    public boolean isNoPrevious() {
        return noPrevious;
    }

    public void setNoPrevious(boolean noPrevious) {
        this.noPrevious = noPrevious;
    }
}
