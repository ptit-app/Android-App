package ptit.ntnt.ptitapp.MarkTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class MarkTable extends android.support.v4.app.Fragment {
    private View view;
    private Spinner spinnerhocky, spinnernamhoc;
    private ArrayList<String> arrayhocky;
    private ArrayList<Integer> arraynamhoc;

    private TextView tvdiemtbhe10, tvdiemtbhe4, tvphanloaihocluc, tvphanloaitenluyen, tvdiemtbtichluyhe10, tvdiemtbtichluyhe4, tvsotinchitichluy;

    private ExpandableListView expandableListView;
    private List<Subject> listMonhoc;
    private HashMap<Subject, List<Mark>> listchitietmonhoc;
    private Expandable_monhoc_Adapter expandable_monhoc_adapter;
    private SemesterMarkAdapter semesterMarkAdapter;

    private FirebaseHelper db = new FirebaseHelper();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mark_table_layout, container, false);
        init();

        setSpinnerhocky();
        setSpinnernamhoc();

        addControl();
//        semesterMarkAdapter semesterMarkAdapter = new semesterMarkAdapter(this, R.layout.mark_table_layout, list);
        expandable_monhoc_adapter = new Expandable_monhoc_Adapter(this.getActivity(), listMonhoc, listchitietmonhoc);
        expandableListView.setAdapter(expandable_monhoc_adapter);

//        db.getListSubjects(expandable_monhoc_adapter);

        return view;
    }

    private void show() {

    }

    private void addControl() {
        listMonhoc = new ArrayList<>();
        listchitietmonhoc = new HashMap<Subject, List<Mark>>();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference();
//        firebase.database().ref().set({
//                whatever:'JSON'
//        });
//        myRef.child("kk").setValue(listMonhoc);
//
//        myRef.setValue("Hello, World!");
//        db.getSubject(listchitietmonhoc[0]);
        listMonhoc.add(new Subject("mmh12", "Mật mã học", 3));
        listMonhoc.add(new Subject("ltdd23", "Lập trình di động", 3));
        listMonhoc.add(new Subject("atm34", "An toàn mạng", 3));
        listMonhoc.add(new Subject("hdh45", "Hệ điều hanh", 3));

        List<Mark> mmh12 = new ArrayList<>();
        mmh12.add(new Mark("1", "n14dcat09", "mmh12", 10, 8, 8, 9, "Đạt", 8.7, 3.6,"A", "Gioi", "1", "2018"));
        List<Mark> ltdd23 = new ArrayList<>();
        ltdd23.add(new Mark("2", "n14dcat09", "hdh23", 10, 8, 8, 9, "Đạt", 8.7, 3.6,"A", "Gioi","1", "2018"));
        List<Mark> atm34 = new ArrayList<>();
        atm34.add(new Mark("3", "n14dcat09", "atm34",10, 8, 8, 9, "Đạt", 8.7, 3.6,"A", "Gioi","1", "2018"));
        List<Mark> hdh45 = new ArrayList<>();
        hdh45.add(new Mark("4", "n14dcat09", "ltdd45",10, 8, 8, 9, "Đạt", 8.7, 3.6,"A", "Gioi","1", "2018"));

//        mmh12.add("Giưa kỳ");

        listchitietmonhoc.put(listMonhoc.get(0), mmh12);
        listchitietmonhoc.put(listMonhoc.get(1), ltdd23);
        listchitietmonhoc.put(listMonhoc.get(2), atm34);
        listchitietmonhoc.put(listMonhoc.get(3), hdh45);
    }

    private void init() {
        spinnerhocky = (Spinner) view.findViewById(R.id.spinnerHocky);
        spinnernamhoc = (Spinner) view.findViewById(R.id.spinnerNamhoc);

        tvdiemtbhe10 = (TextView) view.findViewById(R.id.tvdiemtbhe10);
        tvdiemtbhe4 = (TextView) view.findViewById(R.id.tvdiemtbhe4);
        tvphanloaihocluc = (TextView) view.findViewById(R.id.tvphanloaihocluc);
        tvphanloaitenluyen = (TextView) view.findViewById(R.id.tvphanloaitenluyen);
        tvdiemtbtichluyhe10 = (TextView) view.findViewById(R.id.tvdiemtbtichluyhe10);
        tvdiemtbtichluyhe4 = (TextView) view.findViewById(R.id.tvdiemtbtichluyhe4);
        tvsotinchitichluy = (TextView) view.findViewById(R.id.tvsotinchitichluy);

        expandableListView = (ExpandableListView) view.findViewById(R.id.Expandlv);
    }

    private void setSpinnerhocky() {
        arrayhocky = new ArrayList<>();
        arrayhocky.add("Học Kỳ I");
        arrayhocky.add("Học Kỳ II");
        arrayhocky.add("Học Kỳ III");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this.getActivity(), R.layout.mark_spinner_center_item, arrayhocky);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhocky.setAdapter(arrayAdapter);
    }

    private void setSpinnernamhoc() {
        arraynamhoc = new ArrayList<>();
        arraynamhoc.add(2014);
        arraynamhoc.add(2015);
        arraynamhoc.add(2016);
        arraynamhoc.add(2017);
        arraynamhoc.add(2018);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this.getActivity(), R.layout.mark_spinner_center_item, arraynamhoc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernamhoc.setAdapter(arrayAdapter);
    }
}
