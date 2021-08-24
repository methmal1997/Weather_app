package com.example.finalassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Setter extends AppCompatActivity implements TemperatureUnitChoiceFragment.SingleChoiceListener,LocationDialog.LocationDialogListener{

    LinearLayout city,unit;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setter);

        city = (LinearLayout) findViewById(R.id.settings_city);
        unit = (LinearLayout) findViewById(R.id.settings_unit);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationDialog locationDialog = new LocationDialog();
                locationDialog.show(getSupportFragmentManager(),"Location Dialog");

            }
        });

        unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TemperatureUnitChoiceFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(getSupportFragmentManager(),"Temperature Choice");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        data dat = new data();
        if(position == 0){
            dat.unit = "celsius";
        }else {
            dat.unit = "farenheit";
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void getLocation(String location)
    {
        getLocationData(location);
    }

    @Override
    public void cancel() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void getLocationData(String location){
        mQueue = Volley.newRequestQueue(this);

        String city = location;
        String url = "https://api.openweathermap.org/geo/1.0/direct?q="+location+"&limit=5&appid=9234f9ad8190e6614fa753bdcf4c5efb" ;


        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,url,null,
                new Response.Listener<JSONArray>(


                ) {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent intent = new Intent(Setter.this, Select_location.class);
                        intent.putExtra("locations", response.toString());
                        startActivity(intent);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }
        );
       mQueue.add(request);

       }

    private void give() {

    }


}