package electronicstorage.BussinesLogic;

import electronicstorage.UI.Models.ElementModel;
import electronicstorage.UI.Models.NewElementModel;
import electronicstorage.UI.Models.PageModel;

import java.util.List;


public interface ElementService {
    List<ElementModel> GetAllElements();

    void AddNewElement(NewElementModel element);

    ElementModel GetOneElement(long id);

    void UpdateElement(ElementModel element);

    PageModel GetElementsPage(int page, List<ElementModel> allElements);

    List<ElementModel> FilterElements(String keyword, List<ElementModel> allElements);
}
