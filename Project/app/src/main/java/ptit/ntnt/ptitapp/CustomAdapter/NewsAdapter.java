package ptit.ntnt.ptitapp.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ptit.ntnt.ptitapp.Models.News;
import ptit.ntnt.ptitapp.R;

import static android.content.ContentValues.TAG;

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

        ImageView imgViewFeatureImage = (ImageView) convertView.findViewById(R.id.imgViewFeatureImage);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView)convertView.findViewById(R.id.tvContent);
        TextView tvUploadDate = (TextView)convertView.findViewById(R.id.tvUploadDate);
        News news = objects.get(position);

        imgViewFeatureImage.setImageResource(news.getFeatureImageId());
        tvTitle.setText(news.getTitle());
        tvContent.setText(news.getContent());

        if(news.getCreatedAt() != null){
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy - HH:mm");
            String temp = formater.format(news.getCreatedAt());
            tvUploadDate.setText(formater.format(news.getCreatedAt()));
        }

        return convertView;
    }

    @Nullable
    @Override
    public News getItem(int position) {
        return this.objects.get(position);
    }
}
