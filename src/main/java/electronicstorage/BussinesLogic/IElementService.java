package electronicstorage.BussinesLogic;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;
import electronicstorage.Models.PageModel;

import java.util.List;
import java.util.Optional;


public interface IElementService {
    List<ElementEntity> GetAllElements();

    void AddNewElement(ElementModel element);

    PageModel GetElementsPage(int page, List<ElementEntity> allElements);

    List<ElementEntity> FilterElements(String keyword, List<ElementEntity> allElements);

    ElementEntity GetOneElement(long id);

    void UpdateElement(ElementEntity element);
}
