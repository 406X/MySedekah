package fsktm.um.edu.my.mysedekah;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import java.util.regex.Pattern;

public class DonateApplicationForm extends AppCompatActivity {
    //Initiallize Variable
    TextView applyTitle;
    EditText applyName, applyMobile, applyEmail, applyBank, applyAccountNum, applyDetails;
    Button applySubmit;
    AwesomeValidation applyValidation;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_application_form);

        //Assign Variable
        applyTitle = findViewById(R.id.apply_title);
        applyName = findViewById(R.id.apply_name);
        applyMobile = findViewById(R.id.apply_mobile);
        applyEmail = findViewById(R.id.apply_email);
        applyBank = findViewById(R.id.apply_bank);
        applyAccountNum = findViewById(R.id.apply_accountNum);
        applyDetails = findViewById(R.id.apply_details);
        applySubmit = findViewById(R.id.apply_submit);

        //Initialize Validation Style
        applyValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Add Validation For Name
        applyValidation.addValidation(this, R.id.apply_name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        //For Mobile Number
        applyValidation.addValidation(this,R.id.apply_mobile,
                "[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        // For Email
        applyValidation.addValidation(this,R.id.apply_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        // For Bank
        applyValidation.addValidation(this,R.id.apply_bank,
                R.id.apply_bank,R.string.invalid_bank);

        // For Bank Account Number
        applyValidation.addValidation(this,R.id.apply_accountNum,
                R.id.apply_accountNum,R.string.invalid_accountNumber);

        // For Details
        applyValidation.addValidation(this,R.id.apply_details,
                R.id.apply_details,R.string.invalid_confirm_password);

        applySubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Check Validation
                if(applyValidation.validate()){
                    // On Success
                    Toast.makeText(getApplicationContext(),
                            "Form Validate Succesfully...",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Validation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
