package electronicstorage.BussinesLogic;

import electronicstorage.Models.ElementEntity;

import java.util.List;


public interface IElementService {
    List<ElementEntity> GetAllElements();
}
