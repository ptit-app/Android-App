package ptit.ntnt.ptitapp;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Database.DBHelper;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.PTITClass;

public class NewsActivity extends AppCompatActivity {

    private ListView lvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setControl();

        final ArrayList<News> listNews;
        listNews = new ArrayList<News>();
        listNews.add(new News("Thông báo đóng học phí","Hạn chót đóng học phí là cuối tháng này rồi, mấy mẹ tranh thủ gom tiền, gom hụi, đưa đây nhanh lên nào!"));
        listNews.add(new News("Thông báo đóng học phí","Hạn chót đóng học phí là cuối tháng này rồi, mấy mẹ tranh thủ gom tiền, gom hụi, đưa đây nhanh lên nào!"));

//        Log.d(TAG, listNews);
        final NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.listview_news, listNews);
        lvNews.setAdapter(newsAdapter);

        try{
            SQLiteOpenHelper ptitDBHelper = new DBHelper(this);
            SQLiteDatabase db = ptitDBHelper.getReadableDatabase();
            PTITClass ptitClass = getPTITClass(db, "1");
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }
    protected void setControl(){
        lvNews = findViewById(R.id.lvNews);
    }
    private void importData(){
        DBHelper dbHelper = new DBHelper(this);
    }

    private PTITClass getPTITClass(SQLiteDatabase db, String id){
        PTITClass ptitClass = new PTITClass();
        Cursor cursor = db.query("PTITClass",new String [] {"CLASSNAME","CLASSCODE"},"CLASSCODE = ?",new String[] {"D14CQAT01-N"},null,null,null);
        if (cursor.moveToFirst()){          // It's mean return a record
            ptitClass.setClassCode(cursor.getString(1));
            ptitClass.setClassName(cursor.getString(0));
        }
        return ptitClass;
    }
}
