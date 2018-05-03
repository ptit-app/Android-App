package ptit.ntnt.ptitapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.AppInfo.AppInfoAdapter;
import ptit.ntnt.ptitapp.CustomAdapter.drawerMenuAdapter;
import ptit.ntnt.ptitapp.CustomClass.drawerMenuItem;
import ptit.ntnt.ptitapp.MainPage.MainPageAdapter;
import ptit.ntnt.ptitapp.MarkTable.MarkTableAdapter;
import ptit.ntnt.ptitapp.RegisteredSubjects.RegisteredSubjects;
import ptit.ntnt.ptitapp.RegisteredSubjects.RegisteredSubjectsApdapter;
import ptit.ntnt.ptitapp.TeacherRating.TeacherRatingAdapter;
import ptit.ntnt.ptitapp.TestSchedule.TestScheduleAdapter;
import ptit.ntnt.ptitapp.TimeTable.TimeTableAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerMenu;
    private ImageView bt_open_drawer_menu;

    private ListView drawe_menu_lv;
    private ArrayList<drawerMenuItem> drawe_menu_list_array;
    private drawerMenuAdapter drawer_menu_adapter;

    private ViewPager viewPager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createDrawerMenu();
        drawer_menu_adapter = new drawerMenuAdapter(this,R.layout.listview_item_drawer_menu,drawe_menu_list_array);
        drawe_menu_lv.setAdapter(drawer_menu_adapter);


//        MainPageAdapter mainpage = new MainPageAdapter();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.main_frame,mainpage);
//        fragmentTransaction.commit();
    }


    private void createDrawerMenu() {
        drawe_menu_lv = (ListView) findViewById(R.id.drawer_menu_list);
        drawe_menu_list_array = new ArrayList<>();
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.home_page),R.drawable.home));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.time_table),R.drawable.timetable_icon));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.score),R.drawable.score_icon));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.exp_schedule),R.drawable.test_schedule_icon));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.registered_subjects),R.drawable.list));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.teacher_rating),R.drawable.star));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.app_info),R.drawable.info_icon));
        drawe_menu_list_array.add(new drawerMenuItem(getString(R.string.help),R.drawable.user_manual_icon));

        drawe_menu_lv.setOnItemClickListener(new ItemClick());
    }

    private void initView() {
        drawerMenu = (DrawerLayout) findViewById(R.id.main_page);
        bt_open_drawer_menu = (ImageView) findViewById(R.id.bt_open_drawer_menu);
        bt_open_drawer_menu.setOnClickListener(this);
        drawe_menu_lv = (ListView) findViewById(R.id.drawer_menu_list);

        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        MainPageAdapter mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);

    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            Toast.makeText(this,"hello", Toast.LENGTH_LONG).show();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onClick (View view_object){
        switch (view_object.getId()){
            case R.id.bt_open_drawer_menu:
                drawerMenu.openDrawer(Gravity.LEFT,true);
                break;
        }
    }

    private class ItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            viewPager = (ViewPager) findViewById(R.id.main_view_pager);

            TextView main_page_title = findViewById(R.id.main_page_title);

            LinearLayout mainToolBar = (LinearLayout) findViewById(R.id.main_toolbar);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

            switch (i) {
                case 0:
                    MainPageAdapter mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(mainPageAdapter);
                    main_page_title.setText(getString(R.string.app_name));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                case 1:
                    TimeTableAdapter timeTableAdapter = new TimeTableAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(timeTableAdapter);
                    main_page_title.setText(getString(R.string.time_table));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f));
                    tabLayout.setupWithViewPager(viewPager);
                    break;
                case 2:
                    MarkTableAdapter markTableAdapter = new MarkTableAdapter((getSupportFragmentManager()));
                    viewPager.setAdapter(markTableAdapter);
                    main_page_title.setText(getString(R.string.score));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                case 3:
                    TestScheduleAdapter testScheduleAdapter = new TestScheduleAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(testScheduleAdapter);
                    main_page_title.setText(getString(R.string.exp_schedule));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                case 4:
                    RegisteredSubjectsApdapter registeredSubjectsApdapter = new RegisteredSubjectsApdapter(getSupportFragmentManager());
                    viewPager.setAdapter(registeredSubjectsApdapter);
                    main_page_title.setText(getString(R.string.registered_subjects));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                case 5:
                    TeacherRatingAdapter teacherRatingAdapter = new TeacherRatingAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(teacherRatingAdapter);
                    main_page_title.setText(getString(R.string.teacher_rating));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                case 6:
                    AppInfoAdapter appInfoAdapter = new AppInfoAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(appInfoAdapter);
                    main_page_title.setText(getString(R.string.app_info));
                    mainToolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,0f));
                    break;
                default:

                    break;
            }

            drawerMenu.closeDrawer(Gravity.LEFT,true);
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Vui lòng nhấn 2 lần để thoát.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
