package ptit.ntnt.ptitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Models.News;

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

    }
    protected void setControl(){
        lvNews = findViewById(R.id.lvNews);
    }
}
