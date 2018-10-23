package com.jadiong.volleydemo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class RequestSingleton {
    private static RequestQueue instance;
    private static Context context;

    private RequestSingleton(Context context) {
        this.context = context;
        instance = getInstance(context);
    }

    public static RequestQueue getInstance(Context context) {
        if(instance == null) {
            instance = Volley.newRequestQueue(context.getApplicationContext());
        }
        return instance;
    }
}
