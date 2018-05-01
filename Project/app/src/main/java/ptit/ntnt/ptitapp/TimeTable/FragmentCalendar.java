package ptit.ntnt.ptitapp.TimeTable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import ptit.ntnt.ptitapp.CalendarView;
import ptit.ntnt.ptitapp.R;

public class FragmentCalendar  extends android.support.v4.app.Fragment {
    FragmentDayDetail fragmentDayDetail;
    Date currentTime = Calendar.getInstance().getTime();
    String Date;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        android.widget.CalendarView calendarView =   (android.widget.CalendarView) view.findViewById(R.id.calender_Day);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();


        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                String Date = String.valueOf(dayOfMonth) +"/"+ String.valueOf(month) + "/"+ String.valueOf(year);
                Toast.makeText(getActivity(), Date, Toast.LENGTH_SHORT).show();
               fragmentDayDetail = new FragmentDayDetail();
               fragmentDayDetail = (FragmentDayDetail) getFragmentManager().findFragmentById(R.id.SchuduleDetail);
               fragmentDayDetail.txtDate.setText(Date);


            }
        });

        return view;
    }
}