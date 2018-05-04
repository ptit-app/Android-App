package ptit.ntnt.ptitapp.TimeTable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ptit.ntnt.ptitapp.R;

public class SubjectScheduleAdapter extends ArrayAdapter<SubjectSchedule>{
    private Context context;
    private int resoure;
    private List<SubjectSchedule>  arrSubjectScheduleList;

    public SubjectScheduleAdapter(@NonNull Context context, int resource, @NonNull List<SubjectSchedule> arrSubjectScheduleList) {
        super(context, resource, arrSubjectScheduleList);
        this.context = context;
        this.resoure=resource;
        this.arrSubjectScheduleList=arrSubjectScheduleList;
    }


    private class ViewHolder{
        TextView txtMaMonHoc,txtTenMonHoc,txtPhongHoc,txtBuoi;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.timetable_view_subject_row,parent,false);

            holder.txtBuoi = (TextView)    convertView.findViewById(R.id.txtBuoi);
            holder.txtTenMonHoc = (TextView) convertView.findViewById(R.id.txtTenMonHoc);
            holder.txtMaMonHoc=(TextView) convertView.findViewById(R.id.txtMaMonHoc);
            holder.txtPhongHoc = (TextView) convertView.findViewById(R.id.txtPhong);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SubjectSchedule subjectSchedule = arrSubjectScheduleList.get(position);
        holder.txtBuoi.setText(subjectSchedule.buoi);
        holder.txtMaMonHoc.setText(subjectSchedule.subjectID);
        holder.txtTenMonHoc.setText(subjectSchedule.subjectName);
        holder.txtPhongHoc.setText(subjectSchedule.room);
        return convertView;

    }
}
