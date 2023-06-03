package ir.expensetracker.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LogManager.getLogger(DateUtil.class);
    public static Date toDate(String date){
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error(e,e);
            return null;
        }
    }

    public static String fromDate(Date date){
        return sdf.format(date);
    }

    public static Date getDate(String input){
        Date date=null;
        if(input!=null && !input.equals("")){
            date=toDate(input);
        }
        if(date==null){
            date=new Date();
        }
        return date;
    }
}
