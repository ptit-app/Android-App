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
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;

import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.R;

import static ptit.ntnt.ptitapp.MyApplication.listAllLecturer;

public class FragmentTeacherList extends Fragment {
    ListView lvTeacher;
    TeacherListAdapter adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_teacherlist,container,false);
        lvTeacher = (ListView) view.findViewById(R.id.lv_TeacherRating);
        adapter = new TeacherListAdapter(getActivity(), R.layout.teacherrating_row, listAllLecturer);

        lvTeacher.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Collections.sort(listAllLecturer);
        lvTeacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChangeFragment(listAllLecturer.get(position));
            }
        });
        return view;
    }


    public void ChangeFragment(Lecturer l){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTeacherInfo fragmentTeacherInfo = new FragmentTeacherInfo();

        Bundle bundle = new Bundle();
        bundle.putSerializable("lectureInfo", l);
        fragmentTeacherInfo.setArguments(bundle);

        fragmentTransaction.replace(R.id.Content, fragmentTeacherInfo);
        fragmentTransaction.commit();
    }
}
