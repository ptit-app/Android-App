package ptit.ntnt.ptitapp.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;

public class Fragment_User_Info extends Fragment {
    View view;
    TextView txtName,txtEmail,txtMSSV,txtClass;
    Button btnEdit;
    ImageView imgInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_info, container, false);
        setControl();
        getData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment();
            }
        });
        return view;
    }
    public void setControl(){
        txtName = (TextView) view.findViewById(R.id.txt_HoTen);
        txtEmail = (TextView) view.findViewById(R.id.txt_Email);
        txtClass = (TextView) view.findViewById(R.id.txt_Lop);
        txtMSSV = (TextView) view.findViewById(R.id.txt_MSSV);
        btnEdit = (Button) view.findViewById(R.id.btn_edit);
        imgInfo = (ImageView) view.findViewById(R.id.img_info);

    }
    private void getData ()
    {
        txtName.setText(MyApplication.currentStudent.getFullName().toString());
        txtMSSV.setText(MyApplication.currentStudent.getStudentID().toString());
        txtEmail.setText(MyApplication.currentStudent.getEmail().toString());
        txtClass.setText(MyApplication.currentStudent.getClassID().toString());
    }
    public void ChangeFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_User_Edit fragmentTeacheredit = new    Fragment_User_Edit();



        fragmentTransaction.replace(R.id.LinerContent, fragmentTeacheredit);
        fragmentTransaction.commit();
    }
}
