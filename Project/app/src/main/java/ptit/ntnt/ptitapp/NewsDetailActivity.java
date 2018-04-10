package ptit.ntnt.ptitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.News;

public class NewsDetailActivity extends AppCompatActivity {

    TextView title;
    WebView content;
    TextView uploadDate;
    TextView modifiedDate;

    public static final String NEWS_ID = "news_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        setControl();

        int newsID = (Integer)getIntent().getExtras().get(NEWS_ID) + 1;     // Do list view bat dau tu 0 trong khi id tren firebase bat dau tu 1
//        Toast.makeText(this, "Id" + newsID, Toast.LENGTH_SHORT).show();
        // Get News from Firebase
        final News[] news = new News[1];
        DatabaseReference firebase;
        firebase = FirebaseDatabase.getInstance().getReference();
        firebase.child(DBConst.TB_NEWS.TB_NAME).child(String.valueOf(newsID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                news[0] = dataSnapshot.getValue(News.class);
                if(news[0] != null && news[0].getTitle() != null){
                    Log.d("News", news[0] + "");
                    setNews(news[0]);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setControl(){
        title =  findViewById(R.id.newsDetailTitle);
        content = findViewById(R.id.newsDetailContent);
        uploadDate =  findViewById(R.id.newsDetailUploadDate);
        modifiedDate  =  findViewById(R.id.newsDetailModifiedDate);
    }
    private void setNews(News news){
        title.setText(news.getTitle());
        content.loadData(news.getContent(),"text/html; charset=utf-8", "utf-8");
        uploadDate.setText("Uploaded At: " + news.getCreatedAt());
        modifiedDate.setText("Modified at: " + news.getModifiedAt());
    }
}
