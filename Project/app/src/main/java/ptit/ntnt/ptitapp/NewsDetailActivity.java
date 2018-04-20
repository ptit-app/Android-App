package ptit.ntnt.ptitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
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

    EditText title;
    EditText content;
    TextView uploadDate;
    TextView modifiedDate;

    ImageButton editButton;
    ImageButton deleteButton;
    public static final String NEWS_ID = "news_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        setControl();

        final String newsID = (String)getIntent().getExtras().get(NEWS_ID);
        Toast.makeText(this, "Id: " + newsID, Toast.LENGTH_SHORT).show();
        // Get News from Firebase
        final News[] news = new News[1];
        final DatabaseReference firebase;
        firebase = FirebaseDatabase.getInstance().getReference();
        firebase.child("TB_NEWS2").child(String.valueOf(newsID)).addListenerForSingleValueEvent(new ValueEventListener() {
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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.child("TB_NEWS2").child(String.valueOf(newsID)).removeValue();
                Toast.makeText(NewsDetailActivity.this, "Removed this news", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.isEnabled()){
                    title.setEnabled(false);
                    content.setEnabled(false);
                    Toast.makeText(NewsDetailActivity.this, "Edit Mode Disabled", Toast.LENGTH_SHORT).show();
                    if (title.getText().toString().compareTo(news[0].getTitle()) != 0 || content.getText().toString().compareTo(news[0].getContent()) !=0){
                        // Save News
                        News editedNews = new News();
                        editedNews.setId(newsID);
                        editedNews.setTitle(title.getText().toString());
                        editedNews.setContent(content.getText().toString());
                        editedNews.setCreatedAt(news[0].getCreatedAt());
                        editedNews.setDescription(content.getText().toString().length() < 100 ? content.getText().toString() : content.getText().toString().substring(100));
                        clearNews();
                        setNews(editedNews);
                        firebase.child("TB_NEWS2").child(newsID).setValue(editedNews);
                        Toast.makeText(NewsDetailActivity.this, "Updated News", Toast.LENGTH_SHORT).show();
                    }
                    editButton.setImageResource(R.drawable.editicon);

                }
                else{
                    title.setEnabled(true);
                    content.setEnabled(true);
                    Toast.makeText(NewsDetailActivity.this, "Edit Mode Enabled", Toast.LENGTH_SHORT).show();
                    editButton.setImageResource(R.drawable.save);

                }

            }
        });
    }

    private void setControl(){
        title =  findViewById(R.id.newsDetailTitle);
        content = findViewById(R.id.newsDetailContent);
        uploadDate =  findViewById(R.id.newsDetailUploadDate);
        modifiedDate  =  findViewById(R.id.newsDetailModifiedDate);
        editButton = findViewById(R.id.newsDetailEditImageButton);
        deleteButton = findViewById(R.id.newsDetailDeleteImageButton);

        title.setEnabled(false);
        content.setEnabled(false);
    }
    private void setNews(News news){
        title.setText(news.getTitle());
//        content.loadData(news.getContent(),"text/html; charset=utf-8", "utf-8");
        content.setText(news.getContent());
        uploadDate.setText("Uploaded At: " + news.getCreatedAt());
        modifiedDate.setText("Modified at: " + news.getModifiedAt());
    }
    public void clearNews(){
        title.setText("");
        content.setText("");
        uploadDate.setText("");
        modifiedDate.setText("");
    }
}
