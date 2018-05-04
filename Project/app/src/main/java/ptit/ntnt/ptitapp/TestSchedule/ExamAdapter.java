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

import javax.crypto.interfaces.PBEKey;

import ptit.ntnt.ptitapp.MarkTable.SemesterMark;
import ptit.ntnt.ptitapp.Models.Exam;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.MyApplication;
import ptit.ntnt.ptitapp.R;

public class ExamAdapter extends ArrayAdapter<Exam>{
    private Context context;
    private int resoures;
    private ArrayList<Exam> Objects;

    public ExamAdapter(Context context, int resoures, ArrayList<Exam> Objects) {
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


        final Exam examSchedule = Objects.get(position);

        TextView tietBD = (TextView) convertView.findViewById(R.id.tietBD);
        TextView Room = (TextView) convertView.findViewById(R.id.phong);
        TextView ngayThi = (TextView) convertView.findViewById(R.id.ngaythi);
        TextView tenMonHoc = (TextView) convertView.findViewById(R.id.tenMonHoc);

        Subject subject = MyApplication.mapCourseIDToSubject.get(Objects.get(position).getCourseID());

        tietBD.setText(Integer.toString(Objects.get(position).getTietBD()));
        Room.setText(Objects.get(position).getExamRoom());
        ngayThi.setText(Objects.get(position).getExamDate());
        tenMonHoc.setText(subject.getSubjectName());
        return convertView;
    }
}
