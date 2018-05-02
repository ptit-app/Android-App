package ptit.ntnt.ptitapp.TeacherRating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.R;

public class TeacherRating extends Fragment {
    View view;

    Button btnChangeFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.teacher_rating,container,false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTeacherList fragmentTeacherList = new FragmentTeacherList();

        fragmentTransaction.add(R.id.Content, fragmentTeacherList);
        fragmentTransaction.commit();

        btnChangeFragment = (Button) view.findViewById(R.id.btn_fragment);
        btnChangeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment();
            }
        });
        return view;
    }

    public void AddFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTeacherInfo fragmentTeacherInfo = new FragmentTeacherInfo();

        fragmentTransaction.replace(R.id.Content, fragmentTeacherInfo);
        fragmentTransaction.commit();
    }
}
