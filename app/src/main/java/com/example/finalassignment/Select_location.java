package com.example.finalassignment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Select_location extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_location);

        listView = (ListView) findViewById(R.id.locationList);

        ArrayList<locate> locates = new ArrayList<locate>();

//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));
//        locations.add(new Location("Colombo","LK",6.9319,79.8478));

        try {
            JSONArray location_array = new JSONArray(getIntent().getStringExtra("locations"));
            for (int i = 0;i<location_array.length();i++){
                JSONObject loc = location_array.getJSONObject(i);
                locates.add(new locate(loc.getString("name"),loc.getString("country"),loc.getDouble("lat"),loc.getDouble("lon")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LocationAdapter adapter = new LocationAdapter(this,R.layout.location_row,locates);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data dat = new data();
                locate loc_data = locates.get(position);

                dat.city = loc_data.city+", "+loc_data.country;
                dat.lat = loc_data.lat;
                dat.lon = loc_data.lon;

                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}