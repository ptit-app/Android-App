package ptit.ntnt.ptitapp.TimeTable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import ptit.ntnt.ptitapp.CalendarView;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.Tools;

public class FragmentCalendar  extends android.support.v4.app.Fragment {
    FragmentDayDetail fragmentDayDetail;
    Date currentTime = Calendar.getInstance().getTime();
    String dayOFMONTH,MONTH,YEAR;
    static String tenMH;
    DatabaseReference mData, mData2;
    ArrayList<SubjectSchedule> arrSubjectSchedules;
    ArrayList<Schedule> arrSchedules;
    SubjectScheduleAdapter subjectScheduleAdapter;
    TextView txtDate;
    String buoi;
    View view;
    android.widget.CalendarView calendarView;
    String UserID;
    private String pattern = "dd/MM/yyyy";
    String dateInString = new SimpleDateFormat(pattern).format(new Date());
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        setControl();
        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {

                if(dayOfMonth<10)
                    dayOFMONTH= "0" + String.valueOf(dayOfMonth);
                else dayOFMONTH = String.valueOf(dayOfMonth);
                if(month<10)
                    MONTH = "0" + String.valueOf(month+1);
                else
                    MONTH = String.valueOf(month+1);
                String Date = dayOFMONTH +"/"+ MONTH + "/"+ String.valueOf(year);
                txtDate.setText(Date);
                Toast.makeText(getActivity(), txtDate.getText().toString()+"abc", Toast.LENGTH_SHORT).show();
                arrSubjectSchedules.clear();
                subjectScheduleAdapter.notifyDataSetChanged();
                getSchedule(Date);
            }
        });
        return view;
    }
    private void setControl(){
        calendarView =   (android.widget.CalendarView) view.findViewById(R.id.calender_Day);
        txtDate = (TextView) view.findViewById(R.id.showDate);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        arrSubjectSchedules = new ArrayList<>();
        txtDate.setText(dateInString.toString());
        ListView lvSubjectSchedule = (ListView) view.findViewById(R.id.lvShedule);
        subjectScheduleAdapter = new SubjectScheduleAdapter(getActivity(), R.layout.timetable_view_subject_row, arrSubjectSchedules);
        lvSubjectSchedule.setAdapter(subjectScheduleAdapter);
    }
    private void getSchedule(String Date) {
        int i;
        arrSchedules = Tools.getSchedulesByDate(Date);
//        Collections.sort(arrSchedules);
        Toast.makeText(getActivity(), arrSchedules.toString(), Toast.LENGTH_SHORT).show();
        for (i = 0; i < arrSchedules.size(); i++) {
            if (arrSchedules.isEmpty()) {
                Toast.makeText(getActivity(), "NULL", Toast.LENGTH_SHORT).show();
            } else {
                MyApplication.mapCourse.get(arrSchedules.get(i).getCourseID());

                mData = FirebaseDatabase.getInstance().getReference("TB_SUBJECT");

                final int finalI = i;
                mData.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if (arrSchedules.get(finalI).getCourseID().split("_")[0].toString().equals(dataSnapshot.getKey().toString())) {

                            if (arrSchedules.get(finalI).getTietBD() == 1) {
                                Toast.makeText(getActivity(), "Tiet Bat dau:" + arrSchedules.get(finalI).getTietBD(), Toast.LENGTH_SHORT).show();
                                buoi = "Sáng";
                            } else buoi = "Chiều";
                            CustomSubject customSubject = dataSnapshot.getValue(CustomSubject.class);
                            tenMH = customSubject.getSubjectName();
                            SubjectSchedule subjectSchedule = new SubjectSchedule(arrSchedules.get(finalI).getCourseID().split("_")[0], tenMH, arrSchedules.get(finalI).getRoom().toString(), buoi, arrSchedules.get(finalI).getCourseID());
                            arrSubjectSchedules.add(subjectSchedule);

                            subjectScheduleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }
}