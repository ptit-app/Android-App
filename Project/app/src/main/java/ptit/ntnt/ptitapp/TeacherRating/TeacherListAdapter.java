package ptit.ntnt.ptitapp.TeacherRating;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import ptit.ntnt.ptitapp.R;

public class TeacherListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Teacher> teacherList; //List để chứa các list gv

    public TeacherListAdapter(Context context, int layout, List<Teacher> teacherList) {
        this.context = context;
        this.layout = layout;
        this.teacherList = teacherList;
    }

    @Override
    public int getCount() {
        return teacherList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtName;
        TextView txtID;
        RatingBar rtbGBRating;
        TextView countView;
        ImageView imgPic;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.teacherrating_row,viewGroup,false);
            holder.txtName = (TextView) view.findViewById(R.id.txt_GVName);
            holder.txtID = (TextView) view.findViewById(R.id.txt_GVId);
            //holder.rtbGBRating = (RatingBar) view.findViewById(R.id.rtb_GVRating);
            holder.countView = (TextView) view.findViewById(R.id.txt_countRate);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Teacher teacher = teacherList.get(i);

        holder.txtName.setText("Tên GV: " + teacher.getNameGV());
        holder.txtID.setText("Mã GV: " + teacher.getIdGV());
        //holder.rtbGBRating.setText(teacher.getRatingGV());
        holder.countView.setText("Lượt đánh giá: " + String.valueOf(teacher.getCountView()));

        return view;
    }
}
