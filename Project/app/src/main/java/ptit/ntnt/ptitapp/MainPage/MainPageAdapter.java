package ptit.ntnt.ptitapp.MainPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

/**
 * Created by vdkhoa on 3/18/18.
 */


public class MainPageAdapter extends FragmentStatePagerAdapter{

    public MainPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new MainPage();
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

