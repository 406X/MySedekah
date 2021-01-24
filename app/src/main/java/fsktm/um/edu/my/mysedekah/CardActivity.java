package fsktm.um.edu.my.mysedekah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.braintreepayments.cardform.view.SupportedCardTypesView;


public class CardActivity extends AppCompatActivity  implements View.OnClickListener, OnCardFormSubmitListener, CardEditText.OnCardTypeChangedListener  {

    private static final CardType[] SUPPORTED_CARD_TYPES = { CardType.VISA, CardType.MASTERCARD };
    private SupportedCardTypesView mSupportedCardTypesView;
    private CardForm cardForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        mSupportedCardTypesView = findViewById(R.id.card_list);
        mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        cardForm = (CardForm) findViewById(R.id.card_form);

        cardForm.cardRequired(true).expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(false)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Please enter your Phone Number")
                .actionLabel("Pay")
                .setup(CardActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cardForm.setOnCardFormSubmitListener(this);
        cardForm.setOnCardTypeChangedListener(this);
        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(this);

    }


    @Override
    public void onCardFormSubmit() {
        Intent submit = new Intent(this, DoneDonateActivity.class);
        startActivity(submit);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay:
                if(cardForm.isValid()) {
                    Intent submit = new Intent(this, DoneDonateActivity.class);
                    submit.putExtra("amount", getIntent().getStringExtra("amount"));
                    submit.putExtra("campaign", getIntent().getStringExtra("campaign"));
                    submit.putExtra("date",getIntent().getStringExtra("date"));
                    submit.putExtra("user_id",getIntent().getStringExtra("user_id"));
                    submit.putExtra("campaign_userid",getIntent().getStringExtra("campaign_userid"));
                    startActivity(submit);
                    finish();
                }
            default:
        }
    }

    @Override
    public void onCardTypeChanged(CardType cardType) {
        if (cardType == CardType.EMPTY) {
            mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        } else {
            mSupportedCardTypesView.setSelected(cardType);
        }
    }
}
