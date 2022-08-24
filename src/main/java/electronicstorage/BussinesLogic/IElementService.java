package electronicstorage.BussinesLogic;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;

import java.util.List;


public interface IElementService {
    List<ElementEntity> GetAllElements();
    void AddNewElement(ElementModel element);
}
