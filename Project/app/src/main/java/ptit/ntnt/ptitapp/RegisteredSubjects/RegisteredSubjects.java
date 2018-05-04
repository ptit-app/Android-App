package ptit.ntnt.ptitapp.RegisteredSubjects;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.Tools;

public class RegisteredSubjects extends android.support.v4.app.Fragment {
    View view;
    Adapter adapter;
    ListView lvSubjects;
    ArrayList<Subject> arrSubject = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registered_subjects,container,false);
        lvSubjects = (ListView) view.findViewById(R.id.lv_RegisSubject);

        arrSubject = Tools.getCurrentStudyListSubject();


        Log.d("duy working",arrSubject.toString());
        adapter = new Adapter(getActivity(), R.layout.register_subject_row, arrSubject);

        lvSubjects.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
