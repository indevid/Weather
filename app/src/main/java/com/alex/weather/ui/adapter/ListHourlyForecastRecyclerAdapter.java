package com.alex.weather.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.weather.R;
import com.alex.weather.data.request.retrofit_pojo.forecast_hourly.HourlyForecast;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHourlyForecastRecyclerAdapter extends RecyclerView.Adapter<ListHourlyForecastRecyclerAdapter.ViewHolder> {

    private final List<HourlyForecast> hourlyForecastList;

    public ListHourlyForecastRecyclerAdapter(List<HourlyForecast> items) {
        hourlyForecastList = items;
    }

    private Context ctx;

    @Override
    public ListHourlyForecastRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_hourly_forecast, parent, false);
        return new ListHourlyForecastRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListHourlyForecastRecyclerAdapter.ViewHolder holder, int position) {
        holder.setViewHolder(hourlyForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return hourlyForecastList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.img)
        SimpleDraweeView img;
        @BindView(R.id.txt_temp)
        TextView txt_temp;
        @BindView(R.id.txt_condition)
        TextView txt_condition;
        @BindView(R.id.txt_wind)
        TextView txt_wind;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setViewHolder(HourlyForecast hourlyForecast) {
            img.setImageURI(Uri.parse(hourlyForecast.getIconUrl()));
            txt_condition.setText(hourlyForecast.getCondition());
            String temp = ctx.getResources().getString(R.string.weather_hourly_temp, hourlyForecast.getTemp().getMetric());
            txt_temp.setText(temp);
            String wind = ctx.getResources().getString(R.string.weather_hourly_wind, hourlyForecast.getWdir().getDir(), hourlyForecast.getWspd().getMetric());
            txt_wind.setText(wind);
            String date = ctx.getResources().getString(R.string.weather_hourly_time, hourlyForecast.getFCTTIME().getHour(), hourlyForecast.getFCTTIME().getMin());
            txt_time.setText(date);
        }
    }


}
