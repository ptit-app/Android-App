package ptit.ntnt.ptitapp.TeacherRating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.R;

public class FragmentTeacherList extends Fragment {
    ArrayList<Teacher> arrTeacher;
    ListView lvTeacher;
    TeacherListAdapter adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_teacherlist,container,false);
        lvTeacher = (ListView) view.findViewById(R.id.lv_TeacherRating);
        arrTeacher = new ArrayList<>();
        adapter = new TeacherListAdapter(getActivity(), R.layout.teacherrating_row, arrTeacher);

        Teacher t1 = new Teacher("Duy", "123", "duy@gmail.com", "Tien si", "Truong Khoa", 2000);
        Teacher t2 = new Teacher("Duy", "123", "duy@gmail.com", "Tien si", "Truong Khoa", 2000);
        Teacher t3 = new Teacher("Duy", "123", "duy@gmail.com", "Tien si", "Truong Khoa", 2000);
        arrTeacher.add(t1);
        arrTeacher.add(t2);
        arrTeacher.add(t3);
        lvTeacher.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
