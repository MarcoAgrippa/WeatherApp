package com.igorgvozdic.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igorgvozdic.weatherapp.model.WeatherFeed;
import com.igorgvozdic.weatherapp.retrofit.Api;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText edtCity;
    private ImageButton searchButton;
    private ImageView imgWeather;
    private TextView txtCurrentTemp;
    private TextView txtMaxMinTemp;
    private TextView txtPressure;
    private TextView txtWind;
    private TextView txtMainWeather;
    private TextView txtCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        imgWeather.setImageResource(R.drawable.wondering);

        fetchCurrentWeather();

    }


//        Call<ForecastFeed> feedCall = api.getFiveDaysForecast();

//        feedCall.enqueue(new Callback<ForecastFeed>() {
//            @Override
//            public void onResponse(Call<ForecastFeed> call, Response<ForecastFeed> response) {
//                Log.i(TAG, "onResponse: ForecastFeed Successful");
//
//                ForecastFeed forecastFeed = response.body();
//                Log.i(TAG, "onResponse: " + forecastFeed.getmCity().toString());
//
//                if (forecastFeed != null){
//                    List<WeatherFeed> weatherFeeds = forecastFeed.getmForecast();
//
//                    for (WeatherFeed w : weatherFeeds){
//                        Log.i(TAG, "onResponse: " + w.toString());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ForecastFeed> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//            }
//        });


    private void fetchCurrentWeather() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String city = edtCity.getText().toString().trim();

                Call<WeatherFeed> call = api.getCurrentWeather(city, Api.UNITS_METRIC, Api.API_KEY);
                Log.i(TAG, "onCreate: " + call.request().toString());

                call.enqueue(new Callback<WeatherFeed>() {
                    @Override
                    public void onResponse(Call<WeatherFeed> call, Response<WeatherFeed> response) {
                        Log.i(TAG, "onResponse: Successful" + response.code());

                        WeatherFeed weatherFeed = response.body();

                        Log.i(TAG, "onResponse: " + weatherFeed.toString());

                        int currentTemp = (int) weatherFeed.getmMain().getmTemp();
                        int maxTemp = (int) weatherFeed.getmMain().getmMaxTemp();
                        int minTemp = (int) weatherFeed.getmMain().getmMinTemp();
                        int pressure = (int) weatherFeed.getmMain().getmPressure();
                        double wind = weatherFeed.getmWind().getmSpeed();
                        String mainWeather = weatherFeed.getmWeather().get(0).getmMain();

                        loadImage(mainWeather);

                        txtCurrentTemp.setText(Integer.toString(currentTemp) + "\u2103");
                        txtMaxMinTemp.setText(Integer.toString(maxTemp) + "\u2103" + "/" + Integer.toString(minTemp) + "\u2103");
                        txtPressure.setText(Integer.toString(pressure) + " mbar");
                        txtWind.setText(Double.toString(wind) + " m/s");
                        txtMainWeather.setText(mainWeather);
                        txtCity.setText(city);

                        edtCity.setText("");
                    }

                    @Override
                    public void onFailure(Call<WeatherFeed> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        Toast.makeText(getApplicationContext(), "Sorry, we are not able to fetch the weather at the moment", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Please try latter ", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


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

    private void initViews() {
        edtCity = findViewById(R.id.edtCity);
        imgWeather = findViewById(R.id.imgWeather);
        searchButton = findViewById(R.id.searchButton);

        txtCurrentTemp = findViewById(R.id.txtCurrentTemp);
        txtMaxMinTemp = findViewById(R.id.txtMaxMinTemp);
        txtPressure = findViewById(R.id.txtPressure);
        txtWind = findViewById(R.id.txtWind);
        txtMainWeather = findViewById(R.id.txtMainWeather);
        txtCity = findViewById(R.id.txtCity);
    }

}

