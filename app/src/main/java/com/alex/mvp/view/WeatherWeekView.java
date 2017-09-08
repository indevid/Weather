package com.alex.mvp.view;

import com.alex.weather.data.request.retrofit_pojo.forecast10day.Forecastday_;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

/**
 * Created by Alex on 08.09.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherWeekView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorMessage(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showProgressDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void dismissProgressDialog();

    void showForecastDayList(List<Forecastday_> forecastDayList);

}
