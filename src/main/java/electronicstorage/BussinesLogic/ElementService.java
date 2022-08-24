package electronicstorage.BussinesLogic;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import electronicstorage.Repository.IElementRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementService implements IElementService {
    public final IElementRepository _elementRepository;

    @Override
    public List<ElementEntity> GetAllElements(){
        List<ElementEntity> allElements = new ArrayList<>();


        ResultSet result = _elementRepository.GetAllElements();

        try{
            while(result.next()){
                ElementEntity currentElement = new ElementEntity();
                currentElement.id = result.getLong("ELE_Id");
                currentElement.code = result.getString("ELE_Code");
                currentElement.value = result.getString("ELE_Value");
                currentElement.unit = result.getString("ELE_Unit");
                currentElement.type = result.getString("ELE_Type");
                currentElement.size = result.getString("ELE_Size");
                currentElement.comment = result.getString("ELE_Comment");
                allElements.add(currentElement);
            }
        }
        catch(Exception ex){
            System.out.println("Couldn't get all elements, ERR: " + ex.getMessage());
        }

        return allElements;
    }

    public void AddNewElement(ElementModel element){
        boolean isAdded = _elementRepository.CreateNewElement(element);

        if(!isAdded){
            System.out.println("Element " + element.code + "was not added, error ocured.");
        }
    }
}
