package ptit.ntnt.ptitapp.RegisteredSubjects;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Subject> subjectsList;

    public Adapter(Context context, int layout, List<Subject> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.subjectsList = subjectsList;
    }

    @Override
    public int getCount() {
        return subjectsList.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getLength(){
        return subjectsList.size();
    }

    private class ViewHolder {
        TextView tvSTT, tvIDMH, tvTenMH, tvSoTC;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){

            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.register_subject_row,parent,false);
            holder.tvSTT = (TextView) convertView.findViewById(R.id.tv_soTT);
            holder.tvIDMH = (TextView) convertView.findViewById(R.id.tv_maMH);
            holder.tvTenMH = (TextView) convertView.findViewById(R.id.tv_tenMH);
            holder.tvSoTC = (TextView) convertView.findViewById(R.id.tv_soTC);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Subject subject = subjectsList.get(position);
        holder.tvSTT.setText("" + (position + 1));
        holder.tvIDMH.setText(String.valueOf(subject.getSubjectID()));
        holder.tvTenMH.setText(subject.getSubjectName());
        holder.tvSoTC.setText(String.valueOf(subject.getSoTC()));
        return convertView;
    }
}
