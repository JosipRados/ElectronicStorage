package electronicstorage.UI.Models;

import electronicstorage.Repository.Models.ElementEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PageModel {
    public List<ElementModel> pageElements = new ArrayList<ElementModel>();
    public boolean noNext;
    public boolean noPrevious;

    public List<ElementModel> getPageElements() {
        return pageElements;
    }

    public void setPageElements(List<ElementModel> pageElements) {
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
