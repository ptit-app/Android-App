package ptit.ntnt.ptitapp;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Exam;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.Subject;

import static ptit.ntnt.ptitapp.Tools.DateToString;


/**
 * Created by datshiro on 01/05/2018.
 */

public class MyApplication extends Application{
    public static Student currentStudent;
    static public HashMap<String,HashMap<String,Schedule>> mapCourse = new HashMap<>();
    static public HashMap<String,Subject> mapCourseIDToSubject = new HashMap<>();
    static public HashMap<String,Student> mapAllStudent = new HashMap<>();
    static public ArrayList<Lecturer> listAllLecturer = new ArrayList<>();
    static public HashMap<String,Exam> mapExam = new HashMap<>();
    static public HashMap<String,Lecturer> mapAllLecturer = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            clearData();
            getMapCourse(currentStudent.getStudentID());
            getMapCourseIDToSubject();
            getMapAllStudent();
            getMapAllLecturer();
            getMapExam();
        }catch (Exception ex){
            Toast.makeText(MyApplication.this, "Failed to load data from firebase", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
    private void clearData(){
        mapCourse.clear();
        mapCourseIDToSubject.clear();
        mapAllStudent.clear();
        listAllLecturer.clear();
        mapExam.clear();
        mapAllLecturer.clear();
    }

    public static void getMapCourse(final String studentID){
        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference studentNode = mData.child(DBConst.TB_ATTENDANCE.TB_NAME).child(studentID);      // Reference to student node
        studentNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot course : dataSnapshot.getChildren()){             // for each course get from student
                    final String courseID = course.getKey();                        // pick up courseID
                    mapCourse.put(courseID,new HashMap<String, Schedule>());        // Create a new HashMap with key is that courseID
                    studentNode.child(courseID).orderByKey().addValueEventListener(new ValueEventListener() {       // Reference to that courseID for list of study day
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot s : dataSnapshot.getChildren()){       // for study date (schedule) add into mapCourse
                                if (s.getKey().length() <5 ) continue;
                                Schedule schedule = s.getValue(Schedule.class);
                                schedule.setCourseID(courseID);

                                Date studyDate = new Date();
                                studyDate.setTime(Long.parseLong(s.getKey()));
                                String studyDateKey = DateToString(studyDate);

                                mapCourse.get(courseID).put(studyDateKey,schedule);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void getMapCourseIDToSubject(){
        /**
         * Must be call prior to everything, call in LoginActivity.
         */
        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(DBConst.TB_COURSE.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot course : dataSnapshot.getChildren()) {
                    final String courseID = course.getKey();
                    mData.child(DBConst.TB_COURSE.TB_NAME).child(courseID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String subjectID = dataSnapshot.getValue(Course.class).getSubjectID();
                            mData.child(DBConst.TB_SUBJECT.TB_NAME).child(subjectID).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    mapCourseIDToSubject.put(courseID,dataSnapshot.getValue(Subject.class));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    MyApplication(){
        currentStudent = new Student();
        currentStudent.setStudentID("N14DCAT124");
    }

    public void getMapAllStudent(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(DBConst.TB_STUDENT.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot s : dataSnapshot.getChildren()){
                    String studentID = s.getKey();
                    Student student = s.getValue(Student.class);
                    student.setStudentID(studentID);
                    mapAllStudent.put(studentID,student);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getListAllLecturer(){
        listAllLecturer.clear();
        for (Lecturer lecturer: mapAllLecturer.values()){
            listAllLecturer.add(lecturer);
        }
    }

    public void getMapAllLecturer(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(DBConst.TB_LECTURER.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot l : dataSnapshot.getChildren()){
                    Lecturer lecturer = l.getValue(Lecturer.class);
                    mapAllLecturer.put(l.getKey(),lecturer);
                    getListAllLecturer();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void setCurrentStudent(Student student){
        mapCourse.clear();
        currentStudent = student;
        getMapCourse(currentStudent.getStudentID());
    }

    public void getMapExam(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.child(DBConst.TB_EXAM.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot e : dataSnapshot.getChildren()){
                    Exam exam = e.getValue(Exam.class);
                    exam.setCourseID(e.getKey());
                    mapExam.put(e.getKey(),exam);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
