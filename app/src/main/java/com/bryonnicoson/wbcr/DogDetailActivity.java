package com.bryonnicoson.wbcr;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.Dog;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

public class DogDetailActivity extends AppCompatActivity {

    boolean hasShots, altered, housetrained;
    SliderLayout sliderShow;
    TextView dogName, dogSize, dogAge, dogSex, dogBreed;
    WebView dogDescription;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Dog dog = (Dog) getIntent().getSerializableExtra("DOG");

        dogName = (TextView) findViewById(R.id.detail_dog_name);
        dogName.setText(dog.name);
        dogSize = (TextView) findViewById(R.id.detail_dog_size);
        dogSize.setText(dog.size);
        dogAge = (TextView)findViewById(R.id.detail_dog_age);
        dogAge.setText(dog.age);
        dogSex = (TextView) findViewById(R.id.detail_dog_sex);
        dogSex.setText(dog.sex);
        dogBreed = (TextView)findViewById(R.id.detail_dog_breed);
        dogBreed.setText(dog.breed);

        // remove boilerplate link text from description - we don't need urls
        String[] boilerplate = {"For information about",
                                "For adoption informaton",
                                "To fill out our adoption"};

        // find index of first match
        int match = dog.description.length();
        int firstmatch = dog.description.length();
        for (int i = 0; i < boilerplate.length; i++){
            match = dog.description.indexOf(boilerplate[i]);
            if (match != -1 && match < firstmatch)
                firstmatch = match;
        }
        String trimmed = dog.description.substring(0, firstmatch);

        // using webview to justify description text
        dogDescription = (WebView) findViewById(R.id.detail_dog_description);

        String text = "<html><body style=\"background-color: #BBDEFB;\">"
                + "<p align=\"justify\" style=\"line-height: 150%; background-color: #BBDEFB;\">"
                + trimmed.replaceAll("\n", "<br />")
                + "</p> "
                + "</body></html>";
        text.replaceAll("â\u0080¦", "...");
        dogDescription.loadData(text, "text/html", "utf-8");

        // TODO: add indicators for these booleans...

        hasShots = dog.hasShots;
        altered = dog.altered;
        housetrained = dog.housetrained;

        // TODO: add buttons / imagebuttons linking to application, website, breed info

        sliderShow = (SliderLayout) findViewById(R.id.slider);
        for (String image : dog.images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(image);

            sliderShow.addSlider(defaultSliderView);
        }
        sliderShow.stopAutoCycle();
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
       // sliderShow.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

}
