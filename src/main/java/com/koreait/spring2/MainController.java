package com.koreait.spring2;

import com.koreait.spring2.model.ApartmentDTO;
import com.koreait.spring2.model.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("list",mainService.selLocationCodeList());
        return "main";
    }

    @GetMapping("/result")
    public String view(Model model, SearchDTO param){
        System.out.println(param);
        List<ApartmentDTO> list = mainService.view(param);
        for(ApartmentDTO a : list){
            System.out.println(a);
        }
        model.addAttribute("list",list);
        return "view";
    }

    @PostMapping("/result")
    public String view(SearchDTO param, RedirectAttributes redirectAttributes){
        mainService.saveData(param);

        redirectAttributes.addFlashAttribute("param",param);
        return "redirect:/result";
    }
}
