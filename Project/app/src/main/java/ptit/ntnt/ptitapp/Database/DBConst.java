package ptit.ntnt.ptitapp.Database;

/**
 * Created by datshiro on 21/03/2018.
 */

public class DBConst {
    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "PTIT-APP";

    public static class TB_PTIT_CLASS {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_PTIT_CLASS (CLASS_ID TEXT NOT NULL, CLASS_NAME TEXT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_PTIT_CLASS ;";

        public static final String TB_NAME = "TB_PTIT_CLASS";
        public static final String COL_CLASS_ID = "CLASS_ID";
        public static final String COL_CLASS_NAME = "CLASS_NAME";
    }

    public static class TB_SUBJECT {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_SUBJECT (SUBJECT_ID TEXT PRIMARY KEY, SUBJECT_NAME TEXT NOT NULL, SO_TC TEXT NOT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_SUBJECT ;";

        public static final String TB_NAME = "TB_SUBJECT";
        public static final String COL_SUBJECT_ID = "SUBJECT_ID";
        public static final String COL_SUBJECT_NAME = "SUBJECT_NAME";
        public static final String COL_SO_TC = "SO_TC";
    }

    public static class TB_SCHEDULE {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_SCHEDULE (SCHEDULE_ID TEXT PRIMARY KEY, COURSE_ID TEXT NOT NULL, TIET_BD TEXT NOT NULL, ROOM TEXT NOT NULL, IS_THEORY TEXT NOT NULL, STUDY_DATE NUMERIC NOT NULL, NOTE TEXT NOT NULL" +
                "FOREIGN KEY (COURSE_ID) REFERENCES " + TB_COURSE.TB_NAME+" (COURSE_ID);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_SCHEDULE ;";

        public static final String TB_NAME = "TB_SCHEDULE";
        public static final String COL_SCHEDULE_ID = "SCHEDULE_ID";
        public static final String COL_COURSE_ID = "COURSE_ID";
        public static final String COL_TIET_BD = "TIET_BD";
        public static final String COL_ROOM = "ROOM";
        public static final String COL_IS_THEORY = "IS_THEORY";
        public static final String COL_STUDY_DATE = "STUDY_DATE";
        public static final String COL_NOTE = "NOTE";
    }

    public static class TB_STUDENT {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_STUDENT (STUDENT_ID TEXT PRIMARY KEY,FULL_NAME TEXT NOT NULL, EMAIL TEXT NOT NULL, PHONE TEXT NULL, CLASS_ID TEXT NOT NULL, BIRTHDAY NUMERIC NOT NULL, FACULTY_ID TEXT NOT NULL,  CREATED_AT NUMERIC NOT NULL, MODIFIED_AT NUMERIC NOT NULL, LIST_COURSE TEXT NOT NULL, USER_GROUP TEXT NOT NULL,"+
                "FOREIGN KEY (CLASS_ID) REFERENCES " + TB_PTIT_CLASS.TB_NAME + "(CLASS_ID)," +
                "FOREIGN KEY (USER_GROUP) REFERENCES " + TB_USER_GROUP.TB_NAME + "(NAME));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_STUDENT ;";

        public static final String TB_NAME = "TB_STUDENT";
        public static final String COL_FULL_NAME = "FULL_NAME";
        public static final String COL_STUDENT_ID = "STUDENT_ID";
        public static final String COL_FK_CLASS_ID = "CLASS_ID";
        public static final String COL_EMAIL = "EMAIL";
        public static final String COL_BIRTHDAY = "BIRTHDAY";
        public static final String COL_CREATED_AT = "CREATED_AT";
        public static final String COL_MODIFIED_AT = "MODIFIED_AT";
        public static final String COL_FK_USER_GROUP = "USER_GROUP";
        public static final String COL_FACULTY_ID = "FACULTY_ID";
        public static final String COL_PHONE = "PHONE";
    }

    public static class TB_COURSE {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_COURSE (COURSE_ID TEXT PRIMARY KEY, SUBJECT_ID TEXT NOT NULL, CLASS_ID TEXT NOT NULL, STUDY_GROUP TEXT NOT NULL, TTH TEXT NULL, DAY_OF_WEEK TEXT NULL, TIET_BD TEXT NOT NULL, SO_TIET TEXT NOT NULL, START_DATE NUMERIC NOT NULL, END_DATE NUMERIC, LECTURER_ID TEXT NOT NULL, NOTE TEXT NULL, SEMESTER TEXT NOT NULL,"+
                "FOREIGN KEY (CLASS_ID) REFERENCES " + TB_PTIT_CLASS.TB_NAME + "(CLASS_ID)," +
                "FOREIGN KEY (LECTURER_ID) REFERENCES " + TB_LECTURER.TB_NAME + "(LECTURER_ID)," +
                "FOREIGN KEY (SUBJECT_ID) REFERENCES " + TB_SUBJECT.TB_NAME + "(SUBJECT_ID));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_COURSE ;";

        public static final String TB_NAME = "TB_COURSE";
        public static final String COL_COURSE_ID = "COURSE_ID";
        public static final String COL_FK_SUBJECT_ID = "SUBJECT_ID";
        public static final String COL_FK_CLASS_ID = "CLASS_ID";
        public static final String COL_STUDY_GROUP = "STUDY_GROUP";
        public static final String COL_TTH = "TTH";
        public static final String COL_DAY_OF_WEEK = "DAY_OF_WEEK";
        public static final String COL_TIET_BD = "TIET_BD";
        public static final String COL_SO_TIET = "SO_TIET";
        public static final String COL_NOTE = "NOTE";
        public static final String COL_START_DATE = "START_DATE";
        public static final String COL_END_DATE= "END_DATE";
        public static final String COL_FK_LECTURER_ID = "LECTURER_ID";
        public static final String COL_SEMESTER = "SEMESTER";

    }

    public static class TB_MARK {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_MARK (MARK_ID TEXT PRIMARY KEY, STUDENT_ID TEXT NOT NULL, SUBJECT_ID TEXT NOT NULL, CC REAL NULL, KT REAL NULL, TH REAL NULL, THI REAL NOT NULL, TK REAL NOT NULL, XEP_LOAI TEXT NOT NULL, KQ TEXT NOT NULL, SEMESTER TEXT NOT NULL," +
                "FOREIGN KEY (STUDENT_ID) REFERENCES " + TB_STUDENT.TB_NAME + "(STUDENT_ID)," +
                "FOREIGN KEY (SUBJECT_ID) REFERENCES " + TB_SUBJECT.TB_NAME + "(SUBJECT_ID));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_MARK ;";

        public static final String TB_NAME = "TB_MARK";
        public static final String COL_MARK_ID = "MARK_ID";
        public static final String COL_FK_STUDENT_ID = "STUDENT_ID";
        public static final String COL_FK_SUBJECT_ID = "SUBJECT_ID";
        public static final String COL_CC = "CC";
        public static final String COL_KT = "KT";
        public static final String COL_TH = "TH";
        public static final String COL_THI = "THI";
        public static final String COL_TK = "TK";
        public static final String COL_KQ = "KQ";
        public static final String COL_XEP_LOAI = "XEP_LOAI";
        public static final String COL_SEMESTER = "SEMESTER";

    }

    public static class TB_LECTURER {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_LECTURER (LECTURER_ID TEXT NOT NULL, NAME TEXT NOT NULL,EMAIL TEXT NOT NULL,PHONE TEXT NULL,BIRTHDAY NUMERIC NOT NULL, FACULTY_ID TEXT NOT NULL,RATING INTEGER NOT NULL, CREATED_AT NUMERIC NULL, MODIFIED_AT NUMERIC NULL,USER_GROUP TEXT NULL,"+
                "FOREIGN KEY (USER_GROUP) REFERENCES " + TB_USER_GROUP.TB_NAME + "(USER_GROUP));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_LECTURER ;";

        public static final String TB_NAME = "TB_LECTURER";
        public static final String COL_LECTURER_ID = "LECTURER_ID";
        public static final String COL_FACULTY_ID = "FACULTY_ID";
        public static final String COL_RATING = "RATING";
        public static final String COL_NAME = "NAME";
        public static final String COL_EMAIL = "EMAIL";
        public static final String COL_BIRTHDAY = "BIRTHDAY";
        public static final String COL_CREATED_AT = "CREATED_AT";
        public static final String COL_MODIFIED_AT = "MODIFIED_AT";
        public static final String COL_FK_USER_GROUP = "USER_GROUP";
    }

    public static class TB_USER_GROUP {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_USER_GROUP (NAME TEXT NOT NULL, SHORT_DESCRIPTION TEXT NULL, PERMISSION TEXT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_USER_GROUP ;";

        public static final String TB_NAME = "TB_USER_GROUP";
        public static final String COL_NAME = "NAME";
        public static final String COL_SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
        public static final String COL_PERMISSION = "PERMISSION";
    }

    public static class TB_NEWS {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_NEWS (NEWS_ID TEXT PRIMARY KEY, TITLE TEXT NOT NULL, CONTENT TEXT NOT NULL, AUTHOR TEXT NOT NULL, FEATURE_IMAGE_ID INT NOT NULL, CREATED_AT NUMERIC NOT NULL, MODIFIED_AT NUMERIC NOT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_NEWS ;";

        public static final String TB_NAME = "TB_NEWS";
        public static final String COL_NEWS_ID = "NEWS_ID";
        public static final String COL_TITLE = "TITLE";
        public static final String COL_CONTENT = "CONTENT";
        public static final String COL_AUTHOR = "AUTHOR";
        public static final String COL_FEATURE_IMAGE_ID = "FEATURE_IMAGE_ID";
        public static final String COL_CREATED_AT = "CREATED_AT";
        public static final String COL_MODIFIED_AT = "MODIFIED_AT";
    }

    public static class TB_ATTENDANCE {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_ATTENDANCE (_ID TEXT PRIMARY KEY, ATTENDER_ID TEXT NOT NULL, COURSE_ID TEXT NOT NULL," +
                "FOREIGN KEY (COURSE_ID) REFERENCES " + TB_COURSE.TB_NAME + "(STUDENT_ID));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_ATTENDANCE ;";

        public static final String TB_NAME = "TB_ATTENDANCE";
        public static final String COL_ID = "_ID";
        public static final String COL_ATTENDER_ID = "ATTENDER_ID";
        public static final String COL_FK_COURSE_ID = "COURSE_ID";
    }

    public static class TB_FACULTY{
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_FACULTY (FACULTY_ID TEXT PRIMARY KEY, FACULTY_NAME TEXT NOT NULL)";
        public static final String TB_NAME = "TB_FACULTY";
        public static final String COL_FACULTY_ID = "FACULTY_ID";
        public static final String COL_FACULTY_NAME = "FACULTY_NAME";
    }
    public static class TB_MISSION{
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_MISSION (MISSION_ID INT PRIMARY KEY, MISSION_NAME NVARCHAR(200) )";
        public static final String TB_NAME = "TB_MISSION";
        public static final String COL_MISSION_ID = "MISSION_ID";
        public static final String COL_MISSION_NAME = "MISSION_NAME";
    }
    public static class TB_DEV{
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_DEV (DEV_ID INT PRIMARY KEY, DEV_NAME NVARCHAR(200))";
        public static final String TB_NAME = "TB_DEV";
        public static final String COL_DEV_ID = "DEV_ID";
        public static final String COL_DEV_NAME = "DEV_NAME";
    }
    public static class TB_TONGHOP{
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_TONGHOP (MISSION_ID INT , DEV_ID INT , PRIMARY KEY(MISSION_ID, DEV_ID))";
        public static final String TB_NAME = "TB_TONGHOP";
        public static final String COL_MISSION_ID = "MISSION_ID";
        public static final String COL_DEV_ID = "DEV_ID";
    }
    public static class TB_EXAM{
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_EXAM (EXAM_ID INT , EXAM_NAME NVARCHAR(200))";
        public static final String TB_NAME = "TB_EXAM";
    }
}
