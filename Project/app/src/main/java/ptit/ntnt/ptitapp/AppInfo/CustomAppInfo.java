package ptit.ntnt.ptitapp.AppInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import ptit.ntnt.ptitapp.R;

public class CustomAppInfo extends BaseExpandableListAdapter {
    Context context;
    List<String> listAppInfoHeader;
    HashMap<String,List<String>> listAppInfoChild;

    public CustomAppInfo(Context context, List<String> listAppInfoHeader, HashMap<String, List<String>> listAppInfoChild) {
        this.context = context;
        this.listAppInfoHeader = listAppInfoHeader;
        this.listAppInfoChild = listAppInfoChild;
    }
    @Override
    public int getGroupCount() {
        return listAppInfoHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPos) {
        return listAppInfoChild.get(listAppInfoHeader.get(groupPos)).size();
    }

    @Override
    public Object getGroup(int groupPos) {
        return listAppInfoHeader.get(groupPos);
    }

    @Override
    public Object getChild(int groupPos, int childPos) {
        return listAppInfoChild.get(listAppInfoHeader.get(groupPos)).get(childPos);
    }

    @Override
    public long getGroupId(int groupPos) {
        return groupPos;
    }

    @Override
    public long getChildId(int groupPos, int childPos) {
        return childPos;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPos, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = getGroup(groupPos).toString();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.appinfo_groupview,null);
        TextView txtHeader = (TextView) convertView.findViewById(R.id.AppinfoHeader);
        txtHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPos, int childPos, boolean b, View convertView, ViewGroup viewGroup) {
        String item = getChild(groupPos,childPos).toString();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.appinfo_childview,null);
        TextView txtItem = (TextView) convertView.findViewById(R.id.AppinfoChild);
        txtItem.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPos, int childPos) {
        return false;
    }
}
