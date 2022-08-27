package electronicstorage.UI;

import electronicstorage.BussinesLogic.IElementService;
import electronicstorage.Models.ElementEntity;
import electronicstorage.Models.ElementModel;
import electronicstorage.Models.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ElementController {


    private final IElementService _elementService;
    static List<ElementEntity> _allElements = new ArrayList<ElementEntity>();
    static int currentPage = 1;

    @Autowired
    public ElementController(IElementService elementService){
        _elementService = elementService;
    }

    @GetMapping("/elements")
    public String listElements(Model model){
        _allElements = _elementService.GetAllElements();
        return "redirect:/elements/1";
    }

    //Sada iz templatea saljen putanju s filterom i trenutnom stranicom i ta metoda uzima iz modela i obrađuje
    //Samo kada se doda novi ide na prvu stranicu al to moze pobrkat filter pa vidit mozda da i ona salje trenutnu str
    //I da doda i u allElements u modelu tako da zapravo ne mijenja nista, iako onda filtar vise ne vridi
    //Jer se onda doda na kraj pa se ponistava filter, s vrimenon mozda taj dio popravit s globalnin varijablama
    //Tako bi se mozda moglo napravit da postoje stalni filteri tj sortiranja ali onda triba bit i reset botun
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
    public String addNewElement(ElementModel element){
        _elementService.AddNewElement(element);

        return "redirect:/elements";
    }

    @RequestMapping("/elements/getOne")
    @ResponseBody
    public ElementEntity getOneElement(long id){
        return _elementService.GetOneElement(id);
    }

    @PostMapping("/elements/update/{id}")
    public String updateElement(ElementEntity element,
                                @PathVariable("id") long id){
        element.setElementId(id);
        _elementService.UpdateElement(element);
        return "redirect:/elements";
    }
}
