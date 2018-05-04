package ptit.ntnt.ptitapp.TestSchedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class TestSchedule extends Fragment {
    View view;
    ExamAdapter adapter;
    ListView lvExam;
    ArrayList<ExamSchedule> arrayExam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test_schedule_layout, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mData = database.getReference();
        lvExam = (ListView) view.findViewById(R.id.examLV);
        arrayExam = new ArrayList<ExamSchedule>();
        adapter = new ExamAdapter(getActivity(), R.layout.dong_lich_thi, arrayExam);
        arrayExam.add(new ExamSchedule("1","2B31","3","21/2/2018"));
        arrayExam.add(new ExamSchedule("2","2B31","3","21/2/2018"));
        arrayExam.add(new ExamSchedule("3","2B31","3","21/2/2018"));
        arrayExam.add(new ExamSchedule("4","2B31","3","21/2/2018"));
        arrayExam.add(new ExamSchedule("5","2B31","3","21/2/2018"));
        lvExam.setAdapter(adapter);
        return view;
    }
}
