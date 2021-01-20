package fsktm.um.edu.my.mysedekah;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Toolbar myChildToolbar =(Toolbar)findViewById(R.id.my_toolbar_child);
        setSupportActionBar(myChildToolbar);

        //get a support Action Bar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}