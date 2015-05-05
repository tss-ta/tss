package com.netcracker.router.util;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

/**
 * @author Kyrylo Berehovyi
 */

public class LoggerUtil {

    public static String getStackTrace(Exception e) {
        CharArrayWriter cw = new CharArrayWriter();
        PrintWriter w = new PrintWriter(cw);
        e.printStackTrace(w);
        w.close();
        return cw.toString();
    }
}
