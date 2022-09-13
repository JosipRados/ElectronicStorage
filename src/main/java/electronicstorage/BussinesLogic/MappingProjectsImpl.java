package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.ProjectElementDataDTO;
import electronicstorage.BussinesLogic.Models.ProjectDTO;
import electronicstorage.UI.Models.NewProjectModel;
import electronicstorage.UI.Models.ProjectElementDataModel;
import electronicstorage.UI.Models.ProjectDetailsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingProjectsImpl implements MappingProjects {

    @Override
    public ProjectDetailsModel MappingProjectEntityToModel(ProjectDTO dbProject){
        ProjectDetailsModel project = new ProjectDetailsModel();
        project.id = dbProject.id;
        project.name = dbProject.name;
        project.company = dbProject.company;
        project.timeStamp = dbProject.timeStamp;
        return project;
    }

    @Override
    public List<ProjectDetailsModel> MappingProjectEntityToModel(List<ProjectDTO> dbProjects){
        List<ProjectDetailsModel> projects = new ArrayList<ProjectDetailsModel>();
        for(ProjectDTO dbProject : dbProjects){
            projects.add(MappingProjectEntityToModel(dbProject));
        }
        return projects;
    }

    @Override
    public ProjectDTO MappingProjectModelToEntity(ProjectDetailsModel project){
        ProjectDTO dbProject = new ProjectDTO();
        dbProject.id = project.id;
        dbProject.name = project.name;
        dbProject.company = project.company;
        dbProject.timeStamp = dbProject.timeStamp;
        return dbProject;
    }

    @Override
    public List<ProjectDTO> MappingProjectModelToEntity(List<ProjectDetailsModel> projects){
        List<ProjectDTO> dbProjects = new ArrayList<ProjectDTO>();
        for(ProjectDetailsModel project : projects){
            dbProjects.add(MappingProjectModelToEntity(project));
        }
        return dbProjects;
    }

    @Override
    public ProjectElementDataModel MappingProjectElementDataDTOToModel(ProjectElementDataDTO newElementDTO){
        ProjectElementDataModel newElement = new ProjectElementDataModel();
        newElement.setElementCode(newElementDTO.getElementCode());
        newElement.setProjectId(newElementDTO.getProjectId());
        newElement.setElementQuantity(newElementDTO.getElementQuantity());
        return newElement;
    }

    @Override
    public ProjectElementDataDTO MappingProjectElementDataModelToDTO(ProjectElementDataModel newElement){
        ProjectElementDataDTO newElementDTO = new ProjectElementDataDTO();
        newElementDTO.setElementCode(newElement.getElementCode());
        newElementDTO.setProjectId(newElement.getProjectId());
        newElementDTO.setElementQuantity(newElement.getElementQuantity());
        return newElementDTO;
    }

    @Override
    public ProjectDTO MappingNewProjectModelToProjectDTO(NewProjectModel newProject){
        ProjectDTO newProjectDTO = new ProjectDTO();
        newProjectDTO.name = newProject.name;
        newProjectDTO.company = newProject.company;
        return newProjectDTO;
    }


}
