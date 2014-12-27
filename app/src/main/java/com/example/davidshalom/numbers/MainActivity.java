package com.example.davidshalom.numbers;

import Listeners.NumberRequestListener;
import Model.NumberResponse;
import Model.NumberResponseConverter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.toolbox.StringRequest;


public class MainActivity extends Activity implements NumberRequestListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StringRequest request = NumbersRequest.getUserNumberRequest(5, this, new NumberResponseConverter());
		VolleySingleton.getInstance(this).addToRequestQueue(request);

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


	@Override
	public void onResponse(NumberResponse numberResponse) {
		android.util.Log.e("DSDS", numberResponse.getText());
	}

	@Override
	public void onErrorResponse(String error) {
		android.util.Log.e("DSDS", error);

	}
}
