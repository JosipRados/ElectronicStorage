package electronicstorage.BussinesLogic;

import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.UI.Models.ElementModel;

import java.util.List;

public interface MappingElements {
    ElementModel MappingElementEntityToModel(ElementEntity dbElement);

    List<ElementModel> MappingElementEntityToModel(List<ElementEntity> dbElements);

    ElementEntity MappingElementModelToEntity(ElementModel element);

    List<ElementEntity> MappingElementModelToEntity(List<ElementModel> elements);
}
