package ptit.ntnt.ptitapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

import ptit.ntnt.ptitapp.Models.Course;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.Models.UserGroup;

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
        updateMyDatabase(db, 0, DBConst.DATABASE_VERSION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, DBConst.DATABASE_VERSION);

    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.execSQL(DBConst.TB_SUBJECT.CREATE);
            db.execSQL(DBConst.TB_PTIT_CLASS.CREATE);
            db.execSQL(DBConst.TB_COURSE.CREATE);
            db.execSQL(DBConst.TB_USER_GROUP.CREATE);
            db.execSQL(DBConst.TB_STUDENT.CREATE);
            db.execSQL(DBConst.TB_LECTURER.CREATE);
            db.execSQL(DBConst.TB_MARK.CREATE);
            db.execSQL(DBConst.TB_NEWS.CREATE);
            db.execSQL(DBConst.TB_ATTENDANCE.CREATE);

        }
        if (oldVersion < 2) {
//Code to add the extra column
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys='ON';");
        }
    }

    public void importFromFile(SQLiteDatabase db, Context context, String csvFileName, String tableName){
        String mCSVfile = csvFileName;
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";

        db.beginTransaction();
        try {
            String[] dbCol = buffer.readLine().split("|");
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split("|");
                if (colums.length != dbCol.length) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(dbCol.length);
                for (int i = 0; i < dbCol.length; i++){
                    cv.put(dbCol[i], colums[i].trim());
                }
                db.insert(tableName, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public long insertClass(SQLiteDatabase db, String classID, String className){
        ContentValues classValues = new ContentValues();
        classValues.put("CLASS_ID", classID);
        classValues.put("CLASS_NAME", className);
        return  db.insert("CLASS",null,classValues);

    }

    //get student with id
    public Student getStudent(String studentID){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_STUDENT.TB_NAME, // a. table
                        new String[] {DBConst.TB_STUDENT.COL_FULL_NAME,DBConst.TB_STUDENT.COL_BIRTHDAY,DBConst.TB_STUDENT.COL_EMAIL, DBConst.TB_STUDENT.COL_FK_CLASS_ID, DBConst.TB_STUDENT.COL_FK_USER_GROUP, DBConst.TB_STUDENT.COL_CREATED_AT, DBConst.TB_STUDENT.COL_MODIFIED_AT, DBConst.TB_STUDENT.COL_PHONE, DBConst.TB_STUDENT.COL_FACULTY_ID}, // b. column names
                        DBConst.TB_STUDENT.COL_STUDENT_ID+"=?", // c. selections
                        new String[] { studentID }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build student object
        Student student = new Student();
        student.setId(studentID);
        student.setFullName(cursor.getString(0));
        student.setBirthday(new Date(cursor.getLong(1)));
        student.setMail(cursor.getString(2));
        student.setClassCode(cursor.getString(3));
        student.setGroupName(cursor.getString(4));
        student.setCreatedAt(new Date(cursor.getLong(5)));
        student.setModifiedAt(new Date(cursor.getLong(6)));
        student.setPhone(cursor.getString(7));
        student.setFacultyID(cursor.getString(8));

        Log.i("getStudent("+studentID+")", student.toString());

        // 5. return student
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
        lecturer.setId(lecturerID);
        lecturer.setName(cursor.getString(0));
        lecturer.setBirthday(new Date(cursor.getLong(1)));
        lecturer.setMail(cursor.getString(2));
        lecturer.setRating(cursor.getInt(3));
        lecturer.setGroupName(cursor.getString(4));
        lecturer.setCreatedAt(new Date(cursor.getLong(5)));
        lecturer.setModifiedAt(new Date(cursor.getLong(6)));
        lecturer.setFacultyID(cursor.getString(cursor.getColumnIndex(DBConst.TB_LECTURER.COL_FACULTY_ID)));

        Log.i("getLecturer("+lecturerID+")", lecturer.toString());

        // 5. return lecturer
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
        subject.setName(cursor.getString(0));
        subject.setSoTC(cursor.getInt(1));
        subject.setId(subjectID);

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
        news.setAuthor(cursor.getString(3));
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
        mark.setMarkID(cursor.getString(0));
        mark.setStudentID(cursor.getString(1));
        mark.setSubjectID(cursor.getString(2));
        mark.setCC(cursor.getFloat(3));
        mark.setTH(cursor.getFloat(4));
        mark.setKT(cursor.getFloat(5));
        mark.setThi(cursor.getFloat(6));
        mark.setKQ(cursor.getString(7));
        mark.setTK(cursor.getFloat(8));
        mark.setXepLoai(cursor.getString(9));
        mark.setSemester(cursor.getString(cursor.getColumnIndex(DBConst.TB_MARK.COL_SEMESTER)));

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
        course.setClassCode(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_FK_CLASS_ID)));
        course.setSoTiet(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_SO_TIET)));
        course.setDayOfWeek(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_DAY_OF_WEEK)));

        course.setStudyGroup(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_STUDY_GROUP)));
        course.setTTH(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_TTH)));
        course.setTietBD(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_TIET_BD)));
        course.setNote(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_NOTE)));
        course.setStartDate(new Date(cursor.getLong(cursor.getColumnIndex(DBConst.TB_COURSE.COL_START_DATE))));
        course.setEndDate(new Date(cursor.getLong(cursor.getColumnIndex(DBConst.TB_COURSE.COL_END_DATE))));
        course.setLecturerID(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_FK_LECTURER_ID)));
        course.setSemester(cursor.getString(cursor.getColumnIndex(DBConst.TB_COURSE.COL_SEMESTER)));

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

    // Get Schedule
    public Schedule getSchedule(String courseID, Date studyDate){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(DBConst.TB_SCHEDULE.TB_NAME, // a. table
                        new String[]{DBConst.TB_SCHEDULE.COL_SCHEDULE_ID, DBConst.TB_SCHEDULE.COL_ROOM, DBConst.TB_SCHEDULE.COL_STUDY_DATE, DBConst.TB_SCHEDULE.COL_TIET_BD, DBConst.TB_SCHEDULE.COL_IS_THEORY, DBConst.TB_SCHEDULE.COL_NOTE}, // b. column names
                        DBConst.TB_SCHEDULE.COL_COURSE_ID+"=? AND " + DBConst.TB_SCHEDULE.COL_STUDY_DATE + "=?", // c. selections
                        new String[] { courseID , String.valueOf(studyDate)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build area object
        Schedule schedule = new Schedule();
        schedule.setScheduleID(cursor.getString(0));
        schedule.setRoom(cursor.getString(1));
        schedule.setStudyDate(new Date(cursor.getLong(2)));
        schedule.setTietBD(cursor.getInt(3));
        schedule.setIsTheory(cursor.getString(4));
        schedule.setNote(cursor.getString(5));

        Log.i("getSchedule("+courseID + " - " + studyDate + ")", schedule.toString());

        // 5. return schedule
        return schedule;
    }

}
