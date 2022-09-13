package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.StorageDTO;
import electronicstorage.UI.Models.NewStorageModel;
import electronicstorage.UI.Models.StorageModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingStorageImpl implements MappingStorage {
    @Override
    public StorageModel MappingStorageDTOToModel(StorageDTO storageDTO){
        StorageModel storage = new StorageModel();
        storage.setStorageId(storageDTO.getStorageId());
        storage.setStorageNumber(storageDTO.getStorageNumber());
        storage.setElementCode(storageDTO.getElementCode());
        storage.setElementId(storageDTO.getElementId());
        storage.setQuantity(storageDTO.getQuantity());
        return storage;
    }

    @Override
    public List<StorageModel> MappingStorageDTOToModel(List<StorageDTO> storageDTOList){
        List<StorageModel> storageModelList = new ArrayList<StorageModel>();
        for(StorageDTO storageDTO : storageDTOList){
            StorageModel storage = new StorageModel();
            storage.setStorageId(storageDTO.getStorageId());
            storage.setStorageNumber(storageDTO.getStorageNumber());
            storage.setElementCode(storageDTO.getElementCode());
            storage.setElementId(storageDTO.getElementId());
            storage.setQuantity(storageDTO.getQuantity());
            storageModelList.add(storage);
        }
        return storageModelList;
    }

    @Override
    public StorageDTO MappingStorageModelToDTO(StorageModel storage){
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setElementCode(storage.getElementCode());
        storageDTO.setStorageNumber(storage.getStorageNumber());
        storageDTO.setQuantity(storage.getQuantity());
        storageDTO.setStorageId(storage.getStorageId());
        storageDTO.setElementId(storage.getElementId());
        return storageDTO;
    }

    @Override
    public List<StorageDTO> MappingStorageModelToDTO(List<StorageModel> storageList){
        List<StorageDTO> storageDTOList = new ArrayList<StorageDTO>();
        for(StorageModel storage : storageList){
            StorageDTO storageDTO = new StorageDTO();
            storageDTO.setElementCode(storage.getElementCode());
            storageDTO.setStorageNumber(storage.getStorageNumber());
            storageDTO.setQuantity(storage.getQuantity());
            storageDTO.setStorageId(storage.getStorageId());
            storageDTO.setElementId(storage.getElementId());
            storageDTOList.add(storageDTO);
        }
        return storageDTOList;
    }

    @Override
    public StorageDTO MappingNewStorageModelToStorageDTO(NewStorageModel newStorage){
        StorageDTO storage = new StorageDTO();
        storage.setElementCode(newStorage.getElementCode());
        storage.setStorageNumber(newStorage.getStorageName());
        storage.setQuantity(newStorage.getQuantity());
        return storage;
    }
}
