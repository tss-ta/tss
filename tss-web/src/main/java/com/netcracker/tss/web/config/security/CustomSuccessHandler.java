package com.netcracker.tss.web.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")){
            response.sendRedirect("/admin");
            return;
        } else if (roles.contains("DRIVER")) {
            response.sendRedirect("/driver");
            return;
        } else if (roles.contains("CUSTOMER")) {
            response.sendRedirect("/customer");
            return;
        }
    }
}
