package ptit.ntnt.ptitapp.ForgotPassword;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ptit.ntnt.ptitapp.R;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS2 extends DialogFragment{
    EditText vrf_code;
    Button back;
    Button cont;
    String veriCode;
    String mssv;
    TextView errorMess;
    int count = 5;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Bundle mArgs = getArguments();
        veriCode = mArgs.getString("veriCode");
        mssv = mArgs.getString("mssv");
        dialog.setContentView(R.layout.dialog_recover_pass_step2);
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
        vrf_code = view.findViewById(R.id.edt_verify_code);
        errorMess = view.findViewById(R.id.tv_step2_warning);
        cont = view.findViewById(R.id.btn_continue_step2);
        back = view.findViewById(R.id.btn_return_step2);

        vrf_code.addTextChangedListener(new TextWatcher() {
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
            @Override
            public void onClick(View view) {
                String edtVeriCode = vrf_code.getText().toString().trim();
                if(count>0){
                    if(!edtVeriCode.equalsIgnoreCase("")){
                        if(edtVeriCode.equals(veriCode)){
                            PassRecoverS3 step3 = new PassRecoverS3();
                            Bundle args = new Bundle();
                            args.putString("mssv", mssv);
                            step3.setArguments(args);
                            step3.show(getFragmentManager(),"rec_pass_3");
                        }else {
                            vrf_code.setText("");
                            errorMess.setText("Mã sai mời nhập lại");
                            count--;
                        }
                    }else {
                        errorMess.setText("Mời nhập mã xác nhận!!");
                    }
                }else{
                    errorMess.setText("Bạn đã nhập sai quá số lần quy định! Bye");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            getDialog().dismiss();
                        }
                    }, 1000);
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
}
