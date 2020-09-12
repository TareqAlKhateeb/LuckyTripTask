package com.example.luckytripassignment.data;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.luckytripassignment.data.Rooms.Root;
import com.example.luckytripassignment.util.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.luckytripassignment.util.Constants.BASE_URL;

public class IApiCaller {

    public void callApiGet(Context context, Listener listener) {

        RequestQueue queue = Volley.newRequestQueue(context);

        //Adding the first query parameter manually
        String url = BASE_URL + "?language_code=" + Locale.getDefault().getLanguage();

        //A simple get request with no parameters and a content-type Header
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                response -> {

                    try {

                        JSONObject DataRows = new JSONObject(response.toString());

                        Root rooms = Util.objectFromJSON(DataRows
                                .toString(), Root.class);

                        listener.onSuccessfulResponse(rooms);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                },

                error -> Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_SHORT)) {

            @Override
            public Map<String, String> getHeaders() {

                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/x-www-form-urlencoded");
                return header;

            }
        };

        objectRequest.setShouldCache(false);
        queue.add(objectRequest);

    }

    public interface Listener {

        void onSuccessfulResponse(Rooms.Root rooms);

    }

}