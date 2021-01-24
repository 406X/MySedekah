package fsktm.um.edu.my.mysedekah;


import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fsktm.um.edu.my.mysedekah.donationdb.donationhelper;
import fsktm.um.edu.my.mysedekah.donationdb.donationcontent;
import fsktm.um.edu.my.mysedekah.donationdb.donationitems;

public class DoneDonateActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donedonate);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Button done = findViewById(R.id.btn_back);
        done.setOnClickListener(this);

        donationhelper helper = new donationhelper(this);

        ContentValues values = new ContentValues();
        values.put(donationitems.donation.COLUMN_ID, Long.toString(helper.getNextID()));
        values.put(donationitems.donation.COLUMN_USERID, getIntent().getStringExtra("user_id"));
        values.put(donationitems.donation.COLUMN_CAMPAIGN, getIntent().getStringExtra("campaign"));
        values.put(donationitems.donation.COLUMN_AMOUNT,getIntent().getStringExtra("amount"));
        values.put(donationitems.donation.COLUMN_DATE, getIntent().getStringExtra("date"));
        values.put(donationitems.donation.COLUMN_CAMPAIGN_USER_ID, getIntent().getStringExtra("campaign_userid"));
        helper.insertdonation(values);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btn_back):
                finish();
        }
    }
}
