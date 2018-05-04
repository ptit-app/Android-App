package ptit.ntnt.ptitapp;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.Subject;

public class NotiService extends Service{

    DatabaseReference fbData;
    static String mssv = null;
    static ArrayList<String> courses = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fbData = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mssv==null && courses.size()<1 && intent!=null){
            Bundle args = intent.getBundleExtra("NOTIAGRS");
            courses = (ArrayList<String>) args.getSerializable("COURSE");
            mssv = args.getString("ID");
        }
        for(String course : courses){
            fbData.child(DBConst.TB_ATTENDANCE.TB_NAME).child(mssv).child(course).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Schedule curChange = dataSnapshot.getValue(Schedule.class);
                    if(curChange.getIsOff().equalsIgnoreCase("true")){
                        Subject subject = MyApplication.mapCourseIDToSubject.get(curChange.getCourseID());
                        Notification noti = new Notification(getApplicationContext());
                        noti.createNotification("Thông báo nghỉ học!",
                                "Chào "+ mssv,
                                "Xin chia buồn, bạn đã bị nghỉ môn "
                                        + subject.getSubjectName()
                                        + " vào ngày " + curChange.getStudyDate(), LoginActivity.class);
                    }
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
        /*
         * Mục dưới đây để listen các thay đổi để hiển thị thông báo ra màn hình
         * Ex: báo thay đổi khi đổi password cho student N14DCAT022
         */
//        fbData.child(DBConst.TB_STUDENT.TB_NAME).child("N14DCAT022").child("passwd").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Notification noti = new Notification(getApplicationContext());
//                noti.createNotification("Password thay đổi", "Your pass has been changed to "+dataSnapshot.getValue(), LoginActivity.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
