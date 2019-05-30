package org.chengfan.gitlab.shower;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Globle {
    static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date startDateTime = null;
    public static Date endDateTime = null;


    static {
        try {
            startDateTime = format.parse("2018-07-30 00:00:00");
            endDateTime = format.parse("2019-07-30 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
