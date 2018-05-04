package ptit.ntnt.ptitapp.AppGuide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mrlev on 5/4/2018.
 */

public class AppGuideAdapter extends FragmentStatePagerAdapter {

    public AppGuideAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

        @Override
        public Fragment getItem (int position){
        switch (position) {
            case 0:
                return new FragmentAppGuide1();
            case 1:
                return new FragmentAppGuide2();
            case 2:
                return new FragmentAppGuide3();
            case 3:
                return new FragmentAppGuide4();
            case 4:
                return new FragmentAppGuide5();
            case 5:
                return new FragmentAppGuide6();
        }
        return new FragmentAppGuide6();
    }

        @Override
        public int getCount () {
        return 6;
    }

        @Override
        public int getItemPosition (Object object){
        return POSITION_NONE;
    }

        @Override
        public CharSequence getPageTitle ( int position){
        switch (position) {
            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "3";
            case 3:
                return "4";
            case 4:
                return "5";
            case 5:
                return "6";
        }
        return "";
    }
    }
