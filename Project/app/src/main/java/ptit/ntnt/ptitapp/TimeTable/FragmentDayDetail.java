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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ptit.ntnt.ptitapp.R;

public class FragmentDayDetail extends android.support.v4.app.Fragment{
    DatabaseReference mData,mData2;
    ArrayList<SubjectSchedule> arrSubjectSchedules;
    SubjectScheduleAdapter subjectScheduleAdapter;
    TextView txtDate;
    String UserID;

    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    String currentDate= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+'/'+String.valueOf(calendar.get(Calendar.MONTH))+'/'+String.valueOf(calendar.get(Calendar.YEAR));
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_view_detail,container,false);
        txtDate =(TextView) view.findViewById(R.id.showDate);
        txtDate.setText(currentDate.toString());
        ListView lvSubjectSchedule = (ListView) view.findViewById(R.id.lvSubjectSchedule);
        arrSubjectSchedules= new ArrayList<>();
        subjectScheduleAdapter = new SubjectScheduleAdapter(getActivity(), R.layout.timetable_view_subject_row,arrSubjectSchedules);
        lvSubjectSchedule.setAdapter(subjectScheduleAdapter);
        getData();
        return view;
    }
    private void getData(){



                    mData  = FirebaseDatabase.getInstance().getReference("TB_COURSE");
                    mData2 = FirebaseDatabase.getInstance().getReference("TB_ATTENDANCE").child("N14DCAT022").child("INT1342_D14CQAT01-N_01_00_61");

                    mData2.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            if(dataSnapshot.getKey().equals("1524157200000")){

                                mData.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        SubjectSchedule subjectSchedule = dataSnapshot.getValue(SubjectSchedule.class);
                                        arrSubjectSchedules.add(subjectSchedule);

                                        subjectScheduleAdapter.notifyDataSetChanged();
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


    }}

