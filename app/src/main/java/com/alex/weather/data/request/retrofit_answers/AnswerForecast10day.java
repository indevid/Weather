
package com.alex.weather.data.request.retrofit_answers;

import com.alex.weather.data.request.retrofit_pojo.forecast10day.Forecast;
import com.alex.weather.data.request.retrofit_pojo.forecast10day.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerForecast10day {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
