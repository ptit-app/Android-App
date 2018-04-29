package ptit.ntnt.ptitapp.TimeTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.FriFragment;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.MonFragment;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.SatFragment;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.ThuFragment;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.TueFragment;
import ptit.ntnt.ptitapp.TimeTable.ViewByWeek.WedFragment;

/**
 * Created by vdkhoa on 3/18/18.
 */

public class FragmentViewByWeek extends Fragment implements OnItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner spinner;


    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setRetainInstance(true);
    }
    @Override
    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timetable_view_by_week_layout,container,false);
        return view;
    }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timetable_view_by_week_layout,container, false);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        //Click
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.chonTuan, R.layout.custom_spinner);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        return view;

    }

    //AddFragment to tabs
    private void setupViewPager(ViewPager viewPager)
    {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new MonFragment(), "Thứ 2");
        adapter.addFragment(new TueFragment(), "Thứ 3");
        adapter.addFragment(new WedFragment(), "Thứ 4");
        adapter.addFragment(new ThuFragment(), "Thứ 5");
        adapter.addFragment(new FriFragment(), "Thứ 6");
        adapter.addFragment(new SatFragment(), "Thứ 7");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Đã chọn: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    static class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager){
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
