package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;

import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.TimeTable.CustomSubject;
import ptit.ntnt.ptitapp.TimeTable.FragmentViewByWeek;
import ptit.ntnt.ptitapp.TimeTable.SubjectSchedule;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.TimeTable.SubjectScheduleAdapter;
import ptit.ntnt.ptitapp.Tools;

//import static ptit.ntnt.ptitapp.CustomAdapter.fragmentWeekAdapter.listSchedule;


public class MonFragment extends Fragment{

    public String studentID;
    ArrayList<SubjectSchedule> subjectSchedules;
    ArrayList<Schedule> schedules;
    DatabaseReference mData;
    DatabaseReference mData2;
    ListView listView;
    SubjectScheduleAdapter subjectScheduleAdapter;
    TextView txtDate;
    String buoi;
    View view;
    public static String[] ngay = new String[7];
    private String pattern = "dd/MM/yyyy";
    Date currentTime = Calendar.getInstance().getTime();
    String dayofMONTH, MONTH, YEAR;
    String dateInString = new SimpleDateFormat(pattern).format(new Date());
    static String tenMH;
    public MonFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        view = inflater.inflate(R.layout.layout_listview_byweek, container, false);
        //dR = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) view.findViewById(R.id.listMon);
        txtDate = (TextView) view.findViewById(R.id.txtShowDate);
        subjectSchedules = new ArrayList<>();
        //txtDate.setText("03/05/2018");
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            String t = (String)bundle.getString("NGAY_KEY");
            String s1 = t.split(" ")[2];
            String s2 = s1.split("-")[0];
            txtDate.setText(s2);
            getSchedule(s2);
        }
        //subjectScheduleAdapter.notifyDataSetChanged();
//        getSchedule("22/01/2018");
        //data = new ArrayList<>();;
        subjectScheduleAdapter = new SubjectScheduleAdapter(getActivity(), R.layout.timetable_view_subject_row, subjectSchedules);
        listView.setAdapter(subjectScheduleAdapter);
        return view;
    }
    public void getDayofWeek(){
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK)+2;
        now.add(Calendar.DAY_OF_MONTH, delta);
        for(int i = 0; i<7; i++)
        {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d("Test", Arrays.toString(days));
        ngay = days;
    }
    private void getSchedule(String Date) {
        int i;
        schedules = Tools.getSchedulesByDate(Date);
        Collections.sort(schedules);
        for (i = 0; i < schedules.size(); i++) {
            if (schedules.isEmpty()) {
                Toast.makeText(getActivity(), "NULL", Toast.LENGTH_SHORT).show();
            } else {
                MyApplication.mapCourse.get(schedules.get(i).getCourseID());

                mData = FirebaseDatabase.getInstance().getReference("TB_SUBJECT");

                final int finalI = i;
                mData.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if (schedules.get(finalI).getCourseID().split("_")[0].toString().equals(dataSnapshot.getKey().toString())) {

                            if (schedules.get(finalI).getTietBD() == 1) {
                                //Toast.makeText(getActivity(), "Tiet Bat dau:" + arrSchedules.get(finalI).getTietBD(), Toast.LENGTH_SHORT).show();
                                buoi = "Sáng";
                            } else buoi = "Chiều";
                            Subject subject = dataSnapshot.getValue(Subject.class);
                            tenMH = subject.getSubjectName();
                            SubjectSchedule subjectSchedule = new SubjectSchedule(schedules.get(finalI).getCourseID().split("_")[0], tenMH, schedules.get(finalI).getRoom().toString(), buoi, schedules.get(finalI).getCourseID(), schedules.get(finalI).getStudyDate());
                            subjectSchedules.add(subjectSchedule);

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
