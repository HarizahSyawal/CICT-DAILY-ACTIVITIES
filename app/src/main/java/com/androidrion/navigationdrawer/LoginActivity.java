package com.androidrion.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    boolean isEmailValid, isPasswordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.text_input_email);
        password = (EditText) findViewById(R.id.text_input_password);
        login = (Button) findViewById(R.id.btn_login);


        Button btnForgot = findViewById(R.id.btn_textButton_activity);
        btnForgot.setOnClickListener(this);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }
    public void SetValidation() {
        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }
        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }
        if (isEmailValid && isPasswordValid) {
            User user = new User(1,"test@gtest.com");
            Session session = new Session(LoginActivity.this);
            session.saveSession(user);
            moveToMainActivity();
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            Intent siginIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(siginIntent);
            this.finish();
        }
//        else {
//            Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
//            t.show();
//        }
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_textButton_activity:
                Intent LoginIntent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(LoginIntent);
                break;
        }
    }
    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        Session session = new Session(LoginActivity.this);
        int userID = session.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }
        else{
            //do nothing
        }
    }
}
