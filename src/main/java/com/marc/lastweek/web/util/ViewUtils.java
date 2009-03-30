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
        normalizedtext = normalizedtext.replace ('�','a');
        normalizedtext = normalizedtext.replace ('�','e');
        normalizedtext = normalizedtext.replace ('�','i');
        normalizedtext = normalizedtext.replace ('�','o');
        normalizedtext = normalizedtext.replace ('�','u');
        normalizedtext = normalizedtext.replace ('�','n');
        normalizedtext = normalizedtext.replace ('�','A');
        normalizedtext = normalizedtext.replace ('�','E');
        normalizedtext = normalizedtext.replace ('�','I');
        normalizedtext = normalizedtext.replace ('�','O');
        normalizedtext = normalizedtext.replace ('�','U'); 
        normalizedtext = normalizedtext.replace ('�','N');
        return normalizedtext;
    }
}

