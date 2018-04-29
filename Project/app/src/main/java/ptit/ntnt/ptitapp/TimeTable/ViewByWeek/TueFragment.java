package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptit.ntnt.ptitapp.R;

public class TueFragment extends Fragment {
    public TueFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mon_layout, container, false);
    }
}
