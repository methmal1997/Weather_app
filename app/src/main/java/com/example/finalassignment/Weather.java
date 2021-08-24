package com.example.finalassignment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    String climate;
    String description;
    double temperature;
    int humidity;
    int date;


    public Weather(String climate, String description, double temperature, int humidity, int date) {
        this.climate = climate;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
    }


    public String getTemperature() {
        data dat = new data();
        String temp;
        if (dat.unit == "celsius"){
            temp = String.format("%.2f",(this.temperature-273.15))+" °C";
        }else {
            temp = String.format("%.2f",((this.temperature-273.15) * 9/5 ))+" F";
        }
        return temp;
    }

    public Date getDate(){
        Date date = new Date();
        date.setTime((long)this.date*1000);

        return date;
    }

    public String getFormatedDate(){
        Date date = this.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);
        return strDate;
    }

    public String getDayOfWeek(){
        Date date = this.getDate();
        Date today = new Date();

        String day;

        switch (date.getDay()){
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
            default:
                day = "Sunday";

        }
        if (date.getDate() == today.getDate()){
            day+=" (TODAY)";
        }
        return day;
    }

    public String getHumidity(){
        return String.valueOf(this.humidity)+"%";
    }

    public int getWeatherImage(){
        switch (this.climate){
            case "Clouds":
                return (R.drawable.clouds);
            case "Rain":
                return (R.drawable.rain);
            case "Clear":
                return (R.drawable.clear_day);
            case "Snow":
                return (R.drawable.snow);
            default:
                return (R.drawable.clear_day);
        }
    }

}
