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
    
    public static String normalize(String text) {
        String normalizedtext = text;
        normalizedtext = normalizedtext.replace ('‡','a');
        normalizedtext = normalizedtext.replace ('Ž','e');
        normalizedtext = normalizedtext.replace ('’','i');
        normalizedtext = normalizedtext.replace ('œ','o');
        normalizedtext = normalizedtext.replace ('œ','u');
        normalizedtext = normalizedtext.replace ('–','n');
        normalizedtext = normalizedtext.replace ('ç','A');
        normalizedtext = normalizedtext.replace ('ƒ','E');
        normalizedtext = normalizedtext.replace ('ê','I');
        normalizedtext = normalizedtext.replace ('î','O');
        normalizedtext = normalizedtext.replace ('ò','U'); 
        normalizedtext = normalizedtext.replace ('„','N');
        return normalizedtext;
    }
}

