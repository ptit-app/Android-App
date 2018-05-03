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
import android.widget.Toast;

import java.util.List;

import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.R;

public class TeacherListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Lecturer> teacherList; //List để chứa các list gv


    public TeacherListAdapter(Context context, int layout, List<Lecturer> teacherList) {
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
        TextView ratingCount;
        RatingBar ratingStar;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.teacherrating_row,viewGroup,false);
            holder.txtName = (TextView) view.findViewById(R.id.txt_GVName);
            holder.txtID = (TextView) view.findViewById(R.id.txt_GVId);
            holder.ratingStar = (RatingBar) view.findViewById(R.id.rtb_GVRating);
            holder.ratingCount = (TextView) view.findViewById(R.id.txt_countRate);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Lecturer teacher = teacherList.get(i);

        holder.txtName.setText("Tên GV: " + teacher.getFullName());
        holder.txtID.setText("Mã GV: " + teacher.getId());
        holder.ratingStar.setRating(teacher.getRating());
        holder.ratingCount.setText("Lượt đánh giá: " + String.valueOf(teacher.getRatingCount()));

        return view;
    }
}
