package com.bryonnicoson.wbcr;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.Dog;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

public class DogDetailActivity extends AppCompatActivity  {

    SliderLayout sliderShow;
    ImageView ivVaccinated, ivAltered, ivHouseTrained;
    TextView dogName, dogSize, dogAge, dogSex, dogBreed, tvVaccinated, tvAltered, tvHouseTrained;
    WebView dogDescription;
    Button btnAdopt;
    Intent intentApply;

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
        String trimmed = dog.description.substring(0, firstmatch).trim();

        // using webview to justify description text
        dogDescription = (WebView) findViewById(R.id.detail_dog_description);

        String text = "<html><head><meta charset=\"UTF-8\"></head><body style=\"background-color: #BBDEFB;\">"
                + "<p align=\"justify\" style=\"line-height: 150%; background-color: #BBDEFB;\">"
                + trimmed.replaceAll("\n", "<br />")
                + "</p> "
                + "</body></html>";
        String textra = text.replaceAll("â\u0080¦", "..."); // hack to insert ellipses
        dogDescription.loadData(textra, "text/html", "utf-8");

        if (dog.hasShots) {
            ivVaccinated = (ImageView) findViewById(R.id.detail_iv_vaccinated);
            ivVaccinated.setImageResource(R.drawable.checked36);
            tvVaccinated = (TextView) findViewById(R.id.detail_tv_vaccinated);
            tvVaccinated.setText("vaccinated");
        }

        if (dog.altered) {
            ivAltered = (ImageView) findViewById(R.id.detail_iv_altered);
            ivAltered.setImageResource(R.drawable.checked36);
            tvAltered = (TextView) findViewById(R.id.detail_tv_altered);
            if (dog.sex.equals("Male")) {
                tvAltered.setText("neutered");
            } else {
                tvAltered.setText("spayed");
            }
        }

        if (dog.housetrained) {
            ivHouseTrained= (ImageView) findViewById(R.id.detail_iv_housetrained);
            ivHouseTrained.setImageResource(R.drawable.checked36);
            tvHouseTrained = (TextView) findViewById(R.id.detail_tv_housetrained);
            tvHouseTrained.setText("house-trained");
        }

        // TODO: add buttons / imagebuttons linking to application, website, breed info

        sliderShow = (SliderLayout) findViewById(R.id.slider);
        for (String image : dog.images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(image);

            sliderShow.addSlider(defaultSliderView);
        }
        sliderShow.stopAutoCycle();
        sliderShow.setCurrentPosition(0);  // necessary to line up indicator and initial image
        //sliderShow.setDuration(4000);
        //sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderShow.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator));

        // set button background & text colors to R.color values
        btnAdopt = (Button) findViewById(R.id.button_adopt);
        btnAdopt.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        btnAdopt.setTextColor(getResources().getColor(R.color.white));


        btnAdopt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intentApply = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fs16.formsite.com/WishBoneCanineRescue/adoptionapplication/index.html"));
                startActivity(intentApply);
            }
        });
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
