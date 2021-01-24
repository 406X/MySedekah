package fsktm.um.edu.my.mysedekah;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fsktm.um.edu.my.mysedekah.campaignlist.campaignListItems;

import java.util.List;


public class MyCampaignRecyclerViewAdapter extends RecyclerView.Adapter<MyCampaignRecyclerViewAdapter.ViewHolder> {

    private final List<campaignListItems> mValues;
    private final CampaignView.OnListFragmentInteractionListener mListener;

    public MyCampaignRecyclerViewAdapter(List<campaignListItems> items, CampaignView.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_campaign_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImageView.setImageDrawable(mValues.get(position).img);
        holder.mDesc.setText(mValues.get(position).desc);
        holder.mTitle.setText(mValues.get(position).title);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mDesc;
        public final TextView mTitle;
        public campaignListItems mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.item_image_view);
            mTitle = view.findViewById(R.id.view_title);
            mDesc = view.findViewById(R.id.tv_description);
        }
    }
}