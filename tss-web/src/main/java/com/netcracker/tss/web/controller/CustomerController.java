package com.netcracker.tss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */

@Controller
public class CustomerController {

    @RequestMapping("/customer")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("customer/home-customer");
        modelAndView.addObject("title", "Customer Home Page");
        return modelAndView;
    }
}
