package com.marc.lastweek.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.LocaleUtils;

public class ViewUtils {

    public static String labelizer(Object obj, String datePattern) {
        if (obj!=null) {
            if (obj instanceof Calendar) {
                SimpleDateFormat formatter = new SimpleDateFormat(datePattern, 
                        LocaleUtils.toLocale("es_ES"));
                return formatter.format(((Calendar)obj).getTime());
            } 
            return obj.toString();
        }
        return "";
    }
    
    public static String labelizer(Object obj) {
        return labelizer(obj, "dd/MM/yyyy");
    }
}

