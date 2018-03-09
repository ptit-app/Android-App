package ptit.ntnt.ptitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarView extends AppCompatActivity {
    TextView edtDate;
    TextView edtMonth;
    Date currentDate;
    CompactCalendarView compactCalendarView;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE-d-MMMM-yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        setControl();
        edtDate.setText(dateFormat1.format(currentDate));
        edtMonth.setText(dateFormat.format(currentDate));
        calendarEvent();
    }
    private void calendarEvent(){
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                edtDate.setText(dateFormat1.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                edtMonth.setText(dateFormat.format(firstDayOfNewMonth));
            }
        });
    }
    private void setControl(){
        edtDate = (TextView) findViewById(R.id.txtVieưDate);
        edtMonth = (TextView) findViewById(R.id.txtViewMonth);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        currentDate= Calendar.getInstance().getTime();


    }
}
