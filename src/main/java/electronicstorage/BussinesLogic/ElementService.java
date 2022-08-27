package electronicstorage.BussinesLogic;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;
import electronicstorage.Models.PageModel;
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
                currentElement.elementId = result.getLong("ELE_Id");
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
    @Override
    public PageModel GetElementsPage(int page, List<ElementEntity> allElements){
        ResultSet result = _elementRepository.GetAllElements();

        PageModel pageModel = new PageModel();
        int firstElement = (10 * page) - 10;
        int lastElement = (10 * page) - 1;
        int numberOfElements = allElements.size() - 1;

        if(firstElement == 0){
            pageModel.noPrevious = true;
        }

        if(numberOfElements <= lastElement){
            lastElement = numberOfElements;
            pageModel.noNext = true;
        }


        for(int i = firstElement; i <= lastElement; i++){
            pageModel.pageElements.add(allElements.get(i));
        }

        return pageModel;
    }
    @Override
    public List<ElementEntity> FilterElements(String keyword, List<ElementEntity> allElements){
        List<ElementEntity> filteredList = new ArrayList<>();
        for(ElementEntity element : allElements){
            if(element.code != null && element.code.contains(keyword)){
                filteredList.add(element);
                continue;
            }
            if(element.type != null && element.type.contains(keyword)){
                filteredList.add(element);
                continue;
            }
            if(element.size != null && element.size.contains(keyword)){
                filteredList.add(element);
                continue;
            }
            if(element.comment != null && element.comment.contains(keyword)){
                filteredList.add(element);
            }
            if(element.value != null && element.value.contains(keyword)){
                filteredList.add(element);
            }
        }

        return filteredList;
    }
    @Override
    public void AddNewElement(ElementModel element){
        boolean isAdded = _elementRepository.CreateNewElement(element);

        if(!isAdded){
            System.out.println("Element " + element.code + "was not added, error ocured.");
        }
    }
    @Override
    public ElementEntity GetOneElement(long id){
        ResultSet result = _elementRepository.GetOneElement(id);
        ElementEntity currentElement = new ElementEntity();
        try{
            while(result.next()){
                currentElement.elementId = result.getLong("ELE_Id");
                currentElement.code = result.getString("ELE_Code");
                currentElement.value = result.getString("ELE_Value");
                currentElement.unit = result.getString("ELE_Unit");
                currentElement.type = result.getString("ELE_Type");
                currentElement.size = result.getString("ELE_Size");
                currentElement.comment = result.getString("ELE_Comment");
            }
        }
        catch(Exception ex){
            System.out.println("Couldn't get all elements, ERR: " + ex.getMessage());
        }
        return currentElement;
    }
    @Override
    public void UpdateElement(ElementEntity element){
        _elementRepository.UpdateElement(element);
    }
}
