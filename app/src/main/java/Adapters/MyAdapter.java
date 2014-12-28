package Adapters;

import Model.NumberResponse;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.davidshalom.numbers.R;

import java.util.ArrayList;

/**
 * Created by davidshalom on 27/12/2014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private ArrayList<NumberResponse> data = new ArrayList<>();


	public MyAdapter(ArrayList<NumberResponse> data) {
		this.data = data;
	}

	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.numbers_item, parent, false);
		// set the view's size, margins, paddings and layout parameters

		MyAdapter.ViewHolder vh = new ViewHolder(v);
		return vh;

	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.textViewNumberText.setText(data.get(position).getText());
		holder.textViewNumber.setText(String.valueOf(data.get(position).getNumber()));

	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	// inner class to hold a reference to each item of RecyclerView
	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView textViewNumber;
		public TextView textViewNumberText;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);
			textViewNumber = (TextView) itemLayoutView.findViewById(R.id.textViewNumber);
			textViewNumberText = (TextView) itemLayoutView.findViewById(R.id.textViewNumberText);
		}
	}
}
