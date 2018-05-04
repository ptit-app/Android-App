package ptit.ntnt.ptitapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.Models.UserGroup;
import ptit.ntnt.ptitapp.MyApplication;

import static ptit.ntnt.ptitapp.MyApplication.currentStudent;
import static ptit.ntnt.ptitapp.MyApplication.mapCourse;

/**
 * Created by datshiro on 19/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context){
        super(context, DBConst.DATABASE_NAME, null, DBConst.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConst.TB_SCHEDULE.CREATE);
        db.execSQL(DBConst.TB_STUDENT.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConst.TB_SCHEDULE.DROP);
        db.execSQL(DBConst.TB_STUDENT.DROP);
        this.onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
        super.onOpen(db);
    }

    public int getTableCount(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        return numRows;
    }

    //add Schedule
    public void addSchedule(Schedule schedule){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DBConst.TB_SCHEDULE.COL_COURSE_ID, schedule.getCourseID());
        values.put(DBConst.TB_SCHEDULE.COL_IS_THEORY, schedule.getIsTheory());
        values.put(DBConst.TB_SCHEDULE.COL_ISOFF, schedule.getIsOff());
        values.put(DBConst.TB_SCHEDULE.COL_NOTE, schedule.getNote());
        values.put(DBConst.TB_SCHEDULE.COL_ROOM, schedule.getRoom());
        values.put(DBConst.TB_SCHEDULE.COL_STUDY_DATE, schedule.getStudyDate());
        values.put(DBConst.TB_SCHEDULE.COL_TIET_BD, schedule.getTietBD());
        values.put(DBConst.TB_SCHEDULE.COL_STUDY_DATE, schedule.getStudyDate());

        // 3. insert
        db.insert(DBConst.TB_SCHEDULE.TB_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        Log.i("Number rows of table:", getTableCount(DBConst.TB_SCHEDULE.TB_NAME) + "");
    }

    public void saveScheduleToSQLite(){
        if (mapCourse != null){
            for(HashMap<String,Schedule> courseMap : mapCourse.values()){
                if(!courseMap.isEmpty()){
                    for(Schedule schedule : courseMap.values()){
                        addSchedule(schedule);
                    }
                }
            }
        }
    }

    public int updateNote(Schedule schedule, String note){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DBConst.TB_SCHEDULE.COL_NOTE,note);
//        values.put(DBConst.TB_SCHEDULE.COL_STUDY_DATE, schedule.getStudyDate());
//        values.put(DBConst.TB_SCHEDULE.COL_COURSE_ID, schedule.getCourseID());

        // 3. updating row
        int i = db.update(DBConst.TB_SCHEDULE.TB_NAME, //table
                values, // column/value
                DBConst.TB_SCHEDULE.COL_STUDY_DATE+" = ? and " + DBConst.TB_SCHEDULE.COL_COURSE_ID+" = ?", // selections
                new String[] { schedule.getStudyDate(), schedule.getCourseID() }); //selection args

        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Long longTime = formater.parse(schedule.getStudyDate()).getTime();
            mData.child(currentStudent.getStudentID()).child(schedule.getCourseID()).child(longTime.toString()).child("note").setValue(note);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 4. close
        db.close();

        Log.i("updateARea("+schedule.getCourseID() + " - " + schedule.getStudyDate()+ ")", note);

        return i;

    }

    //get all schedule
    public ArrayList<Schedule> getAllScheduleFromSQLite() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        // 1. build the query
        String query = "SELECT  * FROM " + DBConst.TB_SCHEDULE.TB_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Schedule schedule = null;
        if (cursor.moveToFirst()) {
            do {
                schedule = new Schedule();
                schedule.setTietBD(cursor.getInt(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_TIET_BD)));
                schedule.setIsOff(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_ISOFF)));
                schedule.setStudyDate(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_STUDY_DATE)));
                schedule.setRoom(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_ROOM)));
                schedule.setNote(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_NOTE)));
                schedule.setCourseID(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_COURSE_ID)));
                schedule.setIsTheory(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_IS_THEORY)));

                // Add book to books
                schedules.add(schedule);
            } while (cursor.moveToNext());
        }

        Log.i("getAllSchedule", schedules.toString());

        // return books
        return schedules;
    }

    //get hashMap schedule
    public void getHashMapScheduleFromSQLite() {
//        HashMap<String,HashMap<String,Schedule>>  mapSchedules = new HashMap<>();

        // 1. build the query
        String query = "SELECT  * FROM " + DBConst.TB_SCHEDULE.TB_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Schedule schedule = null;
        if (cursor.moveToFirst()) {
            do {
                schedule = new Schedule();
                schedule.setTietBD(cursor.getInt(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_TIET_BD)));
                schedule.setIsOff(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_ISOFF)));
                schedule.setStudyDate(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_STUDY_DATE)));
                schedule.setRoom(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_ROOM)));
                schedule.setNote(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_NOTE)));
                schedule.setCourseID(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_COURSE_ID)));
                schedule.setIsTheory(cursor.getString(cursor.getColumnIndex(DBConst.TB_SCHEDULE.COL_IS_THEORY)));

                // Add book to books
                HashMap<String,Schedule> mapSchedule = new HashMap<>();
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Long longTime = formater.parse(schedule.getStudyDate()).getTime();
                    mapSchedule.put(longTime.toString(),schedule);
                    MyApplication.mapCourse.put(schedule.getCourseID(),mapSchedule);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        Log.i("getAllSchedule SQLite", mapCourse.toString());

        db.close();
        // return books
//        return mapCourse;
    }

    //add Schedule
    public void addStudent(Student student){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DBConst.TB_STUDENT.COL_STUDENT_ID,student.getStudentID() );
        values.put(DBConst.TB_STUDENT.COL_BIRTHDAY, student.getBirthday());
        values.put(DBConst.TB_STUDENT.COL_EMAIL, student.getEmail());
        values.put(DBConst.TB_STUDENT.COL_NOTE, student.getNote());
        values.put(DBConst.TB_STUDENT.COL_FACULTY_ID, student.getFacultyID());
        values.put(DBConst.TB_STUDENT.COL_FK_CLASS_ID, student.getClassID());
        values.put(DBConst.TB_STUDENT.COL_FK_USER_GROUP, student.getUserGroup());
        values.put(DBConst.TB_STUDENT.COL_PHONE, student.getPhone());
        values.put(DBConst.TB_STUDENT.COL_PASSWD, student.getPasswd());
        values.put(DBConst.TB_STUDENT.COL_FULL_NAME, student.getFullName());

        // 3. insert
        db.insert(DBConst.TB_STUDENT.TB_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        Log.i("Number rows of table:", getTableCount(DBConst.TB_STUDENT.TB_NAME) + "");
    }

    public void updateCurrentUserInSQLite(Student student){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(DBConst.TB_STUDENT.DROP);
        db.execSQL(DBConst.TB_STUDENT.CREATE);
        addStudent(student);
        Log.d("DAT SHIRO WORK", "UPDATE CURRENT USER");
        db.close();
    }

    public Student getLastLoginStudent(){
        Student student = new Student();

        // 1. build the query
        String query = "SELECT  * FROM " + DBConst.TB_STUDENT.TB_NAME + " LIMIT 1";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        if (cursor.moveToFirst()) {
            do {
                student.setStudentID(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_STUDENT_ID)));
                student.setFacultyID(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_FACULTY_ID)));
                student.setPhone(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_PHONE)));
                student.setBirthday(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_BIRTHDAY)));
                student.setClassID(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_FK_CLASS_ID)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_EMAIL)));
                student.setPasswd(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_PASSWD)));
                student.setFullName(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_FULL_NAME)));
                student.setUserGroup(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_FK_USER_GROUP)));
                student.setNote(cursor.getString(cursor.getColumnIndex(DBConst.TB_STUDENT.COL_NOTE)));

            } while (cursor.moveToNext());
        }

        Log.i("getStudent()", student.toString());
        db.close();

        return student;
    }

    // Get lecturer
    public Lecturer getLecturer(String lecturerID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_LECTURER.TB_NAME, // a. table
                        new String[] {DBConst.TB_LECTURER.COL_NAME,DBConst.TB_LECTURER.COL_BIRTHDAY,DBConst.TB_LECTURER.COL_EMAIL, DBConst.TB_LECTURER.COL_RATING, DBConst.TB_LECTURER.COL_FK_USER_GROUP, DBConst.TB_LECTURER.COL_CREATED_AT, DBConst.TB_LECTURER.COL_MODIFIED_AT, DBConst.TB_LECTURER.COL_FACULTY_ID}, // b. column names
                        DBConst.TB_LECTURER.COL_LECTURER_ID+"=?", // c. selections
                        new String[] { lecturerID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build lecturer object
        Lecturer lecturer = new Lecturer();
//        lecturer.setId(lecturerID);
//        lecturer.setName(cursor.getString(0));
//        lecturer.setBirthday(new Date(cursor.getLong(1)));
//        lecturer.setMail(cursor.getString(2));
//        lecturer.setRating(cursor.getInt(3));
//        lecturer.setGroupName(cursor.getString(4));
//        lecturer.setCreatedAt(new Date(cursor.getLong(5)));
//        lecturer.setModifiedAt(new Date(cursor.getLong(6)));
//        lecturer.setFacultyID(cursor.getString(cursor.getColumnIndex(DBConst.TB_LECTURER.COL_FACULTY_ID)));
//
//        Log.i("getLecturer("+lecturerID+")", lecturer.toString());
//
//        // 5. return lecturer
        return lecturer;
    }

    // Get UserGroup
    public UserGroup getUserGroup(String groupName){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_USER_GROUP.TB_NAME, // a. table
                        new String[]{DBConst.TB_USER_GROUP.COL_SHORT_DESCRIPTION, DBConst.TB_USER_GROUP.COL_PERMISSION}, // b. column names
                        DBConst.TB_USER_GROUP.COL_NAME+"=?", // c. selections
                        new String[] { groupName }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        UserGroup userGroup = new UserGroup();
        userGroup.setName(groupName);
        userGroup.setShortDescription(cursor.getString(0));
        userGroup.setPermission(cursor.getString(1));

        Log.i("getUserGroup("+groupName+")", userGroup.toString());

        // 5. return book
        return userGroup;
    }

    // Get Subject
    public Subject getSubject(String subjectID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_SUBJECT.TB_NAME, // a. table
                        new String[]{DBConst.TB_SUBJECT.COL_SUBJECT_NAME, DBConst.TB_SUBJECT.COL_SO_TC}, // b. column names
                        DBConst.TB_SUBJECT.COL_SUBJECT_ID+"=?", // c. selections
                        new String[] { subjectID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        Subject subject = new Subject();
        subject.setSubjectName(cursor.getString(0));
        subject.setSoTC(cursor.getInt(1));
        subject.setSubjectID(subjectID);

        Log.i("getSubject("+subjectID+")", subject.toString());

        // 5. return subject
        return subject;
    }

    // Get News
    public News getNews(String newsID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_NEWS.TB_NAME, // a. table
                        new String[]{DBConst.TB_NEWS.COL_TITLE, DBConst.TB_NEWS.COL_CONTENT, DBConst.TB_NEWS.COL_FEATURE_IMAGE_ID, DBConst.TB_NEWS.COL_AUTHOR, DBConst.TB_NEWS.COL_CREATED_AT, DBConst.TB_NEWS.COL_MODIFIED_AT}, // b. column names
                        DBConst.TB_NEWS.COL_NEWS_ID+"=?", // c. selections
                        new String[] { newsID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        News news = new News();
        news.setTitle(cursor.getString(0));
        news.setContent(cursor.getString(1));
        news.setFeatureImageId(cursor.getInt(2));
//        news.setAuthor(cursor.getString(3));
//        news.setCreatedAt(new Date(cursor.getLong(4)));
//        news.setModifiedAt(new Date(cursor.getLong(5)));

        Log.i("getNews("+newsID+")", news.toString());

        // 5. return news
        return news;
    }

    // Get Marks
    public Mark getMark(String subjectID, String studentID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_MARK.TB_NAME, // a. table
                        new String[]{DBConst.TB_MARK.COL_MARK_ID, DBConst.TB_MARK.COL_FK_STUDENT_ID, DBConst.TB_MARK.COL_FK_SUBJECT_ID, DBConst.TB_MARK.COL_CC, DBConst.TB_MARK.COL_TH, DBConst.TB_MARK.COL_KT, DBConst.TB_MARK.COL_THI, DBConst.TB_MARK.COL_KQ,  DBConst.TB_MARK.COL_TK, DBConst.TB_MARK.COL_XEP_LOAI, DBConst.TB_MARK.COL_SEMESTER}, // b. column names
                        DBConst.TB_MARK.COL_FK_STUDENT_ID+"=? AND " + DBConst.TB_MARK.COL_FK_SUBJECT_ID+"=?", // c. selections
                        new String[] { studentID, subjectID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        Mark mark = new Mark();
//        mark.setMarkID(cursor.getString(0));
//        mark.setStudentID(cursor.getString(1));
//        mark.setSubjectID(cursor.getString(2));
//        mark.setCC(cursor.getFloat(3));
//        mark.setTH(cursor.getFloat(4));
//        mark.setKT(cursor.getFloat(5));
//        mark.setThi(cursor.getFloat(6));
//        mark.setKQ(cursor.getString(7));
//        mark.setTK(cursor.getFloat(8));
//        mark.setXepLoai(cursor.getString(9));
//        mark.setSemester(cursor.getString(cursor.getColumnIndex(DBConst.TB_MARK.COL_SEMESTER)));

        Log.i("getMark("+studentID+" - " + subjectID + ")", mark.toString());

        // 5. return mark
        return mark;
    }

    // Get Course
    public Course getCourse(String courseID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_COURSE.TB_NAME, // a. table
                        new String[]{DBConst.TB_COURSE.COL_COURSE_ID, DBConst.TB_COURSE.COL_FK_SUBJECT_ID, DBConst.TB_COURSE.COL_FK_CLASS_ID, DBConst.TB_COURSE.COL_SO_TIET, DBConst.TB_COURSE.COL_DAY_OF_WEEK, DBConst.TB_COURSE.COL_STUDY_GROUP,  DBConst.TB_COURSE.COL_TTH, DBConst.TB_COURSE.COL_TIET_BD, DBConst.TB_COURSE.COL_NOTE, DBConst.TB_COURSE.COL_START_DATE, DBConst.TB_COURSE.COL_END_DATE, DBConst.TB_COURSE.COL_FK_LECTURER_ID, DBConst.TB_COURSE.COL_SEMESTER}, // b. column names
                        DBConst.TB_COURSE.COL_COURSE_ID+"=?", // c. selections
                        new String[] { courseID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        Course course = new Course();
        course.setCourseID(cursor.getString(0));
        course.setSubjectID(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_FK_SUBJECT_ID)));
//        course.setClassCode(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_FK_CLASS_ID)));
//        course.setSoTiet(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_SO_TIET)));
//        course.setDayOfWeek(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_DAY_OF_WEEK)));
//
//        course.setStudyGroup(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_STUDY_GROUP)));
//        course.setTTH(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_TTH)));
//        course.setTietBD(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_TIET_BD)));
//        course.setNote(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_NOTE)));
//        course.setStartDate(new Date(cursor.getLong(cursor.getColumnIndex(DBConst.TB_COURSE.COL_START_DATE))));
//        course.setEndDate(new Date(cursor.getLong(cursor.getColumnIndex(DBConst.TB_COURSE.COL_END_DATE))));
//        course.setLecturerID(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_FK_LECTURER_ID)));
//        course.setSemester(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_SEMESTER)));

        Log.i("getCourse("+courseID+")", course.toString());

        // 5. return subject
        return course;
    }
    public ArrayList<Course> getListCourseByUserID(String attenderID){
        ArrayList<Course> listCourse = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + DBConst.TB_ATTENDANCE.TB_NAME + " WHERE " + DBConst.TB_ATTENDANCE.COL_ATTENDER_ID + " = " + attenderID;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null );

        // 3. go over each row, build book and add it to list
        Course course = null;
        if (cursor.moveToFirst()) {
            do {
                course = new Course();
                course = getCourse(cursor.getString(2));

                // Add book to books
                listCourse.add(course);
            } while (cursor.moveToNext());
        }

        Log.i("getListCourseByUserID()", listCourse.toString());

        // return courses
        return listCourse;
    }


}
