package com.bryonnicoson.wbcr;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.JsonResponse;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    // private ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new Gson();
    private JsonFactory jsonFactory = new JsonFactory();
    JsonResponse jsonResponse;

    @BindView(R.id.test) TextView tvTest;


    private class DogFetchTask extends AsyncTask<String, Void, JsonResponse>{

        @Override
        protected JsonResponse doInBackground(String...params) {

            String url = "https://wishbonecr.herokuapp.com/";
            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();
                // final ObjectReader objectReader = objectMapper.reader(Petfinder.class);
                // jsonResponse = objectMapper.readValue(response.body().byteStream(), JsonResponse.class);
                jsonResponse = gson.fromJson(response.body().charStream(), JsonResponse.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }
        protected void onPostExecute(JsonResponse jsonResponse) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonResponse.petfinder.pets.pet.size(); i++) {
                sb.append(jsonResponse.petfinder.pets.pet.get(i).name);
                sb.append(" - ");
                sb.append(jsonResponse.petfinder.pets.pet.get(i).size);
                sb.append(" ");
                sb.append(jsonResponse.petfinder.pets.pet.get(i).age);
                sb.append(" ");
                sb.append(jsonResponse.petfinder.pets.pet.get(i).sex);
                sb.append(" ");
                sb.append(jsonResponse.petfinder.pets.pet.get(i).breeds.toString());
                sb.append("\n");
            }
            tvTest.setText(sb.toString());
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
