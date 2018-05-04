package ptit.ntnt.ptitapp.Fee;

import android.support.v4.app.FragmentStatePagerAdapter;

import ptit.ntnt.ptitapp.MainPage.MainPage;

public class FeeAdapter extends FragmentStatePagerAdapter {

    public FeeAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new Fee();
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
