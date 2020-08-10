package com.sekae.learnapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView jsonText;
    private Button jsonArrayButton;
    private Button covidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonText = findViewById(R.id.jsonText);
        jsonArrayButton = findViewById(R.id.buttonJsonArray);
        covidButton = findViewById(R.id.covidBtn);





        requestQueue = Volley.newRequestQueue(MainActivity.this);
//        urlArray = https://jsonplaceholder.typicode.com/todos

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            jsonText.setText("Json Data From Titile is " + jsonObject.getString("title"));
                            Log.d("JSON", "onResponse: " + jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("error", "onErrorResponse: " + volleyError.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);


        jsonArrayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, jsonArray.class);

                startActivity(intent);
            }
        });

        //mulai intent covid

        covidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent covidIntent = new Intent(MainActivity.this,covid19.class);
                covidIntent.putExtra("welcome","Selamat Datang Partisipan Covid");
                startActivity(covidIntent);
            }
        });
    }
}
