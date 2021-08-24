package com.example.finalassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DayWeather extends AppCompatActivity {

    TextView date,location,temperature,description,humidity;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_weather);

        data dat = new data();

        date = (TextView) findViewById(R.id.day_weather_date);
        location = (TextView) findViewById(R.id.day_weather_location);
        temperature = (TextView) findViewById(R.id.day_weather_temp);
        description = (TextView) findViewById(R.id.day_weather_desc);
        humidity = (TextView) findViewById(R.id.day_weather_humidity);
        image = (ImageView) findViewById(R.id.day_weather_image);

        date.setText(getIntent().getStringExtra("date"));
        location.setText(data.city);
        temperature.setText(getIntent().getStringExtra("temperature"));
        description.setText(getIntent().getStringExtra("description"));
        humidity.setText(getIntent().getStringExtra("humidity"));
        image.setImageResource(Integer.parseInt(getIntent().getStringExtra("image")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

