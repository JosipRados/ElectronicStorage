package electronicstorage.Repository;

import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;

import java.sql.ResultSet;


public interface IElementRepository {
    ResultSet GetAllElements();

    boolean CreateNewElement(ElementModel element);

    boolean UpdateElement(ElementEntity element);

    boolean DeleteElement(long elementId);
}
