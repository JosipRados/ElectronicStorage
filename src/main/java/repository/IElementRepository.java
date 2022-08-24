package repository;

import Models.ElementEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;


public interface IElementRepository {
    ResultSet GetAllElements();

    boolean CreateNewElement(ElementEntity element);

    boolean UpdateElement(ElementEntity element);

    boolean DeleteElement(long elementId);
}
