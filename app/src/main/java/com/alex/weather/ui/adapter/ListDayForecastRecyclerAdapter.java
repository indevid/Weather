package com.alex.weather.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.weather.R;
import com.alex.weather.data.request.retrofit_pojo.forecast10day.Forecastday_;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDayForecastRecyclerAdapter extends RecyclerView.Adapter<ListDayForecastRecyclerAdapter.ViewHolder> {

    private final List<Forecastday_> forecastDayList;

    public ListDayForecastRecyclerAdapter(List<Forecastday_> items) {
        forecastDayList = items;
    }

    private Context ctx;

    @Override
    public ListDayForecastRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_day_forecast, parent, false);
        return new ListDayForecastRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListDayForecastRecyclerAdapter.ViewHolder holder, int position) {
        holder.setViewHolder(forecastDayList.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastDayList.size();
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

        void setViewHolder(Forecastday_ forecastDay) {
            img.setImageURI(Uri.parse(forecastDay.getIconUrl()));
            txt_condition.setText(forecastDay.getConditions());
            String temp = ctx.getResources().getString(R.string.weather_temp, forecastDay.getLow().getCelsius(), forecastDay.getHigh().getCelsius());
            txt_temp.setText(temp);
            String wind = ctx.getResources().getString(R.string.weather_wind, forecastDay.getMaxwind().getDir(), forecastDay.getAvewind().getKph(), forecastDay.getMaxwind().getKph());
            txt_wind.setText(wind);
            String date = ctx.getResources().getString(R.string.weather_date, forecastDay.getDate().getDay(), forecastDay.getDate().getMonth(), forecastDay.getDate().getYear());
            txt_time.setText(date);
        }
    }


}
