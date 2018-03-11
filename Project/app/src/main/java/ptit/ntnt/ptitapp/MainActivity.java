package ptit.ntnt.ptitapp;


import android.annotation.SuppressLint;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import ptit.ntnt.ptitapp.CustomClass.*;
import ptit.ntnt.ptitapp.CustomAdapter.*;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerMenu;
    private ImageView bt_open_drawer_menu;

    private ListView drawe_menu_lv;
    private ArrayList<drawerMenuItem> drawe_menu_list_array;
    private drawerMenuAdapter drawer_menu_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createDrawerMenu();
        drawer_menu_adapter = new drawerMenuAdapter(this,R.layout.listview_item_drawer_menu,drawe_menu_list_array);
        drawe_menu_lv.setAdapter(drawer_menu_adapter);
    }


    private void createDrawerMenu() {
        drawe_menu_lv = (ListView) findViewById(R.id.drawer_menu_list);
        drawe_menu_list_array = new ArrayList<>();
        drawe_menu_list_array.add(new drawerMenuItem("Thời khoá biểu",R.drawable.timetable_icon));
        drawe_menu_list_array.add(new drawerMenuItem("Xem điểm",R.drawable.score_icon));
        drawe_menu_list_array.add(new drawerMenuItem("Lịch thi",R.drawable.test_schedule_icon));
        drawe_menu_list_array.add(new drawerMenuItem("Thông tin ứng dụng",R.drawable.info_icon));
        drawe_menu_list_array.add(new drawerMenuItem("Hướng dẫn sử dụng",R.drawable.user_manual_icon));
        drawe_menu_list_array.add(new drawerMenuItem("Góp ý",R.drawable.feedback_icon));
    }

    private void initView() {
        drawerMenu = (DrawerLayout) findViewById(R.id.main_page);
        bt_open_drawer_menu = (ImageView) findViewById(R.id.bt_open_drawer_menu);
        bt_open_drawer_menu.setOnClickListener(this);
    }

    @Override
    public void onClick (View view_object){
        switch (view_object.getId()){
            case R.id.bt_open_drawer_menu:
                drawerMenu.openDrawer(Gravity.LEFT,true);
                break;
        }
    }


}
