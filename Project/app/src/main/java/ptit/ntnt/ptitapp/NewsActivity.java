package ptit.ntnt.ptitapp;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Database.DBHelper;
import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.PTITClass;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.User;

public class NewsActivity extends AppCompatActivity {

    private ListView lvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setControl();

        final ArrayList<News> listNews;
        listNews = new ArrayList<News>();


//        Log.d(TAG, listNews);
        final NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.listview_news, listNews);
        lvNews.setAdapter(newsAdapter);

        FirebaseHelper db = new FirebaseHelper();
        final AtomicInteger count = new AtomicInteger();

        db.mData.child("TB_COURSE").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                // New child added, increment count
                int newCount = count.incrementAndGet();
                System.out.println("Added " + dataSnapshot.getKey() + ", count is " + newCount);
                Log.d("Count Object", String.valueOf(newCount));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

            // ...
        });


//        try{
//            DBHelper ptitDBHelper = new DBHelper(this);
//            SQLiteDatabase db = ptitDBHelper.getReadableDatabase();
//            PTITClass ptitClass = getPTITClass(db, "1");
//        }catch (SQLException e){
//            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }


    }
    protected void setControl(){
        lvNews = findViewById(R.id.lvNews);
    }
    private void importData(){
        DBHelper dbHelper = new DBHelper(this);
    }

}
