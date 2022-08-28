package electronicstorage.BussinesLogic;

import electronicstorage.Repository.ErrorLogRepository;
import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.UI.Models.ElementModel;
import electronicstorage.UI.Models.NewElementModel;
import electronicstorage.UI.Models.PageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import electronicstorage.Repository.ElementRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {
    public final ElementRepository _elementRepository;
    public final MappingElements _mappingElements;
    public final ErrorLogRepository _errorLogRepository;

    @Override
    public List<ElementModel> GetAllElements(){
        try{
            List<ElementEntity> dbElements = ReturnElementList(_elementRepository.GetAllElements());
            return _mappingElements.MappingElementEntityToModel(dbElements);
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetAllElements in ElementService", ex.getMessage());
            return null;
        }
    }
    @Override
    public void AddNewElement(NewElementModel element){
        try{
            boolean isAdded = _elementRepository.CreateNewElement(element);
            if(!isAdded){
                _errorLogRepository.WriteLog("AddNewElement in ElementService", "Didn't add new element");
            }
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("AddNewElement in ElementService", ex.getMessage());
        }
    }
    @Override
    public ElementModel GetOneElement(long id){
        ResultSet result = _elementRepository.GetOneElement(id);
        try{
            List<ElementEntity> element = ReturnElementList(_elementRepository.GetOneElement(id));
            return _mappingElements.MappingElementEntityToModel(element.get(0));
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetOneElement in ElementService", ex.getMessage());
            return null;
        }
    }
    @Override
    public void UpdateElement(ElementModel element){
        try{
            _elementRepository.UpdateElement(_mappingElements.MappingElementModelToEntity(element));
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("UpdateElement in ElementService", ex.getMessage());
        }

    }
    @Override
    public PageModel GetElementsPage(int page, List<ElementModel> allElements){

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
    public List<ElementModel> FilterElements(String keyword, List<ElementModel> allElements){
        List<ElementModel> filteredList = new ArrayList<ElementModel>();

        for(ElementModel element : allElements){
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

    private List<ElementEntity> ReturnElementList(ResultSet result) throws SQLException {
        List<ElementEntity> allElements = new ArrayList<>();

        while(result.next()) {
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

        return allElements;
    }
}
