package electronicstorage.BussinesLogic;

import electronicstorage.UI.Models.NewStorageModel;
import electronicstorage.UI.Models.RepositoryResponseModel;
import electronicstorage.UI.Models.StorageModel;
import electronicstorage.UI.Models.StoragePageModel;

import java.util.List;

public interface StorageService {
    List<StorageModel> GetAllStorage();

    StoragePageModel GetStoragePage(int page, List<StorageModel> allStorage);

    RepositoryResponseModel AddNewStorage(NewStorageModel newStorage);

    List<StorageModel> FilterStorage(String keyword, List<StorageModel> allStorageList);

    RepositoryResponseModel UpdateStorage(long storageId, int quantity);

    RepositoryResponseModel DeleteStorage(long storageId);
}
