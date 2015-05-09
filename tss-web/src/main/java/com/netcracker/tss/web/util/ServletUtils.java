package com.netcracker.tss.web.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by maks on 09.05.15.
 */
public class ServletUtils {
    public static String getRelativeUrl(
            HttpServletRequest request ) {

        String baseUrl = null;

        if ( ( request.getServerPort() == 80 ) ||
                ( request.getServerPort() == 443 ) )
            baseUrl =
                    request.getScheme() + "://" +
                            request.getServerName() +
                            request.getContextPath();
        else
            baseUrl =
                    request.getScheme() + "://" +
                            request.getServerName() + ":" + request.getServerPort() +
                            request.getContextPath();

        StringBuffer buf = request.getRequestURL();

        if ( request.getQueryString() != null ) {
            buf.append( "?" );
            buf.append( request.getQueryString() );
        }

        return buf.substring( baseUrl.length() );
    }

    /**
     * NOT UNIT TESTED Returns the base url (e.g, <tt>http://myhost:8080/myapp</tt>) suitable for
     * using in a base tag or building reliable urls.
     */
    public static String getBaseUrl( HttpServletRequest request ) {
        if ( ( request.getServerPort() == 80 ) ||
                ( request.getServerPort() == 443 ) )
            return request.getScheme() + "://" +
                    request.getServerName() +
                    request.getContextPath();
        else
            return request.getScheme() + "://" +
                    request.getServerName() + ":" + request.getServerPort() +
                    request.getContextPath();
    }

    /**
     * Returns the file specified by <tt>path</tt> as returned by
     * <tt>ServletContext.getRealPath()</tt>.
     */
    public static File getRealFile(
            HttpServletRequest request,
            String path ) {

        return
                new File( request.getSession().getServletContext().getRealPath( path ) );
    }

}
