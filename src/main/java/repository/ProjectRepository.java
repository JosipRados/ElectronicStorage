package repository;

import Models.ProjectEntity;
import Models.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;

@Component
@RequiredArgsConstructor
public class ProjectRepository implements IProjectRepository {
    DataAccess dataAccess;

    @Override
    public ResultSet GetAllProjects(){

        CallableStatement newStatement = null;
        String procedure = "{call spGetProjects}";
        try{
            newStatement = dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.execute();
            return newStatement.getResultSet();
        }
        catch(Exception ex){
            System.out.println("Couldn't execute " + procedure + ", ERR: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean CreateNewProject(ProjectModel project){
        CallableStatement newStatement = null;
        String procedure = "{call spSetNewProject (?, ?)}";
        try{
            newStatement = dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.setString(1, project.name);
            newStatement.setString(2, project.company);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean UpdateProject(ProjectEntity project){
        CallableStatement newStatement;
        String procedure = "{call spUpdateElement (?, ?, ?)}";
        try{
            newStatement = dataAccess.databaseConnection.prepareCall(procedure);
            newStatement.setLong(1, project.id);
            newStatement.setString(2, project.name);
            newStatement.setString(3, project.company);

            newStatement.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
