package com.netcracker.tss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyrylo Berehovyi on 19/04/2015.
 */

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("admin/home-admin");
        modelAndView.addObject("title", "Admin Home Page");
        return modelAndView;
    }
}
