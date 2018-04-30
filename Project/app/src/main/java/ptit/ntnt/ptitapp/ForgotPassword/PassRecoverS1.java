package ptit.ntnt.ptitapp.ForgotPassword;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.SendMail;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS1 extends DialogFragment{

    static final String randomPattern = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&!.=";
    static SecureRandom rnd = new SecureRandom();
    static final String regex = "^[A-Za-z0-9]+@(.+)$";
    static Pattern pattern = Pattern.compile(regex);

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( randomPattern.charAt( rnd.nextInt(randomPattern.length()) ) );
        return sb.toString();
    }

    boolean isEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    EditText mailsv;
    Button cont;
    TextView warning;
    DatabaseReference fbData;
    ArrayList<Student> studentArr;
    ArrayList<String> key;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_recover_pass_step1);
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
        fbData = FirebaseDatabase.getInstance().getReference();
        mailsv = view.findViewById(R.id.edt_mailsv);
        cont = view.findViewById(R.id.btn_continue);
        warning = view.findViewById(R.id.tv_step1_warning);
        studentArr = new ArrayList<>();
        key = new ArrayList<>();

        fbData.child(DBConst.TB_STUDENT.TB_NAME).addChildEventListener(new ChildEventListener() {

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
        mailsv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                warning.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mailsv.getText().toString().trim();
                if(!email.equalsIgnoreCase("")){
                    if(isEmail(email)){
                        String username = email.split("[@._]")[0];
                        Student student = studentArr.get(key.indexOf(username.toUpperCase()));
                        if(student!=null){
                            String subject = "PTIT App - Verification code for new password";
                            String veriCode = randomString(9);
                            String message = "Hi "+ email +",\nYou have recently requested for new password since you forgot yours.\nHere is your verification code: "+veriCode+"\nPlease input into our app to verify your request.";
                            SendMail sm = new SendMail(getActivity(), email, subject, message);
                            sm.execute();
                            PassRecoverS2 step2 = new PassRecoverS2();
                            Bundle args = new Bundle();
                            args.putString("veriCode", veriCode);
                            args.putString("mssv", username);
                            step2.setArguments(args);
                            step2.show(getFragmentManager(),"rec_pass_2");
                        }else {
                            warning.setText("Email không tồn tại!!");
                            mailsv.setText("");
                        }
                    }
                    else{
                        warning.setText("Đây không phải là email!!");
                    }
                }
                else{
                    warning.setText("Hãy điền email trước khi tiếp tục!!");
                }
            }
        });
    }
}
