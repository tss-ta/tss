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

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String DRIVER_ROLE = "DRIVER";
    private static final String CUSTOMER_ROLE = "CUSTOMER";

    private static final String ADMIN_PAGE = "/admin";
    private static final String DRIVER_PAGE = "/driver";
    private static final String CUSTOMER_PAGE = "/customer";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains(ADMIN_ROLE)){
            response.sendRedirect(ADMIN_PAGE);
            return;
        } else if (roles.contains(DRIVER_ROLE)) {
            response.sendRedirect(DRIVER_PAGE);
            return;
        } else if (roles.contains(CUSTOMER_ROLE)) {
            response.sendRedirect(CUSTOMER_PAGE);
            return;
        }
    }
}
