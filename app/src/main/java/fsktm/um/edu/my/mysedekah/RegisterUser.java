package fsktm.um.edu.my.mysedekah;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fsktm.um.edu.my.mysedekah.userdb.userhelper;
import fsktm.um.edu.my.mysedekah.userdb.useritems;
import fsktm.um.edu.my.mysedekah.userdb.usercontent;

public class RegisterUser extends AppCompatActivity {
    //Initiallize Variable
    TextView regTitle;
    EditText etName, etMobile, etEmail, etWebsite, etPassword, etConfirmPassword;
    userhelper helper = new userhelper(this);
    Button btSubmit;

    AwesomeValidation awesomeValidation;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Toolbar myChildToolbar =(Toolbar)findViewById(R.id.my_toolbar_child);
        setSupportActionBar(myChildToolbar);

        //get a support Action Bar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);


        //Assign Variable
        //regTitle = findViewById(R.id.reg_title);
        etName = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etEmail = findViewById(R.id.et_email);
//        etWebsite = findViewById(R.id.et_website);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btSubmit = findViewById(R.id.bt_submit);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Add Validation For Name
        awesomeValidation.addValidation(this, R.id.et_name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        //For Mobile Number
        awesomeValidation.addValidation(this,R.id.et_mobile,
                "[0-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        // For Email
        awesomeValidation.addValidation(this,R.id.et_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        // For Password
        awesomeValidation.addValidation(this,R.id.et_password,
                ".{6,}", R.string.invalid_password);

        // For Confirm Password
        awesomeValidation.addValidation(this,R.id.et_confirm_password,
                R.id.et_password,R.string.invalid_confirm_password);

        btSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Check Validation
                if(awesomeValidation.validate()){


                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA256");

                        EditText pass = findViewById(R.id.et_password);
                        EditText email = findViewById(R.id.et_email);
                        EditText phone = findViewById(R.id.et_mobile);
                        md.update(pass.getText().toString().getBytes());

                        byte messageDigest[] = md.digest();

                        StringBuffer hexString = new StringBuffer();
                        for (int i=0; i<messageDigest.length; i++)
                            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));



                        ContentValues cv = new ContentValues();
                        cv.put(useritems.user.COLUMN_ID, Long.toString(helper.getNextID()));
                        cv.put(useritems.user.COLUMN_EMAIL, email.getText().toString());
                        cv.put(useritems.user.COLUMN_PASS, hexString.toString());
                        cv.put(useritems.user.COLUMN_PHONE, phone.getText().toString());
                        cv.put(useritems.user.COLUMN_NAME, "Null");
                        cv.put(useritems.user.COLUMN_BANK, "Null");
                        cv.put(useritems.user.COLUMN_BANKNUM, "Null");
                        cv.put(useritems.user.COLUMN_APPLYSTATUS, "Null");
                        cv.put(useritems.user.COLUMN_ACCTYPE, "Null");

                        helper.insertUser(cv);
                        helper.checkEmail(email.getText().toString());

                        Toast.makeText(getApplicationContext(),
                                "Registration Complete, please log in.",Toast.LENGTH_SHORT).show();
                    } catch (NoSuchAlgorithmException e) {
                    }

                    openApplyDonation();
                }
            }
        });
    }

    public void openApplyDonation(){
        Intent goToSubmit = new Intent(this, MainActivity.class);
        startActivity(goToSubmit);
    }
}
