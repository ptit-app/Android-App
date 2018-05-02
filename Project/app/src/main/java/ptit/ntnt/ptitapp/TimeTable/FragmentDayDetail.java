package ptit.ntnt.ptitapp.TimeTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.Tools;

public class FragmentDayDetail extends android.support.v4.app.Fragment {
    static String tenMH;
    DatabaseReference mData, mData2;
    ArrayList<SubjectSchedule> arrSubjectSchedules;
    ArrayList<Schedule> arrSchedules;
    SubjectScheduleAdapter subjectScheduleAdapter;
    TextView txtDate;
    String buoi;
    String UserID;
    private String pattern = "dd/MM/yyyy";
    String dateInString = new SimpleDateFormat(pattern).format(new Date());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_view_detail, container, false);
        txtDate = (TextView) view.findViewById(R.id.showDate);
        txtDate.setText(dateInString.toString());
        ListView lvSubjectSchedule = (ListView) view.findViewById(R.id.lvShedule);
        arrSubjectSchedules = new ArrayList<>();
        subjectScheduleAdapter = new SubjectScheduleAdapter(getActivity(), R.layout.timetable_view_subject_row, arrSubjectSchedules);
        lvSubjectSchedule.setAdapter(subjectScheduleAdapter);
//        Tools.getMapCourse("N14DCAT022");
        Toast.makeText(getActivity(), txtDate.getText().toString()+"abc", Toast.LENGTH_SHORT).show();
        arrSchedules = Tools.getSchedulesByDate(txtDate.getText().toString());
        Toast.makeText(getActivity(), arrSchedules.toString(), Toast.LENGTH_SHORT).show();
        if (arrSchedules.isEmpty()) {
            Toast.makeText(getActivity(), "NULL", Toast.LENGTH_SHORT).show();
        } else {
            MyApplication.mapCourse.get(arrSchedules.get(0).getCourseID());
            Toast.makeText(getActivity(), MyApplication.mapCourse.get(arrSchedules.get(0).getCourseID()).toString(), Toast.LENGTH_SHORT).show();
            if (arrSchedules.get(0).getTietBD() == 5) {
                buoi = "Chiều";
            } else buoi = "Sáng";
            mData = FirebaseDatabase.getInstance().getReference("TB_SUBJECT");
            mData.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (arrSchedules.get(0).getCourseID().split("_")[0].toString().equals(dataSnapshot.getKey().toString())) {
                        CustomSubject customSubject = dataSnapshot.getValue(CustomSubject.class);
                        tenMH = customSubject.getSubjectName();
                        SubjectSchedule subjectSchedule = new SubjectSchedule(arrSchedules.get(0).getCourseID().split("_")[0], tenMH, arrSchedules.get(0).getRoom().toString(), buoi, arrSchedules.get(0).getCourseID());
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
        return view;
    }

}

