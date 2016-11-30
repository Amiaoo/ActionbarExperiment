package com.cornez.actionbarexperiment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


public class Appetizer extends Fragment {

    int id;
    SharedPreferences preferences;
    RadioGroup appetizerGroup;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = this.getActivity().getSharedPreferences(
                "Mypreferences", Context.MODE_PRIVATE);
        appetizerGroup = (RadioGroup)getView().findViewById(R.id.appetizer_choice);
        appetizerGroup.setOnCheckedChangeListener(appetizerListener);
        if(preferences.contains("KEYNAME")){
            id = preferences.getInt("KEYNAME",-1);
            appetizerGroup.check(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appetizer, container, false);
    }

    public RadioGroup.OnCheckedChangeListener appetizerListener =
            new RadioGroup.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    SharedPreferences.Editor editor = preferences.edit();

                    id = appetizerGroup.getCheckedRadioButtonId();
                    editor.putInt("KEYNAME", id);
                    if(id == R.id.appetizer_meatball){
                        editor.putInt("APPETIZER_PRICE", 5);
                    } else if(id == R.id.appetizer_salad){
                        editor.putInt("APPETIZER_PRICE", 4);
                    } else if(id == R.id.appetizer_soup){
                        editor.putInt("APPETIZER_PRICE", 3);
                    }

                    //Log.e("LOOK HERE!",String.valueOf(id));
                    editor.commit();

                }
            };



}
