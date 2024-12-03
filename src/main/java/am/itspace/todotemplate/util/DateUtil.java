package am.itspace.todotemplate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat JS = new SimpleDateFormat("dd/MM/yyyy");

    public Date jsFromJava(String date) {
        try {
            return JS.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
