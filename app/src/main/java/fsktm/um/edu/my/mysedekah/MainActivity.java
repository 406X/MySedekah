package fsktm.um.edu.my.mysedekah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fsktm.um.edu.my.mysedekah.login.LoginActivity;
import fsktm.um.edu.my.mysedekah.user.UserActivity;
import fsktm.um.edu.my.mysedekah.user.UserLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
                        Intent RLAct = new Intent(this, UserLoginActivity.class);
                        startActivity(RLAct);
                        return true;

                    case R.id.action_register:
                        Intent intent = new Intent(this, UserActivity.class);
                        startActivity(intent);
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
                    case R.id.test_edit:
                        Intent test2 = new Intent(this, EditViewActivity.class);
                        startActivity(test2);
                        return true;
                    case R.id.test_view:
                        Intent test3 = new Intent(this, CampaignActivity.class);
                        startActivity(test3);
                        return true;
                    case R.id.donation_history:
                        Intent test4 = new Intent(this, DonationHistoryActivity.class);
                        startActivity(test4);
                        return true;
                    default:
                        //if we got here the user action was not recognize
                        // invoke the superclass to handle it
                        return super.onOptionsItemSelected(item);
        }
    }


}