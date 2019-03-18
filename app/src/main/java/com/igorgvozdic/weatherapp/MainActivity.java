package com.igorgvozdic.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igorgvozdic.weatherapp.adapter.WeatherAdapter;
import com.igorgvozdic.weatherapp.model.ForecastFeed;
import com.igorgvozdic.weatherapp.model.WeatherFeed;
import com.igorgvozdic.weatherapp.retrofit.Api;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private TextView txtT;
    private TextView txtMM;
    private TextView txtPr;
    private TextView txtWi;
    private TextView txtCW;
    private TextView txtForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        imgWeather.setImageResource(R.drawable.wondering);

        fetchCurrentWeather();

    }

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
                Call<ForecastFeed> forecastFeedCall = api.getFiveDaysForecast(city, Api.UNITS_METRIC, Api.API_KEY);

                call.enqueue(new Callback<WeatherFeed>() {
                    @Override
                    public void onResponse(Call<WeatherFeed> call, Response<WeatherFeed> response) {
                        Log.i(TAG, "onResponse: Successful" + response.code());

                        WeatherFeed weatherFeed = response.body();

                        if (weatherFeed != null) {

                            Log.i(TAG, "onResponse: " + weatherFeed.toString());

                            int currentTemp = (int) weatherFeed.getmMain().getmTemp();
                            int maxTemp = (int) weatherFeed.getmMain().getmMaxTemp();
                            int minTemp = (int) weatherFeed.getmMain().getmMinTemp();
                            int pressure = (int) weatherFeed.getmMain().getmPressure();
                            double wind = weatherFeed.getmWind().getmSpeed();
                            String mainWeather = weatherFeed.getmWeather().get(0).getmMain();

                            loadImage(mainWeather);

                            txtT.setText("t\u2103:");
                            txtMM.setText("Max/Min t\u2103:");
                            txtPr.setText("Pressure:");
                            txtWi.setText("Wind:");
                            txtCW.setText("Current Weather:");
                            txtForecast.setText("~Forecast for 5 days, every 3 hours~");

                            txtCurrentTemp.setText(Integer.toString(currentTemp) + "\u2103");
                            txtMaxMinTemp.setText(Integer.toString(maxTemp) + "\u2103" + " / " + Integer.toString(minTemp) + "\u2103");
                            txtPressure.setText(Integer.toString(pressure) + " mbar");
                            txtWind.setText(Double.toString(wind) + " m/s");
                            txtMainWeather.setText(mainWeather);
                            txtCity.setText(city);

                        } else {
                            Toast.makeText(getApplicationContext(), "Not a valid city name. Please try again", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherFeed> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Sorry, we are not able to fetch the weather at the moment", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Please try latter ", Toast.LENGTH_SHORT).show();
                    }
                });

                final RecyclerView recyclerView = findViewById(R.id.recyclerview);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);


                forecastFeedCall.enqueue(new Callback<ForecastFeed>() {
                    @Override
                    public void onResponse(Call<ForecastFeed> call, Response<ForecastFeed> response) {

                        ForecastFeed forecastFeed = response.body();
                        if (forecastFeed != null) {

                            List<WeatherFeed> weatherFeeds = forecastFeed.getmForecast();

                            recyclerView.setLayoutManager(layoutManager);

                            WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), weatherFeeds);

                            recyclerView.setAdapter(adapter);


                        }

                        edtCity.setText("");
                        edtCity.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }

                    @Override
                    public void onFailure(Call<ForecastFeed> call, Throwable t) {

                    }
                });

            }
        });


    }


    private void loadImage(String mainWeather) {

        switch (mainWeather) {
            case "Thunderstorm":
                imgWeather.setImageResource(R.drawable.thunderstorm);
                Toast.makeText(getApplicationContext(), "Unfortunately is seems it will!", Toast.LENGTH_LONG).show();
                break;
            case "Drizzle":
                imgWeather.setImageResource(R.drawable.drizzle);
                Toast.makeText(getApplicationContext(), "Unfortunately is seems it will!", Toast.LENGTH_LONG).show();
                break;

            case "Rain":
                imgWeather.setImageResource(R.drawable.rain);
                Toast.makeText(getApplicationContext(), "Unfortunately is seems it will!", Toast.LENGTH_LONG).show();
                break;

            case "Snow":
                imgWeather.setImageResource(R.drawable.snow);
                Toast.makeText(getApplicationContext(), "It seems it won`t but it could snow!", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "It seems it will be a clear day today!", Toast.LENGTH_LONG).show();
                break;

            case "Clouds":
                imgWeather.setImageResource(R.drawable.cloud);
                Toast.makeText(getApplicationContext(), "It seems it won`t but it will be cloudy!", Toast.LENGTH_LONG).show();
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

        txtCW = findViewById(R.id.txtCW);
        txtT = findViewById(R.id.txtT);
        txtMM = findViewById(R.id.txtMM);
        txtPr = findViewById(R.id.txtPr);
        txtWi = findViewById(R.id.txtWi);
        txtForecast = findViewById(R.id.txtForecast);

    }


}

