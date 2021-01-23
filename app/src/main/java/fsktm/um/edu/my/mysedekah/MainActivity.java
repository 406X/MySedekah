package fsktm.um.edu.my.mysedekah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fsktm.um.edu.my.mysedekah.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Button applyDonation, Donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        applyDonation = findViewById(R.id.button2);
        Donation = findViewById(R.id.button3);

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
        Intent goToApply = new Intent(this, DonateApplicationForm.class);
        startActivity(goToApply);
    }

    public void openDonation(){
        Intent goToDonation = new Intent(this, DonationActivity.class);
        startActivity(goToDonation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
                    //user choose the settings item, show the app setting UI
                    case R.id.action_settings:
                        return true;

                    case R.id.action_login:
                        Intent RLAct = new Intent(this, LoginActivity.class);
                        startActivity(RLAct);
                        return true;

                    case R.id.action_register:
                        Intent Reg = new Intent(this, RegisterUser.class);
                        startActivity(Reg);
                        return true;

                    case R.id.action_search:
                        return true;

                    case R.id.action_comments:
                        Intent RLAct1 = new Intent(this, RateActivity.class);
                        startActivity(RLAct1);
                        return true;

                    case R.id.action_favourite:
                        //user choose the favourite item action, mark the current item as a favourite
                        return true;
                    default:
                        //if we got here the user action was not recognize
                        // invoke the superclass to handle it
                        return super.onOptionsItemSelected(item);
        }
    }
}