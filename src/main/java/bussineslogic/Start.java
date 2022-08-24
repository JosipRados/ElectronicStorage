package bussineslogic;

import electronicstorage.BussinesLogic.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import electronicstorage.Repository.DataAccess;
import electronicstorage.Repository.IElementRepository;
import electronicstorage.Repository.IProjectRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Start {
    static List<String> sids = new ArrayList<String>();
    static List<String> lids = new ArrayList<String>();

    private final IElementRepository _elementRepository;
    private final IProjectRepository _projectRepository;
    private final ElementService _elementService;


    public void StartProgram(){

        DataAccess dataAccess = new DataAccess();


        try{
            dataAccess.ConnectToDatabase();

            ResultSet result = _projectRepository.GetAllProjects();//_elementRepository.GetAllElements();

            System.out.println(result);
            while (result.next()) {
                sids.add(result.getString(2));
                lids.add(result.getString(3));
            }
            System.out.println(sids);
            System.out.println(lids);
            result.close();
            //ElementEntity element = new ElementEntity(10005,"T.003.11.1003", "100", "R", "test", "0203", "Comment");
            //boolean success = _elementRepository.CreateNewElement(element);

            //System.out.println(success);

            System.out.println(_elementService.GetAllElements());

            dataAccess.databaseConnection.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
