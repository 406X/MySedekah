package fsktm.um.edu.my.mysedekah;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
import fsktm.um.edu.my.mysedekah.donationhistorylist.donationHistoryItems;


public class MydonationHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MydonationHistoryRecyclerViewAdapter.ViewHolder> {

    private final List<donationHistoryItems> mValues;

    public MydonationHistoryRecyclerViewAdapter(List<donationHistoryItems> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_donationhistory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCampaign.setText("Campaign Name: " + mValues.get(position).campaign);
        holder.mAmount.setText("Amount Donated: RM" + mValues.get(position).amount);
        holder.mDate.setText("Date: " +mValues.get(position).date);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCampaign;
        public final TextView mDate;
        public final TextView mAmount;
        public donationHistoryItems mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCampaign = (TextView) view.findViewById(R.id.tv_campaign);
            mDate = (TextView) view.findViewById(R.id.tv_date);
            mAmount = (TextView) view.findViewById(R.id.tv_amount);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCampaign.getText() + "'" + " '" + mAmount.getText() + "'" + " '" + mDate.getText() + "'";
        }
    }
}