package fsktm.um.edu.my.mysedekah;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fsktm.um.edu.my.mysedekah.campaigndb.campaigncontent;
import fsktm.um.edu.my.mysedekah.campaigndb.campaignhelper;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button Donate = findViewById(R.id.btn_donate);
        Donate.setOnClickListener(this);
        String user_id = getIntent().getStringExtra("id");
        campaignhelper helper = new campaignhelper(this);
        campaigncontent cc = helper.getCampaign(user_id);
        setLayout(cc);

    }

    public void setLayout(campaigncontent cc){
        TextView title = findViewById(R.id.view_title);
        title.setTypeface(null, Typeface.BOLD);
        TextView c_desc = findViewById(R.id.tv_description);
        ImageView Imgv = findViewById(R.id.iv_pageimage);
        c_desc.setMovementMethod(new ScrollingMovementMethod());
        c_desc.setText(cc.getDesc());
        title.setText(cc.getTitle());
        Imgv.setImageDrawable(blobToDrawable(cc.getImg()));
    }

    public Drawable blobToDrawable(byte[] image){
        Drawable img = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(image, 0, image.length));
        return img;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_donate:
                EditText amount = findViewById(R.id.editText_amount);
                TextView title  = findViewById(R.id.view_title);
                Intent donate= new Intent(this, CardActivity.class);
                donate.putExtra("amount", amount.getText().toString());
                donate.putExtra("campaign", title.getText().toString());
                donate.putExtra("user_id", getIntent().getStringExtra("user_id"));
                donate.putExtra("campaign_userid", getIntent().getStringExtra("_id"));
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                donate.putExtra("date", date);
                startActivity(donate);
                finish();
            default:
                break;
        }
    }
}
