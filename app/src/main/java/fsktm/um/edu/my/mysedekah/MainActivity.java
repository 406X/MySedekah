package fsktm.um.edu.my.mysedekah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fsktm.um.edu.my.mysedekah.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button applyDonation, Donation;
    String user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        applyDonation = findViewById(R.id.button2);
        Donation = findViewById(R.id.btn_browse);

        applyDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApplyDonation();
            }
        });

        Donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonation();
            }
        });
    }

    public void openApplyDonation(){
        if(!user_id.isEmpty()) {
            Intent goToApply = new Intent(this, DonateApplicationForm.class);
            startActivity(goToApply);}
        else{
            Toast.makeText(getApplicationContext(),
                    "Please log in.",Toast.LENGTH_SHORT).show();
            }
    }

    public void openDonation(){
        Intent browse = new Intent(this, CampaignActivity.class);
        browse.putExtra("user_id",user_id);
        startActivity(browse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(user_id.isEmpty()){
            menu.clear();
            getMenuInflater().inflate(R.menu.main, menu);}
        else{
            menu.clear();
            getMenuInflater().inflate(R.menu.main_loggedin, menu);}
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                     user_id = data.getStringExtra("user_id");
                }
                break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
                    case R.id.action_settings:
                        return true;
                    case R.id.action_login:
                        Intent RLAct = new Intent(this, LoginActivity.class);
                        startActivityForResult(RLAct,1);
                        this.invalidateOptionsMenu();
                        return true;
                    case R.id.action_register:
                        Intent Reg = new Intent(this, RegisterUser.class);
                        startActivity(Reg);
                        return true;
                    case R.id.action_logout:
                        user_id = "";
                        Toast.makeText(getApplicationContext(),
                                "Log Out Successful.",Toast.LENGTH_SHORT).show();
                        this.invalidateOptionsMenu();
                        return true;
                    case R.id.action_comments:
                        Intent RLAct1 = new Intent(this, RateActivity.class);
                        startActivity(RLAct1);
                        return true;

                    case R.id.test_edit:
                        if(!user_id.isEmpty()) {
                            Intent test2 = new Intent(this, EditViewActivity.class);
                            test2.putExtra("user_id",user_id);
                            startActivity(test2);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),
                                    "Please log in.",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    case R.id.donation_history:
                        if(!user_id.isEmpty()) {
                            Intent test4 = new Intent(this, DonationHistoryActivity.class);
                            test4.putExtra("user_id", user_id);
                            startActivity(test4);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),
                                    "Please log in.",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    case R.id.button2:
                        if(!user_id.isEmpty()) {
                            Intent applyDonation = new Intent(this, DonateApplicationForm.class);
                            startActivity(applyDonation);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),
                                    "Please log in.",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    default:
                        //if we got here the user action was not recognize
                        // invoke the superclass to handle it
                        return super.onOptionsItemSelected(item);
        }
    }


}