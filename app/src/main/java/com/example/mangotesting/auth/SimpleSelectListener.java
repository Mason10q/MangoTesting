package com.example.mangotesting.auth;


import android.view.View;
import android.widget.AdapterView;

public interface SimpleSelectListener extends AdapterView.OnItemSelectedListener {

    @Override
    void onItemSelected(AdapterView<?> parent, View view, int position, long id);

    @Override
    default void onNothingSelected(AdapterView<?> parent){};
}
