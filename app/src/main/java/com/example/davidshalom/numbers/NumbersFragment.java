package com.example.davidshalom.numbers;

import Adapters.MyAdapter;
import Listeners.NumberRequestListener;
import Model.NumberResponse;
import Model.NumberResponseConverter;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class NumbersFragment extends Fragment implements UserNumberDialog.UserNumberDialogListener {

	ArrayList<NumberResponse> data = new ArrayList<>();
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;


	// TODO: Rename and change types and number of parameters
	public static NumbersFragment newInstance() {
		return new NumbersFragment();

	}

	public NumbersFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		data = new ArrayList<>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_numbers, container, false);

		Button buttonRandom = (Button) v.findViewById(R.id.buttonNextRandomNumber);
		buttonRandom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NumbersRequestFactory.NumberRequest numberRequest = NumbersRequestFactory.getRandomNumberRequest(numberRequestListener, new NumberResponseConverter());
				VolleySingleton.getInstance(getActivity()).addToRequestQueue(numberRequest);
			}
		});

		Button buttonUser = (Button) v.findViewById(R.id.buttonUserNumber);
		buttonUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment prev = getFragmentManager().findFragmentByTag("dialog");
				if (prev != null) {
					ft.remove(prev);
				}
				ft.addToBackStack(null);

				// Create and show the dialog.
				UserNumberDialog userNumberDialog = UserNumberDialog.newInstance();
				userNumberDialog.initialise(NumbersFragment.this);
				userNumberDialog.show(ft, "dialog");
			}
		});

		mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

		// use a linear layout manager

		// 2. set layoutManger
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		// 3. create an adapter
		mAdapter = new MyAdapter(data);
		// 4. set adapter
		mRecyclerView.setAdapter(mAdapter);
		// 5. set item animator to DefaultAnimator
		//	mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		return v;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}


	NumberRequestListener numberRequestListener = new NumberRequestListener() {

		@Override
		public void onResponse(NumberResponse numberResponse) {

			for (NumberResponse response : data) {
				if (response.getNumber() == numberResponse.getNumber()) {
					response.setText(numberResponse.getText());
					mAdapter.notifyDataSetChanged();
					return;
				}
			}

			data.add(numberResponse);
			mAdapter.notifyDataSetChanged();
			scrollMyListViewToBottom();

		}

		@Override
		public void onErrorResponse(String error) {

		}
	};

	@Override
	public void numberChosen(int number) {
		NumbersRequestFactory.NumberRequest numberRequest = NumbersRequestFactory.getNumberRequest(number, numberRequestListener, new NumberResponseConverter());
		VolleySingleton.getInstance(getActivity()).addToRequestQueue(numberRequest);
	}

	private void scrollMyListViewToBottom() {

		mRecyclerView.post(new Runnable() {
			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
			}
		});
	}
}
