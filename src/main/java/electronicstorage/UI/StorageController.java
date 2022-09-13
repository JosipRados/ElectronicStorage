package electronicstorage.UI;

import electronicstorage.BussinesLogic.StorageService;
import electronicstorage.UI.Models.NewStorageModel;
import electronicstorage.UI.Models.RepositoryResponseModel;
import electronicstorage.UI.Models.StorageModel;
import electronicstorage.UI.Models.StoragePageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StorageController {
    private final StorageService _storageService;

    static List<StorageModel> _allStorage = new ArrayList<StorageModel>();
    static int currentPage = 1;
    static String errorMessage = "";
    static boolean isSuccess = true;

    @GetMapping("/storage")
    public String listStorage(Model model){
        _allStorage = _storageService.GetAllStorage();
        return "redirect:/storage/1";
    }

    @GetMapping("/storage/{page}")
    public String listElements(@PathVariable("page") Integer page,
                               Model model){
        StoragePageModel pageModel = _storageService.GetStoragePage(page, _allStorage);
        String tempErrorMessage = "";
        if(!isSuccess){
            tempErrorMessage = errorMessage;
            errorMessage = "";
            isSuccess = true;
        }
        model.addAttribute("noNext", pageModel.noNext);
        model.addAttribute("noPrevious", pageModel.noPrevious);
        model.addAttribute("Storage", pageModel.pageElements);
        model.addAttribute("previousPage", page-1);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("currentPage", page);
        model.addAttribute("errorMessage", tempErrorMessage);
        return "Storage";
    }

    @PostMapping("storage/newStorage")
    public String addNewStorage(NewStorageModel newStorage){
        RepositoryResponseModel response = _storageService.AddNewStorage(newStorage);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/storage";
    }

    @GetMapping("/storage/filter")
    public String filterElements(String keyword){
        _allStorage = _storageService.FilterStorage(keyword, _storageService.GetAllStorage());
        return("redirect:/storage/1");
    }

    @PostMapping("/storage/update/{id}")
    public String UpdateStorage(@PathVariable ("id") long id,
                                int quantity){
        RepositoryResponseModel response = _storageService.UpdateStorage(id, quantity);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/storage";
    }

    @PostMapping("storage/delete/{id}")
    public String DeleteStorage(@PathVariable ("id") long id){
        RepositoryResponseModel response = _storageService.DeleteStorage(id);
        isSuccess = response.isSucces();
        errorMessage = response.getErrorMessage();
        return "redirect:/storage";
    }
}
