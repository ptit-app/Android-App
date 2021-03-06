package ptit.ntnt.ptitapp.MarkTable;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.Models.Mark;
import ptit.ntnt.ptitapp.R;

/**
 * Created by locnq on 3/30/2018.
 */

public class Expandable_monhoc_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Mark> listMonhoc;
    private HashMap<Mark, List<Mark>> listchitietmonhoc;

    public Expandable_monhoc_Adapter() {
    }

    public Expandable_monhoc_Adapter(Context context, List<Mark> listMonhoc, HashMap<Mark, List<Mark>> listchitietmonhoc) {
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
        Mark monhoc = (Mark) getGroup(groupPosition);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.mark_monhoc_header, null);

        TextView tvmonhoc = convertview.findViewById(R.id.tvtenmonhoc);
        TextView tvTKChu = convertview.findViewById(R.id.tvTKChu);
        tvmonhoc.setText(monhoc.getSubjectName());
        tvTKChu.setText(monhoc.getTKCHU());

        return convertview;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertview, ViewGroup viewGroup) {

        Mark monhoc = (Mark) getGroup(groupPosition);
        Mark item = (Mark) getChild(groupPosition, childPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.mark_chitiet_monhoc, null);

        TextView tvphantramcc = convertview.findViewById(R.id.tvphantramcc);
        TextView tvphantramth = convertview.findViewById(R.id.tvphantramth);
        TextView tvphantrambt = convertview.findViewById(R.id.tvphantrambt);
        TextView tvphantramkt = convertview.findViewById(R.id.tvphantramkt);
        TextView tvphantramthi = convertview.findViewById(R.id.tvphantramthi);
        TextView tvchuyencan = convertview.findViewById(R.id.tvchuyencan);
        TextView tvthuchanh = convertview.findViewById(R.id.tvthuchanh);
        TextView tvbaitap = convertview.findViewById(R.id.tvbaitap);
        TextView tvktgiuaky = convertview.findViewById(R.id.tvktgiuaky);
        TextView tvthilan1 = convertview.findViewById(R.id.tvthilan1);
        TextView tvdiemtbtichluyhe10 = convertview.findViewById(R.id.tvdiemtbhe10monhoc);
        TextView tvdiemtbtichluyhe4 = convertview.findViewById(R.id.tvdiemtbhe4monhoc);
        TextView tvsotinchi = convertview.findViewById(R.id.tvsotinchi);
        TextView tvkq = convertview.findViewById(R.id.tvkq);

        tvphantramcc.setText(String.valueOf(item.getCC_Percentage()));
        tvphantramth.setText(String.valueOf(item.getTH_Percentage()));
        tvphantrambt.setText(String.valueOf(item.getBT_Percentage()));
        tvphantramkt.setText(String.valueOf(item.getKT_Percentage()));
        tvphantramthi.setText(String.valueOf(item.getTHI_Percentage()));
        tvchuyencan.setText(String.valueOf(item.getCC()));
        tvthuchanh.setText(String.valueOf(item.getTH()));
        tvbaitap.setText(String.valueOf(item.getBT()));
        tvktgiuaky.setText(String.valueOf(item.getKT()));
        tvthilan1.setText(String.valueOf(item.getTHI()));
        tvdiemtbtichluyhe10.setText(String.valueOf(item.getTK()));
        tvdiemtbtichluyhe4.setText(String.valueOf(item.getTK4()));
        tvsotinchi.setText(String.valueOf(monhoc.getSoTC()));
        tvkq.setText(item.getKQ());

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
