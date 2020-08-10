package com.sekae.learnapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class covid19 extends AppCompatActivity {
    private TextView covidText;//
    private TextView positifText;
    private TextView sembuhText;
    private TextView meninggalText;

    RequestQueue quee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);


//        covidText = findViewById(R.id.covidText);
        positifText = findViewById(R.id.textPositif);
        sembuhText = findViewById(R.id.textSembuh);
        meninggalText = findViewById(R.id.textMeninggal);

        String greeting = getIntent().getStringExtra("welcome");
        //covidText.setText(greeting);


        //buat api

        //instansiasi dari class Mysingleton
        //
        quee = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://api.kawalcorona.com/indonesia/"
                , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for(int i = 0; i <10; i++) {
                        JSONObject apidata = jsonArray.getJSONObject(i);

                        Log.d("COPID", "Negara " + apidata.getString("name"));
                        Log.d("COPID", "Jumlah Positif  " + apidata.getString("positif"));
                        //masukan ke text
                        positifText.setText(apidata.getString("positif"));
                        Log.d("COPID", "Jumlah Sembuh " + apidata.getString("sembuh"));
                        sembuhText.setText(apidata.getString("sembuh"));
                        Log.d("COPID", "Jumlah Meninggal " + apidata.getString("meninggal"));
                        meninggalText.setText(apidata.getString("meninggal"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error Listener", "onErrorResponse: Eroor");
            }
        });
        quee.add(jsonArrayRequest);




    }
}
