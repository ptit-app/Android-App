package ptit.ntnt.ptitapp.TimeTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/18/18.
 */

public class FragmentViewByWeek extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timetable_view_by_week_layout,container,false);
        return view;
    }
}
