package com.alex.weather.ui.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alex.weather.R;
import com.alex.weather.ui.activity.CurrentWeatherActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


import butterknife.ButterKnife;

public class SearchCityFragment extends Fragment{

    private final String TAG = "test SearchCityFrag";

    private SelectCityListener mListener;

    public SearchCityFragment() {
        // Required empty public constructor
    }

    public static SearchCityFragment newInstance() {
        return new SearchCityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_city, container, false);
        ButterKnife.bind(this, view);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(placeSelectionListener);
        return view;
    }

    private PlaceSelectionListener placeSelectionListener = new PlaceSelectionListener() {
        @Override
        public void onPlaceSelected(Place place) {
            Location location = new Location("SearchLocation");
            location.setLatitude(place.getLatLng().latitude);
            location.setLongitude(place.getLatLng().longitude);
            if (mListener != null) {
                mListener.onSelectItem(location);
            }
        }

        @Override
        public void onError(Status status) {
            Log.i(TAG, "An error occurred: " + status);
            Toast.makeText(getActivity(), R.string.error_place_selected, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectCityListener) {
            mListener = (SelectCityListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectCityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SelectCityListener {
        void onSelectItem(Location location);
    }

}
