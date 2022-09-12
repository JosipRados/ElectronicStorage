package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ElementDTO;
import electronicstorage.UI.Models.ElementModel;

import java.util.List;

public interface MappingElements {
    ElementModel MappingElementEntityToModel(ElementDTO dbElement);

    List<ElementModel> MappingElementEntityToModel(List<ElementDTO> dbElements);

    ElementDTO MappingElementModelToEntity(ElementModel element);

    List<ElementDTO> MappingElementModelToEntity(List<ElementModel> elements);
}
