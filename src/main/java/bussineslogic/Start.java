package bussineslogic;

import repository.DataAccess;
import repository.IElementRepository;
import repository.IProjectRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Start {
    static List<String> sids = new ArrayList<String>();
    static List<String> lids = new ArrayList<String>();
    private final IElementRepository _elementRepository;
    private final IProjectRepository _projectRepository;

    public Start(IElementRepository elementRepository, IProjectRepository projectsRepository){
        _elementRepository = elementRepository;
        _projectRepository = projectsRepository;
    }
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

            dataAccess.databaseConnection.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
