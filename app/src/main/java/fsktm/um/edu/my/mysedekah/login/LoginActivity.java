package fsktm.um.edu.my.mysedekah.login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fsktm.um.edu.my.mysedekah.R;
import fsktm.um.edu.my.mysedekah.userdb.*;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private userhelper helper = new userhelper(this);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar myChildToolbar =(Toolbar)findViewById(R.id.my_toolbar_child);
        setSupportActionBar(myChildToolbar);

        //get a support Action Bar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
//                setResult(Activity.RESULT_OK);
//
//                //Complete and destroy login activity once successful
//                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                MessageDigest md = null;

                try {
                    md = MessageDigest.getInstance("SHA256");
                    EditText pass = findViewById(R.id.password);
                    EditText email = findViewById(R.id.username);

                    md.update(pass.getText().toString().getBytes());

                    byte messageDigest[] = md.digest();

                    if(helper.checkEmail(email.getText().toString())) {
                        usercontent uc = helper.getUser(email.getText().toString());
                        md.update(pass.getText().toString().getBytes());
                        StringBuffer hexString = new StringBuffer();
                        for (int i = 0; i < messageDigest.length; i++)
                            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

                        if(uc.pass.equals(hexString.toString())){
                            Intent resultIntent = new Intent();

                            resultIntent.putExtra("user_id", uc._id);
                            setResult(Activity.RESULT_OK, resultIntent);
                            Toast.makeText(getApplicationContext(),
                                    "Login Success.",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Invalid Credentials2", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (NoSuchAlgorithmException e) {
                }


            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}