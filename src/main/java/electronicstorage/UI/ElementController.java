package electronicstorage.UI;

import electronicstorage.BussinesLogic.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElementController {


    private final IElementService _elementService;

    @Autowired
    public ElementController(IElementService elementService){
        _elementService = elementService;
    }

    @GetMapping("/elements")
    public String listElements(Model model){
        model.addAttribute("Elements", _elementService.GetAllElements());
        return "elements";
    }
}
