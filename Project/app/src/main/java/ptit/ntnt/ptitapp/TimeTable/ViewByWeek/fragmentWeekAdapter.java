package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.TimeTable.SubjectSchedule;

public class fragmentWeekAdapter extends ArrayAdapter<SubjectSchedule> {
    private static ArrayList<SubjectSchedule> listSchedule;
    private static ArrayList<Subject> subjects;
    //private LayoutInflater mInflater;
    private int resource;
    private Context context;
    private String studentID;
    public fragmentWeekAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SubjectSchedule> listSchedule){
        super(context,resource,listSchedule);
        this.context = context;
        this.resource = resource;
        this.listSchedule = listSchedule;
    }

    /*@Override
    public int getCount() {
        return listSchedule.size();
    }

    @Override
    public Object getItem(int position) {
        return listSchedule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }*/

    public class ViewHolder{
        TextView tvMaMonHoc, tvBuoi, tvTenMonHoc, tvPhong;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.timetable_view_subject_row,null);
            holder = new ViewHolder();
            holder.tvBuoi = (TextView) convertView.findViewById(R.id.txtBuoi);
            holder.tvMaMonHoc = (TextView)convertView.findViewById(R.id.txtMaMonHoc);
            holder.tvTenMonHoc = (TextView) convertView.findViewById(R.id.txtTenMonHoc);
            holder.tvPhong = (TextView) convertView.findViewById(R.id.txtPhong);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.tvBuoi.setText("Sáng");
        //holder.tvBuoi.setText("Chiều");
        holder.tvMaMonHoc.setText(listSchedule.get(position).getCourseID());
        //holder.tvTenMonHoc.setText(subjects.get(position).getName());
        holder.tvPhong.setText(listSchedule.get(position).getRoom());
        return convertView;
    }
}
