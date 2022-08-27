package electronicstorage.BussinesLogic;

import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.UI.Models.ElementModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingElementsImpl implements MappingElements {


    @Override
    public ElementModel MappingElementEntityToModel(ElementEntity dbElement){
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
    public List<ElementModel> MappingElementEntityToModel(List<ElementEntity> dbElements){
        List<ElementModel> elements = new ArrayList<ElementModel>();
        for(ElementEntity dbElement : dbElements){
            elements.add(MappingElementEntityToModel(dbElement));
        }
        return elements;
    }

    @Override
    public ElementEntity MappingElementModelToEntity(ElementModel element){
        ElementEntity dbElement = new ElementEntity();
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
    public List<ElementEntity> MappingElementModelToEntity(List<ElementModel> elements){
        List<ElementEntity> dbElements = new ArrayList<ElementEntity>();
        for(electronicstorage.UI.Models.ElementModel element : elements){
            dbElements.add(MappingElementModelToEntity(element));
        }
        return dbElements;
    }
}
