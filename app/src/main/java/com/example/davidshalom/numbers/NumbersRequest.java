package com.example.davidshalom.numbers;

import Listeners.NumberRequestListener;
import Model.NumberResponseConverter;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidshalom on 27/12/2014.
 */
public class NumbersRequest {

	public static StringRequest getUserNumberRequest(int number, final NumberRequestListener numberRequestListener, final NumberResponseConverter numberResponseConverter) {

		String urlUserChosen = "https://numbersapi.p.mashape.com/" + "random" + "/math";

		StringRequest stringRequest = new StringRequest(Request.Method.GET, urlUserChosen,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							numberRequestListener.onResponse(numberResponseConverter.convert(response));
						} catch (JsonSyntaxException e) {
							numberRequestListener.onErrorResponse(e.getMessage());
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						numberRequestListener.onErrorResponse(error.getMessage());
					}
				}) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				headers.put("X-Mashape-Key", "hntWwK1g1TmshwPloUO7FU8zFroZp12I2pnjsnScflMVXFEhsa");
				return headers;
			}

		};

		return stringRequest;

	}

}
