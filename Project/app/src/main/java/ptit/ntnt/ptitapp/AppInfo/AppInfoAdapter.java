package ptit.ntnt.ptitapp.AppInfo;

import android.support.v4.app.FragmentStatePagerAdapter;

import ptit.ntnt.ptitapp.TestSchedule.TestSchedule;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class AppInfoAdapter extends FragmentStatePagerAdapter{
        public AppInfoAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return new AppInfo();
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