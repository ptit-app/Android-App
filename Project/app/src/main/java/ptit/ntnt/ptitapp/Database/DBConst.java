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
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_PTIT_CLASS (CLASS_CODE TEXT NOT NULL, CLASS_NAME TEXT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_PTIT_CLASS ;";

        public static final String TB_NAME = "TB_PTIT_CLASS";
        public static final String COL_CLASS_CODE = "CLASS_CODE";
        public static final String COL_CLASS_NAME = "CLASS_NAME";
    }

    public static class TB_SUBJECT {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_SUBJECT (SUBJECT_ID TEXT NOT NULL, NAME TEXT NOT NULL, SO_TC INTEGER NOT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_SUBJECT ;";

        public static final String TB_NAME = "TB_SUBJECT";
        public static final String COL_SUBJECT_ID = "SUBJECT_ID";
        public static final String COL_NAME = "NAME";
        public static final String COL_SO_TC = "SO_TC";
    }

    public static class TB_STUDENT {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_STUDENT (STUDENT_ID TEXT NOT NULL,FULL_NAME TEXT NOT NULL, CLASS_CODE TEXT NOT NULL, PHONE TEXT NULL,EMAIL TEXT NOT NULL,NOTE NCHAR NULL,BIRTHDAY DATE NOT NULL, CREATED_AT DATE NOT NULL, MODIFIED_AT DATE NOT NULL,USER_GROUP TEXT NOT NULL,"+
                "FOREIGN KEY (CLASS_CODE) REFERENCES " + TB_PTIT_CLASS.TB_NAME + "(CLASS_CODE)," +
                "FOREIGN KEY (USER_GROUP) REFERENCES " + TB_USER_GROUP.TB_NAME + "(NAME));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_STUDENT ;";

        public static final String TB_NAME = "TB_STUDENT";
        public static final String COL_STUDENT_ID = "STUDENT_ID";
        public static final String COL_FK_CLASS_CODE = "CLASS_CODE";
        public static final String COL_EMAIL = "EMAIL";
        public static final String COL_BIRTHDAY = "BIRTHDAY";
        public static final String COL_CREATED_AT = "CREATED_AT";
        public static final String COL_MODIFIED_AT = "MODIFIED_AT";
        public static final String COL_FK_USER_GROUP = "USER_GROUP";
    }

    public static class TB_COURSE {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_COURSE (SUBJECT_ID TEXT NOT NULL, CLASS_CODE TEXT NOT NULL, STUDY_GROUP TEXT NOT NULL, TTH INTEGER NULL, DAY_OF_WEEK TEXT NULL, TIET_BD INTEGER NOT NULL, SO_TIET INTEGER NOT NULL, ROOM TEXT NULL, STUDY_TIME TEXT NULL, NOTE TEXT NULL,"+
                "FOREIGN KEY (CLASS_CODE) REFERENCES " + TB_PTIT_CLASS.TB_NAME + "(CLASS_CODE)," +
                "FOREIGN KEY (SUBJECT_ID) REFERENCES " + TB_SUBJECT.TB_NAME + "(SUBJECT_ID));";
        public static final String DROP = "DROP TABLE IF EXISTS TB_COURSE ;";

        public static final String TB_NAME = "TB_COURSE";
        public static final String COL_FK_SUBJECT_ID = "SUBJECT_ID";
        public static final String COL_FK_CLASS_CODE = "CLASS_CODE";
        public static final String COL_STUDY_GROUP = "STUDY_GROUP";
        public static final String COL_TTH = "TTH";
        public static final String COL_DAY_OF_WEEK = "DAY_OF_WEEK";
        public static final String COL_TIET_BD = "TIET_BD";
        public static final String COL_SO_TIET = "SO_TIET";
        public static final String COL_ROOM = "ROOM";
        public static final String COL_STUDY_TIME = "STUDY_TIME";
        public static final String COL_NOTE = "NOTE";
    }

    public static class TB_USER_GROUP {
        public static final String CREATE = "CREATE TABLE IF NOT EXISTS TB_USER_GROUP (NAME TEXT NOT NULL, SHORT_DESCRIPTION TEXT NULL, PERMISSION TEXT NULL);";
        public static final String DROP = "DROP TABLE IF EXISTS TB_USER_GROUP ;";

        public static final String TB_NAME = "TB_USER_GROUP";
        public static final String COL_NAME = "NAME";
        public static final String COL_SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
        public static final String COL_PERMISSION = "PERMISSION";
    }


}
