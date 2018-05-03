package ptit.ntnt.ptitapp.TeacherRating;

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

import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.R;

public class FragmentTeacherInfo extends Fragment {
    TextView txtTenGV, txtMaGV, txtHocVi, txtEmail, txtChucVu, txtWebsite;
    RatingBar ratingBarDetail;
    Button btnSubmit;
    Float star;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacheroverview,container,false);
        Bundle bundle = getArguments();
        Lecturer t = (Lecturer) bundle.getSerializable("lectureInfo");
        AnhXa();
        SetInfo(t);
        final float star;
        ratingBarDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
          btnSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  
              }
          });
//                switch ((int) ratingBar.getRating()) {
//                    case 1:
//                        btnSubmit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                        break;
//                    case 2:
//
//                        break;
//                    case 3:
//
//                        break;
//                    case 4:
//
//                        break;
//                    case 5:
//
//                        break;
//                    default:
//
//                }
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
