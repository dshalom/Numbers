package com.example.davidshalom.numbers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get a RequestQueue
		RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).
				getRequestQueue();

		// Add a request (in this example, called stringRequest) to your RequestQueue.
		final ImageView mImageView = (ImageView) findViewById(R.id.myImage);
		final String url = "http://i.imgur.com/7spzG.png";

		// Retrieves an image specified by the URL, displays it in the UI.
		ImageRequest request = new ImageRequest(url,
				new Response.Listener() {
					@Override
					public void onResponse(Object o) {
						android.util.Log.e("DSDS", "got response");
						mImageView.setImageBitmap((Bitmap) o);
					}
				}, 0, 0, null,
				new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {

						;
					}
				});
		// Access the RequestQueue through your singleton class.
		VolleySingleton.getInstance(this).addToRequestQueue(request);

		///////
		//////

		final String theurl = "https://numbersapi.p.mashape.com/21/math";
		final TextView textView = (TextView) findViewById(R.id.txt);
		JsonObjectRequest jsObjRequest = new JsonObjectRequest
				(Request.Method.GET, theurl, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject o) {
						try {
							textView.setText("Response: " + o.getString("text"));
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						int o = 9;
						o--;

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

		VolleySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
		//numbersapi.p.mashape.com/{month}/{day}/date

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
