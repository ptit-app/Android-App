package ptit.ntnt.ptitapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by datshiro on 19/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PTIT-App"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, DB_VERSION);

    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.execSQL(DBConst.TB_SUBJECT.CREATE);
            db.execSQL(DBConst.TB_PTIT_CLASS.CREATE);
            db.execSQL(DBConst.TB_COURSE.CREATE);
            db.execSQL(DBConst.TB_USER_GROUP.CREATE);
            db.execSQL(DBConst.TB_STUDENT.CREATE);
        }
        if (oldVersion < 2) {
//Code to add the extra column
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

    public long insertClass(SQLiteDatabase db, String classCode, String className){
        ContentValues classValues = new ContentValues();
        classValues.put("CLASS_CODE", classCode);
        classValues.put("CLASS_NAME", className);
        return  db.insert("CLASS",null,classValues);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
