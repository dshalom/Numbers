package com.example.davidshalom.numbers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by davidshalom on 28/12/2014.
 */
public class UserNumberDialog extends DialogFragment {

	private UserNumberDialogListener userNumberDialogListener;

	public interface UserNumberDialogListener {
		void numberChosen(int number);
	}

	static UserNumberDialog newInstance() {
		UserNumberDialog f = new UserNumberDialog();
		return f;
	}

	public void initialise(UserNumberDialogListener userNumberDialogListener) {
		this.userNumberDialogListener = userNumberDialogListener;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dlg = super.onCreateDialog(savedInstanceState);

		dlg.setTitle("Enter a number");
		TextView titleTextView = (TextView) dlg.findViewById(android.R.id.title);
		titleTextView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL );

		return dlg;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.usernumber_dialog_fragment, container, false);
		final EditText editTextUsersNumber = (EditText) v.findViewById(R.id.editTextUsersNumber);

		// Watch for button clicks.
		Button buttonHitMe = (Button) v.findViewById(R.id.buttonHitMe);
		buttonHitMe.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// When button is clicked, call up to owning activity.
				if(!editTextUsersNumber.getText().toString().equals("")) {
					userNumberDialogListener.numberChosen(Integer.valueOf(editTextUsersNumber.getText().toString()));
				}
				dismiss();
			}
		});

		return v;
	}
}
