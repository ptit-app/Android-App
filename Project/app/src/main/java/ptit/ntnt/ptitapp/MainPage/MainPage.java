package ptit.ntnt.ptitapp.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.MainActivity;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.NewsActivity;
import ptit.ntnt.ptitapp.NewsDetailActivity;
import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/18/18.
 */

public class MainPage extends Fragment{

    View view;
    ViewPager viewPager;

    private ListView lvNews;
    private ArrayList<News> listNews =new ArrayList<>();
    private FirebaseHelper db = new FirebaseHelper();
    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_page,container,false);

        setControl();
        newsAdapter = new NewsAdapter(getContext(), R.layout.listview_news, listNews);
        lvNews.setAdapter(newsAdapter);

        db.getListgNews(newsAdapter, 5);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(NewsActivity.this, i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.NEWS_ID, (int) id);
                startActivity(intent);

            }
        });
        return view;
    }

    protected void setControl(){
        lvNews = (ListView) view.findViewById(R.id.lvNews_MainPage);
    }

}
