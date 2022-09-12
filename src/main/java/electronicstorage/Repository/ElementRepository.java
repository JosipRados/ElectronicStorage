package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.ElementDTO;
import electronicstorage.UI.Models.NewElementModel;

import java.util.List;


public interface ElementRepository {
    List<ElementDTO> GetAllElements();

    boolean CreateNewElement(NewElementModel element);

    boolean UpdateElement(ElementDTO element);

    boolean DeleteElement(long elementId);

    ElementDTO GetOneElement(long elementId);


}
