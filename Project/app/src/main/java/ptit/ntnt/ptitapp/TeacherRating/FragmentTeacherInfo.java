package ptit.ntnt.ptitapp.TeacherRating;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import ptit.ntnt.ptitapp.MainActivity;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.Tools;

public class FragmentTeacherInfo extends Fragment {
    TextView txtTenGV, txtMaGV, txtHocVi, txtEmail, txtChucVu, txtWebsite;
    RatingBar ratingBarDetail;
    Button btnSubmit;
    View view;
    DatabaseReference mData, mData2, mData3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacheroverview,container,false);
        Bundle bundle = getArguments();
        final Lecturer t = (Lecturer) bundle.getSerializable("lectureInfo");
        AnhXa();
        SetInfo(t);
        ratingBarDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
              btnSubmit.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Tools.addRate(MyApplication.currentStudent.getStudentID().toString(),t.getId(),(int) rating);
                      Toast.makeText(getActivity(), "Submit thành công", Toast.LENGTH_SHORT).show();
                  }
              });
            }

        });


        return view;
    }

    public void SetInfo(Lecturer gV){
        txtTenGV.setText("Tên GV: " + gV.getFullName());
        txtMaGV.setText("ID GV: " + gV.getId());
        txtHocVi.setText("Học vị: " + gV.getDegree());
        txtEmail.setText("Email: " + gV.getEmail());
        txtChucVu.setText("Chức vụ: " + gV.getPosition());
        txtWebsite.setText("Website: " + gV.getWebsite());
    }

    private void AnhXa(){
        txtTenGV = (TextView) view.findViewById(R.id.txt_tenGV);
        txtMaGV = (TextView) view.findViewById(R.id.txt_maGV);
        txtHocVi = (TextView) view.findViewById(R.id.txt_hocviGV);
        txtEmail = (TextView) view.findViewById(R.id.txt_emailGV);
        txtChucVu = (TextView) view.findViewById(R.id.txt_chucvuGV);
        txtWebsite = (TextView) view.findViewById(R.id.txt_websiteGV);
        ratingBarDetail=(RatingBar) view.findViewById(R.id.rtb_GVRatingDeTail);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
    }
}
