package fsktm.um.edu.my.mysedekah;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fsktm.um.edu.my.mysedekah.campaignlist.campaignListContent;
import fsktm.um.edu.my.mysedekah.campaignlist.campaignListItems;

public class CampaignView extends Fragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private CampaignView.OnListFragmentInteractionListener mListener;

    public CampaignView() {
    }
    public static CampaignView newInstance(int columnCount) {
        CampaignView fragment = new CampaignView();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign_view_list, container, false);


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyCampaignRecyclerViewAdapter(campaignListContent.ITEMS, mListener));
        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(campaignListItems item);
    }
}