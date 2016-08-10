package com.bryonnicoson.wbcr;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.Dog;
import com.bryonnicoson.wbcr.model.JsonResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    JsonResponse jsonResponse;
    ArrayList<Dog> dogs = new ArrayList<Dog>();


    ListView mDogList;
    //DogCursorAdapter mAdapter;
    AdapterView.OnItemClickListener mDogClickListener;
    Intent mDetailIntent;

    //@BindView(R.id.test) TextView tvTest;


    private class DogFetchTask extends AsyncTask<String, Void, JsonResponse> {

        @Override
        protected JsonResponse doInBackground(String... params) {
            String url = "https://wishbonecr.herokuapp.com/";
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                jsonResponse = gson.fromJson(response.body().charStream(), JsonResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        protected void onPostExecute(JsonResponse jsonResponse) {

            //make a list of Dogs


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new DogFetchTask().execute();
    }
}
