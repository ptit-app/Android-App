package ptit.ntnt.ptitapp.MarkTable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.R;

/**
 * Created by vdkhoa on 3/23/18.
 */

public class MarkTable extends android.support.v4.app.Fragment {
    private View view;
    private Spinner spinnerhocky, spinnernamhoc;

    private TextView tvdiemtbhe10, tvdiemtbhe4, tvphanloaihocluc, tvphanloaitenluyen, tvdiemtbtichluyhe10, tvdiemtbtichluyhe4, tvsotinchitichluy, tvbangdiemchitiet;

    private ExpandableListView expandableListView;
    private List<Mark> listMonhoc;
    private HashMap<Mark, List<Mark>> listchitietmonhoc;
    private Expandable_monhoc_Adapter expandable_monhoc_adapter;

    private DatabaseReference database;
    private DatabaseReference mdata;
    String namhoc;
    String hocky;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mark_table_layout, container, false);
        init();

        database = FirebaseDatabase.getInstance().getReference();
        mdata = database.child("TB_MARK").child("N14DCAT069");
        getdataspinner();
        addControl();

        return view;
    }

    private void addControl() {
        spinnernamhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                namhoc = spinnernamhoc.getSelectedItem().toString();
                mdata.child(namhoc).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<String> arrayhocky = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String hocky = ds.getKey();
                            Log.d("reset hocky ", hocky);
                            arrayhocky.add(hocky);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MarkTable.this.getActivity(), R.layout.mark_spinner_center_item, arrayhocky);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerhocky.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                spinnerhocky.setSelection(0);
                hocky = spinnerhocky.getSelectedItem().toString();
//                Log.d("hoc ky ", "get "+hocky);
                getData();
                expandable_monhoc_adapter = new Expandable_monhoc_Adapter(MarkTable.this.getActivity(), listMonhoc, listchitietmonhoc);
                expandableListView.setAdapter(expandable_monhoc_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerhocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hocky = spinnerhocky.getSelectedItem().toString();
                getData();
                expandable_monhoc_adapter = new Expandable_monhoc_Adapter(MarkTable.this.getActivity(), listMonhoc, listchitietmonhoc);
                expandableListView.setAdapter(expandable_monhoc_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getdataspinner() {
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> arraynamhoc = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nam = ds.getKey();
                    Log.d("year ", nam);
                    arraynamhoc.add(nam);
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MarkTable.this.getActivity(), R.layout.mark_spinner_center_item, arraynamhoc);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnernamhoc.setAdapter(arrayAdapter);
                namhoc = spinnernamhoc.getSelectedItem().toString();
                mdata.child(namhoc).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<String> arrayhocky = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String hocky = ds.getKey();
                            Log.d("hocky ", hocky);
                            arrayhocky.add(hocky);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MarkTable.this.getActivity(), R.layout.mark_spinner_center_item, arrayhocky);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerhocky.setAdapter(arrayAdapter);
                        hocky = spinnerhocky.getSelectedItem().toString();
//                        addControl();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getData() {
        listMonhoc = new ArrayList<>();
        listchitietmonhoc = new HashMap<>();
        namhoc = spinnernamhoc.getSelectedItem().toString();
        hocky = spinnerhocky.getSelectedItem().toString();
        tvbangdiemchitiet.setText("Bảng điễm chi tiết "+hocky+" năm "+namhoc);
        mdata.child(namhoc).child(hocky).child("Tongket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SemesterMark semesterMark = dataSnapshot.getValue(SemesterMark.class);

                Log.d("Get Data From Firebase", "Get " + semesterMark);
                tvdiemtbhe4.setText(String.valueOf(semesterMark.getTBhe4()));
                tvdiemtbhe10.setText(String.valueOf(semesterMark.getTBhe10()));
                tvdiemtbtichluyhe4.setText(String.valueOf(semesterMark.getTBtichluyhe4()));
                tvdiemtbtichluyhe10.setText(String.valueOf(semesterMark.getTBtichluyhe10()));
                tvphanloaihocluc.setText(semesterMark.getPlhocluc());
                tvphanloaitenluyen.setText(semesterMark.getPlrenluyen());
                tvsotinchitichluy.setText(String.valueOf(semesterMark.getSotinchitichluy()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mdata.child(namhoc).child(hocky).child("Subject").addValueEventListener(new ValueEventListener() {
            int counter = 0;
            List<Mark> childItem;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMonhoc.clear();
                listchitietmonhoc.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Mark mark = ds.getValue(Mark.class);
                    Log.d("Get Data From Firebase", "Get " + mark);
                    childItem = new ArrayList<>();
                    childItem.add(mark);
                    listMonhoc.add(mark);
                    listchitietmonhoc.put(listMonhoc.get(counter), childItem);
                    counter++;
                }
                expandable_monhoc_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        spinnerhocky = (Spinner) view.findViewById(R.id.spinnerHocky);
        spinnernamhoc = (Spinner) view.findViewById(R.id.spinnerNamhoc);

        tvbangdiemchitiet = view.findViewById(R.id.tvbangdiemchitiet);

        tvdiemtbhe10 = (TextView) view.findViewById(R.id.tvdiemtbhe10);
        tvdiemtbhe4 = (TextView) view.findViewById(R.id.tvdiemtbhe4);
        tvphanloaihocluc = (TextView) view.findViewById(R.id.tvphanloaihocluc);
        tvphanloaitenluyen = (TextView) view.findViewById(R.id.tvphanloaitenluyen);
        tvdiemtbtichluyhe10 = (TextView) view.findViewById(R.id.tvdiemtbtichluyhe10);
        tvdiemtbtichluyhe4 = (TextView) view.findViewById(R.id.tvdiemtbtichluyhe4);
        tvsotinchitichluy = (TextView) view.findViewById(R.id.tvsotinchitichluy);

        expandableListView = (ExpandableListView) view.findViewById(R.id.Expandlv);
    }
}
