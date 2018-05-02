package ptit.ntnt.ptitapp.Firebase;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.PTITClass;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.User;
import ptit.ntnt.ptitapp.Models.UserGroup;
import ptit.ntnt.ptitapp.R;

/**
 * Created by datshiro on 26/03/2018.
 */

public class FirebaseHelper {
    public static DatabaseReference mData;

    public FirebaseHelper(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    public Student getStudent (String studentID){
        final Student[] students = {new Student()};
        mData.child(DBConst.TB_STUDENT.TB_NAME).child(studentID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                students[0] = dataSnapshot.getValue(Student.class);         // Do cai ham nay doi` phai? tra ve` 1 phan tu cua mang
                Log.d("Student", students[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (students[0] != null){
            return students[0];
        }else{
            return null ;
        }
    }

    public Lecturer getLecturer(String lecturerID){
        final Lecturer[] lecturers = {new Lecturer()};
        mData.child(DBConst.TB_LECTURER.TB_NAME).child(lecturerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lecturers[0] = dataSnapshot.getValue(Lecturer.class);         // Do cai ham nay doi` phai? tra ve` 1 phan tu cua mang
                Log.d("Lecturer", lecturers[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (lecturers[0] != null){
            return lecturers[0];
        }else{
            return null ;
        }
    }

    public Subject getSubject(String subjectID){
        final Subject[] subjects = {new Subject()};
        mData.child(DBConst.TB_SUBJECT.TB_NAME).child(subjectID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subjects[0] = dataSnapshot.getValue(Subject.class);         // Do cai ham nay doi` phai? tra ve` 1 phan tu cua mang
                Log.d("Subject", subjects[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (subjects[0] != null){
            return subjects[0];
        }else{
            return null ;
        }
    }
    public void getListSubjects(final ArrayAdapter adapter){
        /***
         * must Pass a ArrayAdapter<Subject> as a parameter
         */
        mData.child(DBConst.TB_SUBJECT.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Subject subject = ds.getValue(Subject.class);

                    Log.d("Get Data From Firebase", "Get " + subject);
                    adapter.add(subject);
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Course getCourse(final String courseID){
        final Course[] courses = {new Course()};
        mData.child(DBConst.TB_COURSE.TB_NAME).child(courseID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courses[0] = dataSnapshot.getValue(Course.class);
                Log.d("Course", courses[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (courses[0] != null){
            return courses[0];
        }else{
            return null;
        }
    }

    public Schedule getSchedule(final String scheduleID){
        final Schedule[] schedules = {new Schedule()};
        mData.child(DBConst.TB_SCHEDULE.TB_NAME).child(scheduleID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                schedules[0] = dataSnapshot.getValue(Schedule.class);
                Log.d("Schedule", schedules[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (schedules[0] != null){
            return schedules[0];
        }else{
            return null;
        }
    }

    public PTITClass getPTITClass(final String ptitClassID){
        final PTITClass[] ptitClasses = {new PTITClass()};
        mData.child(DBConst.TB_PTIT_CLASS.TB_NAME).child(ptitClassID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ptitClasses[0] = dataSnapshot.getValue(PTITClass.class);
                Log.d("PTITClass", ptitClasses[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (ptitClasses[0] != null){
            return ptitClasses[0];
        }else{
            return null;
        }
    }

    public News getNews(final String newsID){
        final News[] news = {new News()};
        mData.child(DBConst.TB_NEWS.TB_NAME).child(newsID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                news[0] = dataSnapshot.getValue(News.class);
                Log.d("News", news[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (news[0] != null){
            return news[0];
        }else{
            return null;
        }
    }


    public void getListNews(final NewsAdapter adapter){
        mData.child(DBConst.TB_NEWS.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    News news = ds.getValue(News.class);

//                    Log.d("Get Data From Firebase", "Get " + news);
                    adapter.add(news);
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public UserGroup getUserGroup(final String userGroupName){
        final UserGroup[] userGroups = {new UserGroup()};
        mData.child(DBConst.TB_USER_GROUP.TB_NAME).child(userGroupName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userGroups[0] = dataSnapshot.getValue(UserGroup.class);
                Log.d("UserGroup", userGroups[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (userGroups[0] != null){
            return userGroups[0];
        }else{
            return null;
        }
    }

}
