package electronicstorage.BussinesLogic;

import electronicstorage.Repository.Models.ProjectEntity;
import electronicstorage.UI.Models.ProjectModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingProjectsImpl implements MappingProjects {

    public ProjectModel MappingProjectEntityToModel(ProjectEntity dbProject){
        ProjectModel project = new ProjectModel();
        project.id = dbProject.id;
        project.name = dbProject.name;
        project.company = dbProject.company;
        project.timeStamp = dbProject.timeStamp;
        return project;
    }

    public List<ProjectModel> MappingProjectEntityToModel(List<ProjectEntity> dbProjects){
        List<ProjectModel> projects = new ArrayList<ProjectModel>();
        for(ProjectEntity dbProject : dbProjects){
            projects.add(MappingProjectEntityToModel(dbProject));
        }
        return projects;
    }

    public ProjectEntity MappingProjectModelToEntity(ProjectModel project){
        ProjectEntity dbProject = new ProjectEntity();
        dbProject.id = project.id;
        dbProject.name = project.name;
        dbProject.company = project.company;
        dbProject.timeStamp = dbProject.timeStamp;
        return dbProject;
    }

    public List<ProjectEntity> MappingProjectModelToEntity(List<ProjectModel> projects){
        List<ProjectEntity> dbProjects = new ArrayList<ProjectEntity>();
        for(ProjectModel project : projects){
            dbProjects.add(MappingProjectModelToEntity(project));
        }
        return dbProjects;
    }



}
