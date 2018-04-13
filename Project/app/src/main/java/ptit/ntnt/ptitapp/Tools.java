package ptit.ntnt.ptitapp;

import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by datshiro on 22/03/2018.
 */

public class Tools {
    public String DateToString(Date d){
        return new SimpleDateFormat("dd-MM-yyyy").format(d);
    }

    public Long DateStringToLongMillies(String dateString){
        /***
         * Give a string with pattern "dd-MM-yyyy" to return LongMilies type, which can then be converted to Date easily
         */
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateString).getTime();
        } catch (ParseException e) {
            Log.i("Failed to parse date", dateString);
            return null;
        }
    }

    public static ArrayList<Date> ListDate(String stringStartDate, String stringEndDate){
        /***
         * Return the list of date of each week since startDate to endDate
         */
        try {
            ArrayList<Date> listDate = new ArrayList<>();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            Calendar d = Calendar.getInstance();
            Date startDate = formater.parse(stringStartDate);
            Date endDate = formater.parse(stringEndDate);
            c.setTime(startDate);
            d.setTime(endDate);
            for(Calendar cal = c ; cal.before(d) ; cal.add(Calendar.DATE,7)){
                listDate.add(cal.getTime());
            }
            return listDate;
        } catch (ParseException ex) {
            Log.d("Failed Date Parse", ex.getMessage());
            return null;
        }
    }

    public static Date getDateFromDayOfWeek(String stringDate, int dayOfWeek){
        /***
         * Input is the stringDate of Monday, which also mean the first day of the week, and get the date correspond to the input-dayOfWeek
         */
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            Date d = formater.parse(stringDate);
            c.setTime(d);
            int duration = dayOfWeek - c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE,duration);
            return c.getTime();
        } catch (ParseException ex) {
            Log.d("Failed Date Parse", ex.getMessage());
            return null;
        }
    }
}
