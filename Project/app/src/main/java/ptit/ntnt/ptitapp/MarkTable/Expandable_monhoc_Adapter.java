package ptit.ntnt.ptitapp.MarkTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.Models.Subject;
import ptit.ntnt.ptitapp.R;

/**
 * Created by locnq on 3/30/2018.
 */

public class Expandable_monhoc_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Subject> listMonhoc;
    private HashMap<Subject, List<Mark>> listchitietmonhoc;
//    private FirebaseHelper db = new FirebaseHelper();

    public Expandable_monhoc_Adapter() {
    }

    public Expandable_monhoc_Adapter(Context context, List<Subject> listMonhoc, HashMap<Subject, List<Mark>> listchitietmonhoc) {
        this.context = context;
        this.listMonhoc = listMonhoc;
        this.listchitietmonhoc = listchitietmonhoc;
    }

    @Override
    public int getGroupCount() {
        return listMonhoc.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listchitietmonhoc.get(listMonhoc.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listMonhoc.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listchitietmonhoc.get(listMonhoc.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertview, ViewGroup viewGroup) {
        Subject monhoc = (Subject) getGroup(groupPosition);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.mark_monhoc_header, null);

        TextView tvmonhoc = convertview.findViewById(R.id.tvtenmonhoc);
        tvmonhoc.setText(monhoc.getName());
        return convertview;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertview, ViewGroup viewGroup) {

        Subject monhoc = (Subject) getGroup(groupPosition);
//        db.getSubject(String.valueOf(groupPosition));
        Mark item = (Mark) getChild(groupPosition, childPosition);
//        db.getMark(String.valueOf(childPosition));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.mark_chitiet_monhoc, null);

//        TextView tvtenmonhoc = convertview.findViewById(R.id.tvtenmonhoc);
        TextView tvchuyencan = convertview.findViewById(R.id.tvchuyencan);
        TextView tvktgiuaky = convertview.findViewById(R.id.tvktgiuaky);
        TextView tvthilan1 = convertview.findViewById(R.id.tvthilan1);
        TextView tvdiemtbtichluyhe10 = convertview.findViewById(R.id.tvdiemtbhe10monhoc);
        TextView tvdiemtbtichluyhe4 = convertview.findViewById(R.id.tvdiemtbhe4monhoc);
        TextView tvsotinchi = convertview.findViewById(R.id.tvsotinchi);
        TextView tvkq = convertview.findViewById(R.id.tvkq);

        tvchuyencan.setText(item.getCC());
        tvktgiuaky.setText(item.getKT());
        tvthilan1.setText(item.getThi());
        tvdiemtbtichluyhe10.setText(item.getTK());
        tvdiemtbtichluyhe4.setText(item.getTK4());
        tvsotinchi.setText(monhoc.getSoTC());
        tvkq.setText(item.getKQ());

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
