package ptit.ntnt.ptitapp.TimeTable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by vdkhoa on 3/16/18.
 */

public class TimeTableAdapter extends FragmentStatePagerAdapter{


    public TimeTableAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentViewByDay();
            case 1:
                return new FragmentViewByWeek();
        }
        return new FragmentViewByDay();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Xem theo ngày";
            case 1:
                return "Xem theo tuần";
        }
        return "";
    }
}
