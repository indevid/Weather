
package com.alex.weather.data.request.retrofit_answers;

import java.util.List;

import com.alex.weather.data.request.retrofit_pojo.forecast_hourly.HourlyForecast;
import com.alex.weather.data.request.retrofit_pojo.forecast_hourly.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerForecastHourly {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

}
