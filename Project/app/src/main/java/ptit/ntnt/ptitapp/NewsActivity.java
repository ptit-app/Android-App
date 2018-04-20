package ptit.ntnt.ptitapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
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
    private ImageButton addButton;
    private ArrayList<News> listNews =new ArrayList<>();
    private FirebaseHelper db = new FirebaseHelper();
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setControl();


        newsAdapter = new NewsAdapter(NewsActivity.this, R.layout.listview_news, listNews);
        lvNews.setAdapter(newsAdapter);

        db.getListNews2(newsAdapter);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(NewsActivity.this, i+"", Toast.LENGTH_SHORT).show();
                News item = (News) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.NEWS_ID, item.getId());
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsActivity.this, AddNewsActivity.class);
                intent.putExtra(AddNewsActivity.LIST_SIZE, newsAdapter.getListItem().size());
                startActivity(intent);
            }
        });
    }
    protected void setControl(){
        addButton = findViewById(R.id.addButton);
        lvNews = (ListView) findViewById(R.id.lvNews);
    }
    private void importData(){
        DBHelper dbHelper = new DBHelper(this);
    }

}
