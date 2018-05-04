package ptit.ntnt.ptitapp.UserProfile;

import android.support.v4.app.FragmentStatePagerAdapter;

import ptit.ntnt.ptitapp.TeacherRating.TeacherRating;

public class UserProfileAdapter extends FragmentStatePagerAdapter {

    public UserProfileAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new UserProfile();
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
