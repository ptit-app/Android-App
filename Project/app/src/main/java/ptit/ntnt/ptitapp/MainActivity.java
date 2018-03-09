package ptit.ntnt.ptitapp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerMenu;
    private ImageView bt_open_drawer_menu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

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
