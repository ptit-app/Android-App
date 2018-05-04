package ptit.ntnt.ptitapp.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;

public class UserProfile extends Fragment {
    View view;
    ImageView img_info;
    EditText txt_HoTen, txt_MSSV, txt_Email, txt_Lop;
    Button edit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_profile,container,false);
        setControl();
        getData();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
}
private void setControl ()
    {
        img_info = (ImageView) view.findViewById(R.id.img_info);
        txt_HoTen = (EditText) view.findViewById(R.id.txt_HoTen);
        txt_MSSV = (EditText) view.findViewById(R.id.txt_MSSV);
        txt_Email = (EditText) view.findViewById(R.id.txt_Email);
        txt_Lop = (EditText) view.findViewById(R.id.txt_Lop);
        edit = (Button) view.findViewById(R.id.btn_edit);
    }
    private void getData ()
    {
        txt_HoTen.setText(MyApplication.currentStudent.getFullName().toString());
        txt_MSSV.setText(MyApplication.currentStudent.getStudentID().toString());
        txt_Email.setText(MyApplication.currentStudent.getEmail().toString());
        txt_Lop.setText(MyApplication.currentStudent.getClassID().toString());
    }
}
