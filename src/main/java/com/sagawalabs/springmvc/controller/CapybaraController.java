package com.sagawalabs.springmvc.controller;

import com.sagawalabs.springmvc.domain.Capybara;
import com.sagawalabs.springmvc.services.CapybaraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gustavo on 02/01/2018.
 */
@Controller
public class CapybaraController {

    @Autowired
    private CapybaraService capybaraService;

    @RequestMapping("/capybaras")
    public String listCapybaras(Model model){
        model.addAttribute("capybaras", capybaraService.listCapybaras());
        return "capybara/list";
    }

    @RequestMapping("/capybara/{id}")
    public String getCapybaraById(Model model, @PathVariable Integer id){
        model.addAttribute("capybara", capybaraService.getCapybaraById(id));
        return "capybara/detail";
    }

    @RequestMapping("/capybara/new")
    public String createCapybara(Model model){
        model.addAttribute("capybara", new Capybara());
        return "capybara/create";
    }

    @RequestMapping(value = "/capybara", method = RequestMethod.POST)
    public String saveOrUpdateCapybara(Capybara capybara){
        Capybara savedCapybara = capybaraService.saveOrUpdateCapybara(capybara);
        return "redirect:/capybara/" + capybara.getId();
    }

    @RequestMapping("/capybara/delete/{id}")
    public String deleteCapybara(@PathVariable Integer id){
        capybaraService.deleteCapybara(id);
        return "redirect:/capybaras";
    }
}
