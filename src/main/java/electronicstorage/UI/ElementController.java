package electronicstorage.UI;

import electronicstorage.BussinesLogic.ElementService;
import electronicstorage.UI.Models.ElementModel;
import electronicstorage.UI.Models.NewElementModel;
import electronicstorage.UI.Models.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ElementController {
    private final ElementService _elementService;
    static List<ElementModel> _allElements = new ArrayList<ElementModel>();
    static int currentPage = 1;

    @Autowired
    public ElementController(ElementService elementService){
        _elementService = elementService;
    }

    @GetMapping("/")
    public String main(){
        return "Index";
    }
    @GetMapping("/elements")
    public String listElements(Model model){
        _allElements = _elementService.GetAllElements();
        return "redirect:/elements/1";
    }
    @GetMapping("/elements/{page}")
    public String listElements(@PathVariable("page") Integer page,
                               Model model){
        PageModel pageModel = _elementService.GetElementsPage(page, _allElements);
        model.addAttribute("noNext", pageModel.noNext);
        model.addAttribute("noPrevious", pageModel.noPrevious);
        model.addAttribute("Elements", pageModel.pageElements);
        model.addAttribute("previousPage", page-1);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("currentPage", page);
        return "elements";
    }
    @GetMapping("/elements/filter")
    public String filterElements(String keyword){
        _allElements = _elementService.FilterElements(keyword, _elementService.GetAllElements());
        return("redirect:/elements/1");
    }
    @PostMapping("/elements/newElement")
    public String addNewElement(NewElementModel element){
        _elementService.AddNewElement(element);

        return "redirect:/elements";
    }
    @RequestMapping("/elements/getOne")
    @ResponseBody
    public ElementModel getOneElement(long id){
        return _elementService.GetOneElement(id);
    }
    @PostMapping("/elements/update/{id}")
    public String updateElement(ElementModel element,
                                @PathVariable("id") long id){
        element.setElementId(id);
        _elementService.UpdateElement(element);
        return "redirect:/elements";
    }
}
