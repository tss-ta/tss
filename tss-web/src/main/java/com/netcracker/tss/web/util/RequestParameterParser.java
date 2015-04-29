package com.netcracker.tss.web.util;

import org.hibernate.annotations.SourceType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public class RequestParameterParser {

    public static Integer parseInteger(HttpServletRequest request, String parameterName) {
        ArgumentValidator.validateArgumentOnNull("request", request);
        ArgumentValidator.validateArgumentOnNull("parameterName", parameterName);

        Integer parsedParameter;
        try {
            parsedParameter = Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            parsedParameter = null;
        }
        return parsedParameter;
    }

    public static boolean parseBoolean(HttpServletRequest request, String parameterName) {
        ArgumentValidator.validateArgumentOnNull("request", request);
        ArgumentValidator.validateArgumentOnNull("parameterName", parameterName);

        if(request.getParameter(parameterName) != null) {
            return true;
        }
        return false;
    }
}
