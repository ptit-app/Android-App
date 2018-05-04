package ptit.ntnt.ptitapp.Fee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.RegisteredSubjects.Adapter;
import ptit.ntnt.ptitapp.Tools;

public class Fee extends Fragment{
    View view;
    SubjectFeeAdapter adapter;
    ListView lvSubjects;
    ArrayList<Subject> arrSubject = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fee,container,false);
        lvSubjects = (ListView) view.findViewById(R.id.lv_SubjectFee);

        arrSubject = Tools.getCurrentStudyListSubject();


        Log.d("duy working",arrSubject.toString());
        adapter = new SubjectFeeAdapter(getActivity(), R.layout.fee_row, arrSubject);

        lvSubjects.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
