package ptit.ntnt.ptitapp.ForgotPassword;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.R;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS3 extends DialogFragment {
    EditText new_pass;
    EditText pass_confirm;
    Button cont;
    Button back;
    String mssv;
    TextView errorMess;
    DatabaseReference fbData;
    ArrayList<Student> studentArr;
    ArrayList<String> key;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Bundle mArgs = getArguments();
        mssv = mArgs.getString("mssv");
        dialog.setContentView(R.layout.dialog_recover_pass_step3);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapping();
        event();
    }

    private void mapping(){
        View view = getDialog().getWindow().getDecorView();
        new_pass = view.findViewById(R.id.edt_new_pwd);
        pass_confirm = view.findViewById(R.id.edt_confirm_pwd);
        errorMess = view.findViewById(R.id.tv_step3_warning);
        cont = view.findViewById(R.id.btn_change_pwd);
        back = view.findViewById(R.id.btn_return_step3);
        fbData = FirebaseDatabase.getInstance().getReference();
        studentArr = new ArrayList<>();
        key = new ArrayList<>();

        fbData.child(DBConst.TB_STUDENT.TB_NAME).addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                studentArr.add(student);
                key.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void event(){
        new_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errorMess.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pass_confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errorMess.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String newPass = new_pass.getText().toString();
                String confirm = pass_confirm.getText().toString();
                if(!newPass.equalsIgnoreCase("")&&!confirm.equalsIgnoreCase("")){
                    if(newPass.equals(confirm)){
                        Student student = studentArr.get(key.indexOf(mssv.toUpperCase()));
                        student.setPasswd(newPass);
                        setStudent(student);
                        closeAllDialog();
                    }else{
                        errorMess.setText("Mật khẩu không khớp!");
                    }
                }else {
                    errorMess.setText("Hãy nhập đầy đủ trường!");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    public void setStudent (Student student){
        fbData.child(DBConst.TB_STUDENT.TB_NAME).child(student.getStudentID()).setValue(student, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    errorMess.setText("Cập nhật thành công!");
                }else {
                    errorMess.setText("Cập nhật thất bại!");
                }
            }
        });
    }

    private void closeAllDialog(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getDialog().dismiss();
            }
        }, 1500);
        Fragment sec = getFragmentManager().findFragmentByTag("rec_pass_2");
        if (sec != null) {
            DialogFragment df = (DialogFragment) sec;
            df.dismiss();
        }
        Fragment first = getFragmentManager().findFragmentByTag("rec_pass_1");
        if (first != null) {
            DialogFragment df = (DialogFragment) first;
            df.dismiss();
        }
    }
}
