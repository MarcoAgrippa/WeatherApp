package com.igorgvozdic.weatherapp.retrofit;

import com.igorgvozdic.weatherapp.model.ForecastFeed;
import com.igorgvozdic.weatherapp.model.WeatherFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    //http://api.openweathermap.org/data/2.5/weather?q=Belgrade&units=metric&APPID=69deffc8e0fe1777c8695e7b68f8f6e6

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String WEATHER = "weather";
    String FORECAST = "forecast";
    String UNITS_METRIC = "metric";
    String API_KEY = "69deffc8e0fe1777c8695e7b68f8f6e6";


    @Headers("Content-Type: application/json")
    @GET(WEATHER)
    Call<WeatherFeed> getCurrentWeather(@Query("q") String city,
                                        @Query("units") String units,
                                        @Query("APPID") String apiKey);

    @Headers("Content-Type: application/json")
    @GET("forecast?q=london&units=metric&APPID=69deffc8e0fe1777c8695e7b68f8f6e6")
    Call<ForecastFeed> getFiveDaysForecast();


}
