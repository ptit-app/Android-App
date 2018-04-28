package ptit.ntnt.ptitapp.RegisteredSubjects;

import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by vdkhoa on 3/23/18.
 */

public class RegisteredSubjectsApdapter extends FragmentStatePagerAdapter {
    public RegisteredSubjectsApdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new RegisteredSubjects();
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
