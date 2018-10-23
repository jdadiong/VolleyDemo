package com.jadiong.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private String TAG = "QueueTag";
    TextView content;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.tvContent);
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI2();
            }
        });
    }

    // Using Volley new request. Always making a new RequestQueue.
    private void callAPI(){
        // Set up RequestQueue for API/Network calls.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://xivapi.com/content?pretty=1&key=16e70f1742094efc8071340e";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        content.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        content.setText("Didn't work");
                    }
                });
        request.setTag(TAG);
        queue.add(request);
    }

    // Using singleton
    private void callAPI2() {
        // Get instance of RequestQueue
        // Important to pass in the applicationContext.
        RequestQueue queue = RequestSingleton.getInstance(this.getApplicationContext());
        String url = "https://xivapi.com/content?pretty=1&key=16e70f1742094efc8071340e";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        content.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        content.setText("Didn't work");
                    }
                });
        request.setTag(TAG);
        queue.add(request);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(queue != null) {
            queue.cancelAll(TAG);
        }
    }
}
