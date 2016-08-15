package com.bryonnicoson.wbcr;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bryonnicoson.wbcr.model.Dog;
import com.bryonnicoson.wbcr.model.JsonResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    public JsonResponse jsonResponse;
    private Gson gson = new Gson();
    public ArrayList<Dog> dogs = new ArrayList<>();
    public AdapterView.OnItemClickListener dogClickListener;
    public Intent detailIntent;

    private class DogFetchTask extends AsyncTask<String, Void, JsonResponse> {

        private Context mContext;
        public DogFetchTask (Context context) {
            mContext = context;
        }

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

            dogs = Dog.dogMaker(jsonResponse);
            DogListAdapter dogListAdapter = new DogListAdapter(mContext, dogs);
            ListView dogListView = (ListView) findViewById(R.id.dog_card_list_view);
            dogListView.setBackgroundResource(R.drawable.splashscreen);
            dogListView.setAdapter(dogListAdapter);

            dogClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Dog dog = dogs.get(position);
                    detailIntent.putExtra("DOG", dog);
                    startActivity(detailIntent);
                }
            };
            dogListView.setOnItemClickListener(dogClickListener);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailIntent = new Intent(MainActivity.this, DogDetailActivity.class);

        new DogFetchTask(this).execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
