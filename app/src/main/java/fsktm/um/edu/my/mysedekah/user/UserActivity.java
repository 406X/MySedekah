package fsktm.um.edu.my.mysedekah.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fsktm.um.edu.my.mysedekah.MainActivity;
import fsktm.um.edu.my.mysedekah.R;

public class UserActivity extends AppCompatActivity {

    EditText username, name, email, hpnum, password, repassword;
    Button signup, signin;
    UserDBHelper DB;
    Integer userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar myChildToolbar =(Toolbar)findViewById(R.id.my_toolbar_child);
        setSupportActionBar(myChildToolbar);

        //get a support Action Bar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        //enable the up button
        ab.setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        hpnum = findViewById(R.id.hpnum);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);
        DB = new UserDBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String nameuser = name.getText().toString();
                String emailuser = email.getText().toString();
                String hpuser = hpnum.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || nameuser.equals("") || emailuser.equals("") || hpuser.equals("") || pass.equals("") || repass.equals("") )
                    Toast.makeText(UserActivity.this, "Please enter all this field", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user, nameuser, emailuser, hpuser, pass );
                            if( insert == true){
                                Toast.makeText(UserActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(UserActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(UserActivity.this, "User already exists! Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(UserActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);

            }
        });
    }
}