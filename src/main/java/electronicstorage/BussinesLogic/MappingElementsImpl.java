package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ElementDTO;
import electronicstorage.UI.Models.ElementModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingElementsImpl implements MappingElements {


    @Override
    public ElementModel MappingElementEntityToModel(ElementDTO dbElement){
        ElementModel element = new ElementModel();
        element.elementId = dbElement.elementId;
        element.code = dbElement.code;
        element.size = dbElement.size;
        element.type = dbElement.type;
        element.value = dbElement.value;
        element.unit = dbElement.unit;
        element.comment = dbElement.comment;
        return element;
    }

    @Override
    public List<ElementModel> MappingElementEntityToModel(List<ElementDTO> dbElements){
        List<ElementModel> elements = new ArrayList<ElementModel>();
        for(ElementDTO dbElement : dbElements){
            elements.add(MappingElementEntityToModel(dbElement));
        }
        return elements;
    }

    @Override
    public ElementDTO MappingElementModelToEntity(ElementModel element){
        ElementDTO dbElement = new ElementDTO();
        dbElement.elementId = element.elementId;
        dbElement.code = element.code;
        dbElement.size = element.size;
        dbElement.type = element.type;
        dbElement.value = element.value;
        dbElement.unit = element.unit;
        dbElement.comment = element.comment;
        return dbElement;
    }

    @Override
    public List<ElementDTO> MappingElementModelToEntity(List<ElementModel> elements){
        List<ElementDTO> dbElements = new ArrayList<ElementDTO>();
        for(electronicstorage.UI.Models.ElementModel element : elements){
            dbElements.add(MappingElementModelToEntity(element));
        }
        return dbElements;
    }
}
