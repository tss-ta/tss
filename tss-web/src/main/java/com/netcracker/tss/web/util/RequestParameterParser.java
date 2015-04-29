package com.netcracker.tss.web.util;

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

    public static Boolean parseBoolean(HttpServletRequest request, String parameterName) {
        ArgumentValidator.validateArgumentOnNull("request", request);
        ArgumentValidator.validateArgumentOnNull("parameterName", parameterName);

        Boolean parsedParameter;
        try {
            parsedParameter = Boolean.parseBoolean(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            parsedParameter = null;
        }
        return parsedParameter;
    }
}
