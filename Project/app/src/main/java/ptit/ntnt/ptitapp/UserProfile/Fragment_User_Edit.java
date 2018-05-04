package ptit.ntnt.ptitapp.UserProfile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;

public class Fragment_User_Edit extends android.support.v4.app.Fragment {
    View view;
    EditText edtName,edtEmail,edtClass;
    TextView txtMSSV;
    Button btnEdit;
    ImageView imgInfo;
    DatabaseReference mData;
    Student student;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_user, container, false);
        setControl();
        getData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                mData.child(DBConst.TB_STUDENT.TB_NAME).child(MyApplication.currentStudent.getStudentID()).setValue(student);
                Toast.makeText(getActivity(), "UPDATE Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void setControl(){
        edtName = (EditText) view.findViewById(R.id.edt_HoTen);
        edtEmail = (EditText) view.findViewById(R.id.edt_Email);
        edtClass = (EditText) view.findViewById(R.id.edt_Lop);
        txtMSSV = (TextView) view.findViewById(R.id.edt_MSSV);
        btnEdit = (Button) view.findViewById(R.id.btn_update);
        imgInfo = (ImageView) view.findViewById(R.id.img_info);
        student = MyApplication.currentStudent;
        mData =  FirebaseDatabase.getInstance().getReference();

    }
    private void getData ()
    {
        edtName.setText(MyApplication.currentStudent.getFullName().toString());
        txtMSSV.setText(MyApplication.currentStudent.getStudentID().toString());
        edtEmail.setText(MyApplication.currentStudent.getEmail().toString());
        edtClass.setText(MyApplication.currentStudent.getClassID().toString());
    }
    private void setData()
    {
        student.setFullName(edtName.getText().toString());
       // student.setStudentID(edtMSSV.getText().toString());
        student.setClassID(edtClass.getText().toString());
        student.setEmail(edtEmail.getText().toString());

    }
}
