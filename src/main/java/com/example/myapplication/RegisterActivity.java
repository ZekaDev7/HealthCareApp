package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextAppFullName);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirmPassword = findViewById(R.id.editTextAppFees);
        edEmail = findViewById(R.id.editTextAppAddress);
        btn = findViewById(R.id.buttonBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String mail = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPass = edConfirmPassword.getText().toString();

                DataBase db = new DataBase(getApplicationContext(), "healthcare", null, 1);

                if (username.length() == 0 || password.length() == 0 || mail.length() == 0 || confirmPass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Login failed, please fill all details!", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPass) == 0) {
                        if (isValid(password)) {
                            db.register(username, password, mail);
                            Toast.makeText(getApplicationContext(), "Record saved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letters, digit and spesific characters!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordHere) {

        int f1 = 0, f2 = 0, f3 = 0;

        if (passwordHere.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < passwordHere.length(); i++) {
                if (Character.isLetter(passwordHere.charAt(i))) ;
                f1 = 1;
            }
            for (int j = 0; j < passwordHere.length(); j++) {
                if (Character.isDigit(passwordHere.charAt(j))) ;
                f2 = 1;
            }
            for (int k = 0; k < passwordHere.length(); k++) {
                char c = passwordHere.charAt(k);
                if (k >= 33 && k <= 46 || c == 64) ;
                f3 = 1;
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }
}