package com.example.utilitypayment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    private TextInputEditText  firstName,
                                lastName,
                                phoneNumber,
                                createPassword,
                                confirmPassword;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        phoneNumber = findViewById(R.id.phoneNumberEditText);
        createPassword = findViewById(R.id.createPasswordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);



        // String fName = firstName.getText().toString().trim();
        // String lName = lastName.getText().toString().trim();
        //int pNumber = Integer.parseInt(phoneNumber.getText().toString().trim());
        //int addressNumber = Integer.parseInt(address.getText().toString().trim());
        //int accountNumber = Integer.parseInt(account.getText().toString().trim());

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform registration logic here
                if (!createPassword.getText().toString().trim().equals(confirmPassword.getText().toString().trim())) {
                    Toast.makeText(Registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else{
                DBHelper mydb = new DBHelper(Registration.this);
                long val= mydb.addData(firstName.getText().toString().trim(),
                        lastName.getText().toString().trim(),
                        Integer.parseInt(phoneNumber.getText().toString().trim()),
                        createPassword.getText().toString().trim());
                if(val!=-1){
                    Toast.makeText(Registration.this, "Registered successfully", Toast.LENGTH_LONG).show();
                   startActivity( new Intent(Registration.this,Utility.class));
                }else{
                    Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }

}
