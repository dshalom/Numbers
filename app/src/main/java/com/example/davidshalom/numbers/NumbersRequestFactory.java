package com.example.davidshalom.numbers;

import Listeners.NumberRequestListener;
import Model.NumberResponse;
import Model.NumberResponseConverter;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidshalom on 27/12/2014.
 */
public class NumbersRequestFactory {

	public static NumberRequest getNumberRequest(final int number, final NumberRequestListener numberRequestListener, final NumberResponseConverter numberResponseConverter) {

		String urlUserChosen = "https://numbersapi.p.mashape.com/" + number + "/math";

		NumberRequest numberRequest = new NumberRequest(Request.Method.GET, urlUserChosen,
				new Response.Listener<NumberResponse>() {

					@Override
					public void onResponse(NumberResponse numberResponse) {
						numberRequestListener.onResponse(numberResponse);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						numberRequestListener.onErrorResponse(error.getMessage());
					}
				}, numberResponseConverter) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				headers.put("X-Mashape-Key", "hntWwK1g1TmshwPloUO7FU8zFroZp12I2pnjsnScflMVXFEhsa");
				return headers;
			}

		};

		return numberRequest;
	}

	public static NumberRequest getRandomNumberRequest(final NumberRequestListener numberRequestListener, final NumberResponseConverter numberResponseConverter) {

		String urlUserChosen = "https://numbersapi.p.mashape.com/random/math";

		NumberRequest numberRequest = new NumberRequest(Request.Method.GET, urlUserChosen,
				new Response.Listener<NumberResponse>() {

					@Override
					public void onResponse(NumberResponse numberResponse) {
						numberRequestListener.onResponse(numberResponse);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						numberRequestListener.onErrorResponse(error.getMessage());
					}
				}, numberResponseConverter) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				headers.put("X-Mashape-Key", "hntWwK1g1TmshwPloUO7FU8zFroZp12I2pnjsnScflMVXFEhsa");
				return headers;
			}

		};

		return numberRequest;
	}


	public static class NumberRequest extends com.android.volley.Request<NumberResponse> {

		private Response.Listener<NumberResponse> listener;
		private NumberResponseConverter numberResponseConverter;

		public NumberRequest(int method, String url, Response.Listener<NumberResponse> listener, Response.ErrorListener errorListener, NumberResponseConverter numberResponseConverter) {
			super(method, url, errorListener);

			this.listener = listener;
			this.numberResponseConverter = numberResponseConverter;
		}

		@Override
		protected Response<NumberResponse> parseNetworkResponse(NetworkResponse networkResponse) {

			String jsonString = new String(networkResponse.data);
			NumberResponse MyResponse = numberResponseConverter.convert(jsonString);
			return Response.success(MyResponse, parseIgnoreCacheHeaders(networkResponse));
		}

		@Override
		protected void deliverResponse(NumberResponse numberResponse) {
			listener.onResponse(numberResponse);
		}
	}

	public static Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
		long now = System.currentTimeMillis();

		Map<String, String> headers = response.headers;
		long serverDate = 0;
		String serverEtag = null;
		String headerValue;

		headerValue = headers.get("Date");
		if (headerValue != null) {
			serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
		}

		serverEtag = headers.get("ETag");

		final long cacheHitButRefreshed = 2 * 1000; // cache only for 2 seconds then cache and network
		final long cacheExpired = 24 * 60 * 60 * 1000 * 365; // 1 year this cache entry expires completely
		final long softExpire = now + cacheHitButRefreshed;
		final long ttl = now + cacheExpired;

		Cache.Entry entry = new Cache.Entry();
		entry.data = response.data;
		entry.etag = serverEtag;
		entry.softTtl = softExpire;
		entry.ttl = ttl;
		entry.serverDate = serverDate;
		entry.responseHeaders = headers;

		return entry;
	}

}
