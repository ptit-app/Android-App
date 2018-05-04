package ptit.ntnt.ptitapp.TestSchedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptit.ntnt.ptitapp.MarkTable.SemesterMark;
import ptit.ntnt.ptitapp.Models.Exam;
import ptit.ntnt.ptitapp.R;

public class ExamAdapter extends ArrayAdapter<ExamSchedule>{
    private Context context;
    private int resoures;
    private ArrayList<ExamSchedule> Objects;

    public ExamAdapter(Context context, int resoures, ArrayList<ExamSchedule> Objects) {
        super(context, resoures,Objects);
        this.context = context;
        this.resoures = resoures;
        this.Objects = Objects;
    }

    @Override

    public int getCount() {
        return Objects.size();
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resoures, parent, false);


        final ExamSchedule examSchedule = Objects.get(position);

        TextView tietBD = (TextView) convertView.findViewById(R.id.tietBD);
        TextView Room = (TextView) convertView.findViewById(R.id.phong);
        TextView ngayThi = (TextView) convertView.findViewById(R.id.ngaythi);
        TextView tenMonHoc = (TextView) convertView.findViewById(R.id.tenMonHoc);

        tietBD.setText(Objects.get(position).TietBD);
        Room.setText(Objects.get(position).Room);
        ngayThi.setText(Objects.get(position).Date);
        tenMonHoc.setText(Objects.get(position).Id);
        return convertView;
    }
}
