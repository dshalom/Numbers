package Model;

/**
 * Created by davidshalom on 27/12/2014.
 */
public class NumberResponse {

	private String text;
	private int number;
	private boolean found;
	private String type;

	public NumberResponse(String text, int number, boolean found, String type) {
		this.text = text;
		this.number = number;
		this.found = found;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
