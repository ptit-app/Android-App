package ptit.ntnt.ptitapp.Fee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;
import ptit.ntnt.ptitapp.RegisteredSubjects.Adapter;

public class SubjectFeeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Subject> subjectsList;

    public SubjectFeeAdapter(Context context, int layout, List<Subject> subjectsList) {
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
        TextView tvTenMonHoc, tvSoTinChi, tvHocPhi,tvIDMonHoc;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectFeeAdapter.ViewHolder holder;
        if(convertView == null){

            holder = new SubjectFeeAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fee_row,parent,false);
            holder.tvHocPhi = (TextView) convertView.findViewById(R.id.tv_HocPhi);
            holder.tvTenMonHoc = (TextView) convertView.findViewById(R.id.tv_tenMonHoc);
            holder.tvSoTinChi = (TextView) convertView.findViewById(R.id.tv_soTinChi);
            convertView.setTag(holder);
        }
        else {
            holder = (SubjectFeeAdapter.ViewHolder) convertView.getTag();
        }

        Subject subject = subjectsList.get(position);
        holder.tvHocPhi.setText(String.valueOf(subject.getSoTC()*390000));
        holder.tvTenMonHoc.setText(subject.getSubjectName());
        holder.tvSoTinChi.setText(String.valueOf(subject.getSoTC()));
        return convertView;
    }
}

