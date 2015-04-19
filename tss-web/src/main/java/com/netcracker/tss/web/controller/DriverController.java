package com.netcracker.tss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */

@Controller
public class DriverController {

    @RequestMapping("/driver")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("driver/home-driver");
        modelAndView.addObject("title", "Driver Home Page");
        return modelAndView;
    }
}
