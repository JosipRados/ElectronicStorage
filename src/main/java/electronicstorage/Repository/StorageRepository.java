package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.ProcedureResponseDTO;
import electronicstorage.BussinesLogic.Models.StorageDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StorageRepository {
    List<StorageDTO> GetAllStorage();

    StorageDTO GetOneStorage(long storageId);

    ProcedureResponseDTO UpdateStorageData(StorageDTO storage);

    ProcedureResponseDTO AddNewStorage(StorageDTO storage);

    ProcedureResponseDTO DeleteStorage(long storageId);

    default List<StorageDTO> ReturnStorageList(ResultSet result) throws SQLException {
        List<StorageDTO> allProjects = new ArrayList<StorageDTO>();

        while (result.next()) {
            StorageDTO currentProject = new StorageDTO();
            currentProject.setStorageId(result.getLong("STO_Id"));
            currentProject.setStorageNumber(result.getLong("STO_RollNumber"));
            currentProject.setElementId(result.getLong("STO_ElementId"));
            currentProject.setElementCode(result.getString("STO_ElementCode"));
            currentProject.setQuantity(result.getInt("STO_RollQuantity"));

            allProjects.add(currentProject);
        }

        return allProjects;
    }
}
