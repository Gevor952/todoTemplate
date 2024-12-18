package am.itspace.todotemplate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final SimpleDateFormat JS = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat WEB = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

    static public Date jsFromJava(String date) {
        try {
            return JS.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    static public String javaToWeb(Date date) {
        return WEB.format(date);
    }

}
