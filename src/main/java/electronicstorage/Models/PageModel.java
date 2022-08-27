package electronicstorage.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PageModel {
    public List<ElementEntity> pageElements = new ArrayList<ElementEntity>();
    public boolean noNext;
    public boolean noPrevious;

    public List<ElementEntity> getPageElements() {
        return pageElements;
    }

    public void setPageElements(List<ElementEntity> pageElements) {
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
