package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.StorageDTO;
import electronicstorage.Repository.ErrorLogRepository;
import electronicstorage.Repository.StorageRepository;
import electronicstorage.UI.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final MappingStorage _mappingStorage;
    private final ErrorLogRepository _errorLogRepository;
    private final StorageRepository _storageRepository;

    @Override
    public List<StorageModel> GetAllStorage(){
        try{
            return _mappingStorage.MappingStorageDTOToModel(_storageRepository.GetAllStorage());
        }
        catch(Exception ex){
            _errorLogRepository.WriteLog("GetAllStorage in StorageService", ex.getMessage());
            return null;
        }
    }

    @Override
    public StoragePageModel GetStoragePage(int page, List<StorageModel> allStorage){

        StoragePageModel pageModel = new StoragePageModel();
        int firstElement = (8 * page) - 8;
        int lastElement = (8 * page) - 1;
        int numberOfElements = allStorage.size() - 1;

        if(firstElement == 0){
            pageModel.noPrevious = true;
        }

        if(numberOfElements <= lastElement){
            lastElement = numberOfElements;
            pageModel.noNext = true;
        }


        for(int i = firstElement; i <= lastElement; i++){
            pageModel.pageElements.add(allStorage.get(i));
        }

        return pageModel;
    }

    @Override
    public RepositoryResponseModel AddNewStorage(NewStorageModel newStorage){
        ProcedureResponseDTO repositoryResponse = _storageRepository.AddNewStorage(_mappingStorage.MappingNewStorageModelToStorageDTO(newStorage));

        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());

    }

    @Override
    public List<StorageModel> FilterStorage(String keyword, List<StorageModel> allStorageList){
        List<StorageModel> filteredList = new ArrayList<StorageModel>();

        for(StorageModel storage : allStorageList){
            String storageNumber = String.valueOf(storage.getStorageNumber());
            if(storageNumber != null && storageNumber.contains(keyword)){
                filteredList.add(storage);
                continue;
            }
            if(storage.getElementCode() != null && storage.getElementCode().contains(keyword)){
                filteredList.add(storage);
            }
        }

        return filteredList;
    }

    @Override
    public RepositoryResponseModel UpdateStorage(long storageId, int quantity){
        StorageDTO storage = new StorageDTO();
        storage.setQuantity(quantity);
        storage.setStorageId(storageId);
        ProcedureResponseDTO repositoryResponse = _storageRepository.UpdateStorageData(storage);

        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
    }

    @Override
    public RepositoryResponseModel DeleteStorage(long storageId){
        ProcedureResponseDTO repositoryResponse = _storageRepository.DeleteStorage(storageId);

        if(repositoryResponse.isSuccess())
            return new RepositoryResponseModel(true, "");
        else
            return new RepositoryResponseModel(false, repositoryResponse.getErrorMessage());
    }
}
