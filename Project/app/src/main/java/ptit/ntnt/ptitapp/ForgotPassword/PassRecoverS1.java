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
import android.widget.Toast;

import java.security.SecureRandom;

import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.SendMail;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS1 extends DialogFragment{

    static final String pattern = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&!.=";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( pattern.charAt( rnd.nextInt(pattern.length()) ) );
        return sb.toString();
    }

    EditText mailsv;
    Button cont;
    TextView warning;
//    String shit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        Bundle mArgs = getArguments();
//        shit = mArgs.getString("fuck");
        dialog.setContentView(R.layout.dialog_recover_pass_step1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapping();
    }

    private void mapping(){
        View view = getDialog().getWindow().getDecorView();
        mailsv = view.findViewById(R.id.edt_mailsv);
        cont = view.findViewById(R.id.btn_continue);
        warning = view.findViewById(R.id.tv_step1_warning);

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
                    if(email.contains("@")){
                        String subject = "PTIT App - Verification code for new password";
                        String veriCode = randomString(9);
                        String message = "Hi "+ email +",\nYou have recently requested for new password since you forgot yours.\nHere is your verification code: "+veriCode+"\nPlease input into our app to verify your request.";
                        SendMail sm = new SendMail(getActivity(), email, subject, message);
                        sm.execute();
                        PassRecoverS2 step2 = new PassRecoverS2();
                        Bundle args = new Bundle();
                        args.putString("veriCode", veriCode);
                        step2.setArguments(args);
                        step2.show(getFragmentManager(),"rec_pass_2");
                    }
                    else{
                        warning.setText("Email sai!!");
                    }
                }
                else{
                    warning.setText("Hãy điền email trước khi tiếp tục!!");
                }
            }
        });
    }
}
