package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

import ptit.ntnt.ptitapp.R;

import static ptit.ntnt.ptitapp.Tools.getMapCourse;

public class MonFragment extends Fragment{

    private DatabaseReference dR;
    private TextView tvBuoi, tvMaMonHoc, tvTenMonHoc, tvPhong;
    public String studentID;
    public MonFragment(){
        //không có gì ở đây :v
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View monView = inflater.inflate(R.layout.mon_layout, container, false);
        dR = FirebaseDatabase.getInstance().getReference();
        tvBuoi = (TextView) monView.findViewById(R.id.tvBuoi);
        tvMaMonHoc = (TextView) monView.findViewById(R.id.tvMaMonHoc);
        tvTenMonHoc = (TextView) monView.findViewById(R.id.tvTenMonHoc);
        tvPhong = (TextView) monView.findViewById(R.id.tvPhong);
        getMapCourse(studentID);
        return monView;
    }

}
