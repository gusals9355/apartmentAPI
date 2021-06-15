package com.koreait.spring2;

import com.koreait.spring2.model.LocationCodeEntity;
import com.koreait.spring2.model.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String view(){

        return "";
    }

    @PostMapping("/result")
    public String view(Model model, SearchDTO param){
        mainService.saveData(param);
         return "redirect:/result";
    }
}
