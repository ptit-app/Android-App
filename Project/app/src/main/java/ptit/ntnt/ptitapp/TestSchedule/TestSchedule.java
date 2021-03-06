package ptit.ntnt.ptitapp.TestSchedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.Exam;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.Tools;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class TestSchedule extends Fragment {
    View view;
    ExamAdapter adapter;
    ListView lvExam;
    ArrayList<Exam> arrayExam;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test_schedule_layout, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mData = database.getReference();
        lvExam = (ListView) view.findViewById(R.id.examLV);
        arrayExam = new ArrayList<Exam>();
        adapter = new ExamAdapter(getActivity(),R.layout.dong_lich_thi, arrayExam);
        ArrayList<Exam> array = Tools.getCurrentExamOnStudyingCourse();
        arrayExam.clear();
        String month="";
        TextView semester = (TextView) view.findViewById(R.id.hk);
        for(int i=0; i<array.size();i++) {
            if(array.get(i) == null){
                Toast.makeText(getActivity(), "Méo thấy gì", Toast.LENGTH_SHORT).show();
            }else {
                Exam exam = new Exam(array.get(i).getCourseID(),array.get(i).getExamDate(),array.get(i).getExamRoom(),array.get(i).getTietBD());
                arrayExam.add(exam);
            }
        }
        month = array.get(0).getExamDate().substring(3,5);
        if (Integer.parseInt(month)<=6 && Integer.parseInt(month)>=3){
            semester.setText("2");
        }else semester.setText("1");
        lvExam.setAdapter(adapter);
        return view;
    }
}
