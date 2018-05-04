package ptit.ntnt.ptitapp.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import ptit.ntnt.ptitapp.CustomAdapter.NewsAdapter;
import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.MainActivity;
import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.Models.Schedule;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.NewsActivity;
import ptit.ntnt.ptitapp.NewsDetailActivity;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.TimeTable.SubjectSchedule;
import ptit.ntnt.ptitapp.TimeTable.SubjectScheduleAdapter;
import ptit.ntnt.ptitapp.Tools;

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


    SubjectScheduleAdapter subjectScheduleAdapter;
    ArrayList<SubjectSchedule> arrSubjectSchedules;
    ArrayList<Schedule> arrSchedules;
    DatabaseReference mData;
    String tenMH;
    String buoi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_page,container,false);

        setControl();
        newsAdapter = new NewsAdapter(getContext(), R.layout.listview_news, listNews);
        lvNews.setAdapter(newsAdapter);

        db.getListNews(newsAdapter);

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
