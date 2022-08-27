package electronicstorage.Repository;

import electronicstorage.Repository.Models.ElementEntity;
import electronicstorage.UI.Models.NewElementModel;

import java.sql.ResultSet;


public interface ElementRepository {
    ResultSet GetAllElements();

    boolean CreateNewElement(NewElementModel element);

    boolean UpdateElement(ElementEntity element);

    boolean DeleteElement(long elementId);

    ResultSet GetOneElement(long elementId);
}
