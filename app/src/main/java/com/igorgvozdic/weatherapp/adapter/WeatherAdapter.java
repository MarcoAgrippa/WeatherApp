package com.igorgvozdic.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igorgvozdic.weatherapp.R;
import com.igorgvozdic.weatherapp.model.WeatherFeed;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private Context context;
    private List<WeatherFeed> weatherList;

    public WeatherAdapter(Context context, List<WeatherFeed> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        if (weatherList != null) {
            WeatherFeed currentWeatherItem = weatherList.get(position);

            String date = currentWeatherItem.getmDate();
            int currentTemp = (int) currentWeatherItem.getmMain().getmTemp();
            double maxTemp = currentWeatherItem.getmMain().getmMaxTemp();
            double minTemp = currentWeatherItem.getmMain().getmMinTemp();
            int pressure = (int) currentWeatherItem.getmMain().getmPressure();
            double wind = currentWeatherItem.getmWind().getmSpeed();
            String mainWeather = currentWeatherItem.getmWeather().get(0).getmMain();


            holder.txtDateTime.setText(date);
            holder.loadImage(mainWeather);
            holder.txtTemperature.setText(Integer.toString(currentTemp) + "\u2103");
            holder.txtMaxMinTemperature.setText(Double.toString(maxTemp) + "\u2103" + " / " + Double.toString(minTemp) + "\u2103");
            holder.txtPressure.setText(Integer.toString(pressure) + " mbar");
            holder.txtWind.setText(Double.toString(wind) + " m/s");
            holder.txtMainWeather.setText(mainWeather);
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView txtDateTime;
        ImageView imgWeather;
        TextView txtMainWeather;
        TextView txtTemperature;
        TextView txtMaxMinTemperature;
        TextView txtPressure;
        TextView txtWind;

        private WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateTime = itemView.findViewById(R.id.txtItemDateTime);
            imgWeather = itemView.findViewById(R.id.imgItemCloud);
            txtMainWeather = itemView.findViewById(R.id.txtItemMainWeather);
            txtTemperature = itemView.findViewById(R.id.txtItemTemp);
            txtMaxMinTemperature = itemView.findViewById(R.id.txtItemMaxMin);
            txtPressure = itemView.findViewById(R.id.txtItemPressure);
            txtWind = itemView.findViewById(R.id.txtItemWind);

        }

        private void loadImage(String mainWeather) {

            switch (mainWeather) {
                case "Thunderstorm":
                    imgWeather.setImageResource(R.drawable.thunderstorm);
                    break;
                case "Drizzle":
                    imgWeather.setImageResource(R.drawable.drizzle);
                    break;

                case "Rain":
                    imgWeather.setImageResource(R.drawable.rain);
                    break;

                case "Snow":
                    imgWeather.setImageResource(R.drawable.snow);
                    break;

                case "Mist":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Smoke":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Haze":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Dust":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Fog":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Sand":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Ash":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Squall":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Tornado":
                    imgWeather.setImageResource(R.drawable.wind);
                    break;

                case "Clear":
                    imgWeather.setImageResource(R.drawable.sun);
                    break;

                case "Clouds":
                    imgWeather.setImageResource(R.drawable.cloud);
                    break;
            }
        }

    }


}
