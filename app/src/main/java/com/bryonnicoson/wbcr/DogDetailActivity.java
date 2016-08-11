package com.bryonnicoson.wbcr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class DogDetailActivity extends AppCompatActivity {

    String name, breed, age, sex, size, description;
    boolean hasShots, altered, housetrained;
    ArrayList<String> images;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);




    }
}
