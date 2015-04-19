package com.netcracker.tss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyrylo Berehovyi on 19/04/2015.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "Taxi Service System");
        return modelAndView;
    }
}
