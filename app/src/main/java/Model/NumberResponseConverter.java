package Model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by davidshalom on 27/12/2014.
 */
public class NumberResponseConverter {

	private Gson gson;

	public NumberResponseConverter() {
		gson = new Gson();
	}

	public NumberResponse convert(String jsonString) throws JsonSyntaxException {

		return gson.fromJson(jsonString
				, NumberResponse.class);
	}
}
