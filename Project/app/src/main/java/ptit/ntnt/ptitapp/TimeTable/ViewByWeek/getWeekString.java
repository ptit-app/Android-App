package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class getWeekString extends Application{
    private static String NGAY_KEY;
    public static void addDay(String ngay)
    {
        if (NGAY_KEY == null) NGAY_KEY = "";
        NGAY_KEY = ngay;
        Log.d("Ngay test",NGAY_KEY);
    }
    public static String getNgayKey(){
        //if(NGAY_KEY == null) NGAY_KEY="";
        return NGAY_KEY;
    }
    public static void DeleteDay(){
        NGAY_KEY = "";
    }
}
