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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_textButton_activity:
                Intent LoginIntent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(LoginIntent);
                break;
        }
    }
}
