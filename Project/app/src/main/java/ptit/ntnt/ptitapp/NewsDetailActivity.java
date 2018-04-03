package ptit.ntnt.ptitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewsDetailActivity extends AppCompatActivity {

    TextView title;
    TextView content;
    TextView uploadDate;
    TextView modifiedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


    }

    private void setControl(){
        title = findViewById(R.id.newsDetailTitle);
        content = findViewById(R.id.newsDetailContent);
        uploadDate = findViewById(R.id.newsDetailUploadDate);
        modifiedDate  = findViewById(R.id.newsDetailModifiedDate);
    }
}
