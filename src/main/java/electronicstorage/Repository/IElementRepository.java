package electronicstorage.Repository;

import electronicstorage.Models.ElementEntity;

import java.sql.ResultSet;


public interface IElementRepository {
    ResultSet GetAllElements();

    boolean CreateNewElement(ElementEntity element);

    boolean UpdateElement(ElementEntity element);

    boolean DeleteElement(long elementId);
}
