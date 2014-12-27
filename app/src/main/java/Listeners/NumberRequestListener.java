package Listeners;

import Model.NumberResponse;
import com.android.volley.VolleyError;

/**
 * Created by davidshalom on 27/12/2014.
 */
public interface NumberRequestListener {

	public void onResponse(NumberResponse numberResponse);

	void onErrorResponse(String error);

}

