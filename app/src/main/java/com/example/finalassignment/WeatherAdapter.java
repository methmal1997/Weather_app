package com.example.finalassignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather> {

    private Context mContext;
    private int mResource;

    public WeatherAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Weather> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView climate = convertView.findViewById(R.id.climate);
        TextView temp = convertView.findViewById(R.id.temp);
        TextView day = convertView.findViewById(R.id.day);
        ImageView image = convertView.findViewById(R.id.img);

        climate.setText(getItem(position).climate);
        day.setText(getItem(position).getDayOfWeek());
        temp.setText(getItem(position).getTemperature());
      image.setImageResource(getItem(position).getWeatherImage());

        return convertView;
    }
}
