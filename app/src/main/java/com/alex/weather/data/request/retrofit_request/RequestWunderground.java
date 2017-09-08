package com.alex.weather.data.request.retrofit_request;


import com.alex.weather.data.request.retrofit_answers.AnswerForecast10day;
import com.alex.weather.data.request.retrofit_answers.AnswerForecastHourly;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestWunderground {

    @GET("forecast10day/lang:RU/q/{lat_lon}.json")
    Call<AnswerForecast10day> forecast10day(@Path("lat_lon") String lat_lon);

    @GET("hourly/lang:RU/q/{lat_lon}.json")
    Call<AnswerForecastHourly> forecastHourly(@Path("lat_lon") String lat_lon);

}
