package ptit.ntnt.ptitapp.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.R;

/**
 * Created by datshiro on 11/03/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    private int resource;
    private ArrayList<News> objects;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        ImageButton imgbtnFeatureImage = (ImageButton)convertView.findViewById(R.id.imgbtnFeatureImage);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView)convertView.findViewById(R.id.tvContent);

        News news = objects.get(position);

        imgbtnFeatureImage.setImageResource(news.getFeatureImageId());
        tvTitle.setText(news.getTitle());
        tvContent.setText(news.getContent());

        return convertView;
    }

}
