package com.example.finalassignment;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<locate> {

    private Context mContext;
    private int mResource;

    public LocationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<locate> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView Ccity = convertView.findViewById(R.id.location_city);
        TextView Ccountry = convertView.findViewById(R.id.location_country);
        TextView Clat = convertView.findViewById(R.id.location_lat);
        TextView Clon = convertView.findViewById(R.id.location_lon);

        Ccity.setText(getItem(position).city);
        Ccountry.setText(getItem(position).country);
        Clat.setText(getItem(position).lat.toString());
        Clon.setText(getItem(position).lon.toString());

        return convertView;
    }
}

