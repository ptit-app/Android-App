package ptit.ntnt.ptitapp.TimeTable.ViewByWeek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import ptit.ntnt.ptitapp.R;

public class WeekAdapter extends BaseAdapter{
    private final ArrayList mData;
    public WeekAdapter(Map<String, String> map){
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView tvMaMonHoc, tvBuoi, tvTenMonHoc, tvPhong;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_view_subject_row, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, String> item = getItem(position);

        // TODO replace findViewById by ViewHolder
        //((TextView) result.findViewById(R.id.tvMaMonHoc)).setText(item.getKey());
        //((TextView) result.findViewById(android.R.id.text2)).setText(item.getValue());

        return result;
    }
}
