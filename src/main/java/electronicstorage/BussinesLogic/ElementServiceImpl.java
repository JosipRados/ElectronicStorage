package electronicstorage.BussinesLogic;

import electronicstorage.Repository.ErrorLogRepository;
import electronicstorage.UI.Models.ElementModel;
import electronicstorage.UI.Models.NewElementModel;
import electronicstorage.UI.Models.PageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import electronicstorage.Repository.ElementRepository;

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
            return _mappingElements.MappingElementEntityToModel(_elementRepository.GetAllElements());
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
        try{
            return _mappingElements.MappingElementEntityToModel(_elementRepository.GetOneElement(id));
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

}
