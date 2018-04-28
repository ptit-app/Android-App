package ptit.ntnt.ptitapp.TeacherRating;

import android.support.v4.app.FragmentStatePagerAdapter;

import ptit.ntnt.ptitapp.TestSchedule.TestSchedule;

public class TeacherRatingAdapter extends FragmentStatePagerAdapter {
    public TeacherRatingAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new TeacherRating();
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
