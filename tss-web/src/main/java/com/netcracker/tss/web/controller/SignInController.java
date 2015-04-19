package com.netcracker.tss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyrylo Berehovyi on 19/04/2015.
 */

@Controller
public class SignInController {

    @RequestMapping("/signin")
    public ModelAndView getSignInPage() {
        return new ModelAndView("sign-in");
    }

    @RequestMapping("/403")
    public String getForbiddenPage() {
        return "403";
    }
}
