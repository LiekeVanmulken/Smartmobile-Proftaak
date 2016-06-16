package com.wouterv.quantifiedstudents.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class for making GET or POST calls with Volley
 *
 * Created by sander on 25-5-2016.
 */
public class VolleyServiceFitbit {

    private IResultFitbit resultCallback = null;
    private Context context;
    private String requestType = null;
    private RequestQueue requestQueue;

    /**
     *
     * @param resultCallback the IResult implementation
     * @param context the current activity context
     */
    public VolleyServiceFitbit(IResultFitbit resultCallback, Context context) {
        this.resultCallback = resultCallback;
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }


    /**
     * Make a GET request
     * @param url the url of the api
     * @param jsonObject optional jsonObject to be send with the request
     */
    public void getActivity() {
        requestType = "GETCALL";
        String url = "http://fitbit.zlst.nl?action=getactivity";
        JSONObject jsonObject = null;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, jsonObject, successListener, errorListener);
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Make a GET request
     * @param url the url of the api
     * @param jsonObject optional jsonObject to be send with the request
     */
    public void getSleep() {
        requestType = "GETCALL";
        String url = "http://fitbit.zlst.nl?action=getsleep";
        JSONObject jsonObject = null;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, jsonObject, successListener, errorListener);
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * The callback for a successful request
     */
    private Response.Listener<JSONArray> successListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
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
