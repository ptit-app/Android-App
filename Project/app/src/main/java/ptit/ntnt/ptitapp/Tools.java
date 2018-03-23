package ptit.ntnt.ptitapp;

import android.util.Log;

import java.text.ParseException;
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
}
