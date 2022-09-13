package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.StorageDTO;
import electronicstorage.UI.Models.NewStorageModel;
import electronicstorage.UI.Models.StorageModel;

import java.util.List;

public interface MappingStorage {
    StorageModel MappingStorageDTOToModel(StorageDTO storageDTO);

    List<StorageModel> MappingStorageDTOToModel(List<StorageDTO> storageDTOList);

    StorageDTO MappingStorageModelToDTO(StorageModel storage);

    List<StorageDTO> MappingStorageModelToDTO(List<StorageModel> storageList);

    StorageDTO MappingNewStorageModelToStorageDTO(NewStorageModel newStorage);
}
