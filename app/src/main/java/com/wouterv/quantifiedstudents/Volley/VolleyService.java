package com.wouterv.quantifiedstudents.Volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for making GET or POST calls with Volley
 *
 * Created by sander on 25-5-2016.
 */
public class VolleyService {

    private IResult resultCallback = null;
    private Context context;
    private String requestType = null;
    private RequestQueue requestQueue;

    /**
     *
     * @param resultCallback the IResult implementation
     * @param context the current activity context
     */
    public VolleyService(IResult resultCallback, Context context) {
        this.resultCallback = resultCallback;
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Make a POST request
     * @param url the url of the api
     * @param jsonObject optional jsonObject to be send with the request
     */
    public void requestAccessToken(String url, JSONObject jsonObject, final String code) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * Make a GET request
     * @param url the url of the api
     * @param jsonObject optional jsonObject to be send with the request
     */
    public void getDataVolley(String url, JSONObject jsonObject) {
        requestType = "GETCALL";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, successListener, errorListener);
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * The callback for a successful request
     */
    private Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            resultCallback.notifySuccess(requestType, response);
        }
    };

    /**
     * The callback for a unsuccessful request
     */
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public  void onErrorResponse(VolleyError error) {
            resultCallback.notifyError(requestType, error);
        }
    };
}
