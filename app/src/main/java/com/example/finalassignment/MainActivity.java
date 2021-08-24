package com.example.finalassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Weather> weathers = new ArrayList<Weather>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mQueue = Volley.newRequestQueue(this);
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617559200));
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617645600));
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617732000));
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617818400));
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617645600));
//        weathers.add(new Weather("Clouds","Crowded Clouds",300,70,1617645600));

        setAdapter();

        getWeatherData();


    }

    public void getWeatherData(){
        data dat = new data();

       String url = "https://api.openweathermap.org/data/2.5/onecall?lat="+dat.lat.toString()+"&lon="+dat.lon.toString()+"&exclude=current,minutely,hourly,alerts&appid=9234f9ad8190e6614fa753bdcf4c5efb";


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray w = response.getJSONArray("daily");
                            for (int i = 0;i<w.length();i++){
                                JSONObject weather = w.getJSONObject(i);
                                int time = weather.getInt("dt");
                                int humidity = weather.getInt("humidity");
                                JSONObject temps = weather.getJSONObject("temp");
                                Double temperature = temps.getDouble("day");
                                JSONArray weather_list = weather.getJSONArray("weather");
                                JSONObject weather_list_obj = weather_list.getJSONObject(0);
                                String climate = weather_list_obj.getString("main");
                                String description = weather_list_obj.getString("description");
                                weathers.add(new Weather(climate,description,temperature,humidity,	time));

                            }
                            setAdapter();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        mQueue.add(request);
    }

    public void setAdapter(){
        listView = (ListView) findViewById(R.id.listView);

        WeatherAdapter weatherAdapter=new WeatherAdapter(this,R.layout.list_view,weathers);

        listView.setAdapter(weatherAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(),DayWeather.class);
                intent.putExtra("date",weathers.get(position).getFormatedDate());
                intent.putExtra("description",weathers.get(position).description);
                intent.putExtra("temperature",weathers.get(position).getTemperature());
                intent.putExtra("humidity",weathers.get(position).getHumidity());
                intent.putExtra("image",String.valueOf(weathers.get(position).getWeatherImage()));
                startActivity(intent);

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent settings = new Intent(this, Setter.class);
                startActivity(settings);
                return true;

            case R.id.about:
                Intent about = new Intent(this,about.class);
                startActivity(about);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}