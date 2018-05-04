package ptit.ntnt.ptitapp.TimeTable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ptit.ntnt.ptitapp.R;

public class SubjectScheduleAdapter extends ArrayAdapter<SubjectSchedule>{
    private Context context;
    private int resoure;
    private List<SubjectSchedule>  arrSubjectScheduleList;


    final boolean[] isAffterChange = {false};
    final boolean[] isNoteChange={false};

    public SubjectScheduleAdapter(@NonNull Context context, int resource, @NonNull List<SubjectSchedule> arrSubjectScheduleList) {
        super(context, resource, arrSubjectScheduleList);
        this.context = context;
        this.resoure=resource;
        this.arrSubjectScheduleList=arrSubjectScheduleList;
    }


    private class ViewHolder{
        TextView txtMaMonHoc,txtTenMonHoc,txtPhongHoc,txtBuoi;
        EditText txtNote;
        Button btSaveNote;
    }
    @SuppressLint("WrongConstant")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.timetable_view_subject_row,parent,false);

            holder.txtBuoi = (TextView)    convertView.findViewById(R.id.txtBuoi);
            holder.txtTenMonHoc = (TextView) convertView.findViewById(R.id.txtTenMonHoc);
            holder.txtMaMonHoc=(TextView) convertView.findViewById(R.id.txtMaMonHoc);
            holder.txtPhongHoc = (TextView) convertView.findViewById(R.id.txtPhong);
            holder.txtNote = (EditText) convertView.findViewById(R.id.txt_note);
            holder.btSaveNote = (Button) convertView.findViewById(R.id.bt_save_note);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SubjectSchedule subjectSchedule = arrSubjectScheduleList.get(position);
        holder.txtBuoi.setText(subjectSchedule.buoi);
        holder.txtMaMonHoc.setText(subjectSchedule.subjectID);
        holder.txtTenMonHoc.setText(subjectSchedule.subjectName);
        holder.txtPhongHoc.setText(subjectSchedule.room);


        final View finalConvertView = convertView;

        if (!isNoteChange[0]) {
            holder.btSaveNote.setVisibility(View.GONE);
        }

        holder.txtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isAffterChange[0] =false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!isAffterChange[0]){
                    isAffterChange[0] =true;
                    isNoteChange[0]=true;
                    holder.btSaveNote.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.btSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNoteChange[0]=false;
                holder.btSaveNote.setVisibility(View.GONE);
            }
        });

        return convertView;

    }
}
