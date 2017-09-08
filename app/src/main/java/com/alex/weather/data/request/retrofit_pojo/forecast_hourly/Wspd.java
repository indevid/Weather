
package com.alex.weather.data.request.retrofit_pojo.forecast_hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wspd {

    @SerializedName("english")
    @Expose
    private String english;
    @SerializedName("metric")
    @Expose
    private int metric;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getMetric() {
        return metric;
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

}
