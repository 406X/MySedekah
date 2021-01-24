package fsktm.um.edu.my.mysedekah;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import fsktm.um.edu.my.mysedekah.donationhistorylist.donationHistoryContent;
import fsktm.um.edu.my.mysedekah.donationhistorylist.donationHistoryItems;
import fsktm.um.edu.my.mysedekah.donationdb.donationhelper;
import fsktm.um.edu.my.mysedekah.donationdb.donationcontent;

public class DonationHistoryActivity extends AppCompatActivity {

    public String user_id = "40";
    private DonationHistoryActivity context;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationhistory);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        if (recyclerViewAdapter == null) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.dhistory_fragment);
            recyclerView = (RecyclerView) currentFragment.getView();
            recyclerViewAdapter = ((RecyclerView) currentFragment.getView()).getAdapter();
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
        };

        donationhelper helper = new donationhelper(this);
        donationHistoryContent.ITEMS.clear();
        List<donationcontent> cc = helper.getDonationsByUser(user_id);
        if(cc.isEmpty()){
            Toast.makeText(getApplicationContext(), "No donation history found.", Toast.LENGTH_SHORT).show();
        }


        for(int x = 0 ; x < cc.size() ; x+=1){
            donationHistoryItems f = new donationHistoryItems();
            f.amount = cc.get(x).amount;
            f.date = cc.get(x).date;
            f.campaign = cc.get(x).campaign;
            donationHistoryContent.loadHistory(f);
        }
        recyclerViewAdapter.notifyItemInserted(0);

    }

}
