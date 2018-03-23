package ptit.ntnt.ptitapp.TestSchedule;

import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class TestScheduleAdapter extends FragmentStatePagerAdapter {
    public TestScheduleAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new TestSchedule();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
