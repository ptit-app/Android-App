package ptit.ntnt.ptitapp.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ptit.ntnt.ptitapp.CustomClass.*;
import ptit.ntnt.ptitapp.R;

import java.util.List;

/**
 * Created by vdkhoa on 3/10/18.
 */

public class drawerMenuAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<drawerMenuItem> list;

    public drawerMenuAdapter(Context context, int layout, List<drawerMenuItem> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout,null);


        //Anh xa
        TextView txtTitle = (TextView) view.findViewById(R.id.drawer_menu_item_title);
        ImageView imgIcon = (ImageView) view.findViewById(R.id.drawer_menu_item_icon);

        //Gan gia tri
        drawerMenuItem listItem = list.get(i);

        txtTitle.setText(listItem.getTitle());
        imgIcon.setImageResource(listItem.getIcon());
        return view;
    }
}
