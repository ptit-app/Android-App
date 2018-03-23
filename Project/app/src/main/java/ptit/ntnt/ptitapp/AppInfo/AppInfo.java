package ptit.ntnt.ptitapp.AppInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class AppInfo extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.app_info_layout,container,false);
        return view;
    }
}