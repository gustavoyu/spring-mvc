package com.sagawalabs.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustavo on 02/01/2018.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index(){
        return "index";
    }
}
