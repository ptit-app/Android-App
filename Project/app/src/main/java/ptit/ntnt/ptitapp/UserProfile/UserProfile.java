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
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_User_Info fragment_user_info = new Fragment_User_Info();



        fragmentTransaction.add(R.id.LinerContent, fragment_user_info);
        fragmentTransaction.commit();
        return view;
}
}
