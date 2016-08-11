package com.bryonnicoson.wbcr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.Dog;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

public class DogDetailActivity extends AppCompatActivity {

    String name, breed, age, sex, size, description;
    boolean hasShots, altered, housetrained;
    List<String> images;
    SliderLayout sliderShow;
    TextView dogName, dogSize, dogAge, dogSex, dogBreed;
    WebView dogDescription;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);

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

        // using webview to justify description text

        dogDescription = (WebView) findViewById(R.id.detail_dog_description);

        String text = "<html><body>"
                + "<p align=\"justify\" style=\"line-height: 150%; \">"
                + dog.description.replaceAll("\n", "<br />")
                + "</p> "
                + "</body></html>";

        dogDescription.loadData(text, "text/html", "utf-8");

        hasShots = dog.hasShots;
        altered = dog.altered;
        housetrained = dog.housetrained;

        sliderShow = (SliderLayout) findViewById(R.id.slider);
        for (String image : dog.images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(image);

            sliderShow.addSlider(defaultSliderView);
        }



    }
    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }
}
