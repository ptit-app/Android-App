package ptit.ntnt.ptitapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Exam;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Rate;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Subject;

import static ptit.ntnt.ptitapp.MyApplication.mapCourse;
import static ptit.ntnt.ptitapp.MyApplication.mapCourseIDToSubject;
import static ptit.ntnt.ptitapp.MyApplication.mapExam;

/**
 * Created by datshiro on 22/03/2018.
 */

public class Tools {

    public final static String DateToString(Date d){
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    public Long DateStringToLongMillies(String dateString){
        /***
         * Give a string with pattern "dd-MM-yyyy" to return LongMilies type, which can then be converted to Date easily
         */
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateString).getTime();
        } catch (ParseException e) {
            Log.i("Failed to parse date", dateString);
            return null;
        }
    }

    public static ArrayList<Date> ListDate(String stringStartDate, String stringEndDate, int dayOfWeek){
        /***
         * Return the list of date of each week since startDate to endDate
         */
        try {
            ArrayList<Date> listDate = new ArrayList<>();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            Calendar d = Calendar.getInstance();
            Date startDate = formater.parse(stringStartDate);
            startDate = getDateFromDayOfWeek(formater.format(startDate),dayOfWeek);
            Date endDate = formater.parse(stringEndDate);
            c.setTime(startDate);
            d.setTime(endDate);
            for(Calendar cal = c ; cal.before(d) ; cal.add(Calendar.DATE,7)){
                listDate.add(cal.getTime());
            }
            return listDate;
        } catch (ParseException ex) {
            Log.d("Failed Date Parse", ex.getMessage());
            return null;
        }
    }
    public static Date getDateFromDayOfWeek(String stringDate, int dayOfWeek){
        /***
         * Input is the stringDate of Monday, which also mean the first day of the week, and get the date correspond to the input-dayOfWeek
         */
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            Date d = formater.parse(stringDate);
            c.setTime(d);
            int duration = dayOfWeek - c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE,duration);
            return c.getTime();
        } catch (ParseException ex) {
            Log.d("Failed Date Parse", ex.getMessage());
            return null;
        }
    }

    public static void DatShiroWork(){
        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference attendanceNode = mData.child(DBConst.TB_ATTENDANCE.TB_NAME);      // Reference to student node
        final DatabaseReference courseNode = mData.child(DBConst.TB_COURSE.TB_NAME);
        attendanceNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot student: dataSnapshot.getChildren()){     // For each student in TB_ATTENDANCE
                    final String studentID = student.getKey();
                    attendanceNode.child(studentID).child("courseID").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final ArrayList<String> listCourse = (ArrayList<String>) dataSnapshot.getValue();
                            for(final String course : listCourse){                            // For each course in listCourse of that Student
//                                attendanceNode.child(studentID).child(course).setValue("");
                                courseNode.child(course).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Course c = (Course) dataSnapshot.getValue(Course.class);
                                        ArrayList<Date> listStudyDate = ListDate(c.getStartDate(),c.getEndDate(),c.getDayOfWeek()); // For each study date - create schedule
                                        for(Date d : listStudyDate){
                                            Schedule schedule = new Schedule();
                                            schedule.setCourseID(c.getCourseID());
                                            schedule.setTietBD(c.getTietBD());
                                            schedule.setIsTheory(c.getTTH() == 0 ? "LT":"TH");
                                            schedule.setNote("");
                                            schedule.setRoom(c.getRoom());
                                            schedule.setStudyDate(DateToString(d));
                                            schedule.setIsOff("false");

                                            attendanceNode.child(studentID).child(course).child(String.valueOf(d.getTime())).setValue(schedule);
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static HashMap<String,Subject> getCurrentStudyMapSubject(){
        /**
         * Get all the subject that current Student is studying as HashMap
         * @key: courseID
         * @value: Subject
         */
        HashMap<String,Subject> mapSubject = new HashMap<>();
        for(String courseID: mapCourse.keySet()){
            mapSubject.put(courseID,mapSubject.get(courseID));
        }
        return mapSubject;
    }

    public static ArrayList<Subject> getCurrentStudyListSubject(){
        /**
         * Get all the subject that current Student is studying as ArrayList
         *
         */
        Set<Subject> setSubject = new TreeSet<>();
        ArrayList<Subject> listSubject = new ArrayList<>();
        for(String courseID: mapCourse.keySet()){
            Subject subject = mapCourseIDToSubject.get(courseID);
            if (subject != null){
                setSubject.add(subject);
            }
        }
        listSubject.addAll(setSubject);
        return listSubject;
    }

    public static ArrayList<Exam> getCurrentExamOnStudyingCourse(){
        ArrayList<Exam> listExam = new ArrayList<>();
        for(String courseID: mapCourse.keySet()){
            Exam exam = mapExam.get(courseID);
            if(exam != null){
                listExam.add(exam);
            }else{
                Log.d("TAM TEST CAN'T GET", courseID);
            }
        }
        Log.d("TAM TEST", listExam.toString());
        return listExam;
    }

    public static HashMap<String,Exam> getCurrentExamOnStudyingCourseMap(){
        HashMap<String, Exam> mapExam = new HashMap<>();
        for (String courseID: mapCourse.keySet()){
            Subject s = MyApplication.mapCourseIDToSubject.get(mapExam.get(courseID));
            mapExam.put(s.getSubjectID(),mapExam.get(courseID));
        }
        return  mapExam;
    }

    public static ArrayList<Schedule> getSchedulesByDate(String stringDate){
        /**
         * Call getMapCourse first when run program before call this method
         * Remember to check if return list has length > 0
         */
        ArrayList<Schedule> listSchedule = new ArrayList<Schedule>();
        Set<Map.Entry<String, HashMap<String, Schedule>>> setCourse = mapCourse.entrySet();
        for(Map.Entry<String,HashMap<String,Schedule>> course: setCourse){
                try{
                Schedule schedule = course.getValue().get(stringDate);
                if (schedule != null){
                    listSchedule.add(schedule);
                    Log.d("DAT SHIRO WORK", schedule.toString());
                    continue;
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        if (listSchedule.size() <= 0 ){
            Log.d("DAT SHIRO WORK", "No Schedule On This Date ("+stringDate+")" );

        }
        return listSchedule;
    }

    public static void addRate(String studentID, final String lecturerID, final int rateStar){
        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        Rate rate = new Rate();
        rate.setLecturerID(lecturerID);
        rate.setStar(rateStar);
        mData.child("TB_RATE").child(studentID).child(lecturerID).setValue(rate);
        // Update Lecturer

        mData.child(DBConst.TB_LECTURER.TB_NAME).child(lecturerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lecturer lecturer = dataSnapshot.getValue(Lecturer.class);
                int ratingCount = lecturer.getRatingCount();
                int rating = lecturer.getRating();
                rating += rateStar;
                ratingCount++;
                lecturer.setRating(rating);
                lecturer.setRatingCount(ratingCount);
                Log.d("DAT SHIRO WORK","Update new rating lecturer " + lecturer.toString());
                mData.child(DBConst.TB_LECTURER.TB_NAME).child(lecturerID).setValue(lecturer);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
