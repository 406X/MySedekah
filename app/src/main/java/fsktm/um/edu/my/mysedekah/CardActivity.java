package fsktm.um.edu.my.mysedekah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import com.braintreepayments.cardform.view.CardForm;


public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        //Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true).expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Please enter your Phone Number")
                .actionLabel("Purchase")
                .setup(CardActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

    }



}
