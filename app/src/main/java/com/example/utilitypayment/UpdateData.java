package com.example.utilitypayment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateData extends AppCompatActivity {
    Button updateButton;
    TextInputEditText firstName,lastName,phoneNumber,address,account;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_data);
        updateButton=findViewById(R.id.updateButton);
        firstName=findViewById(R.id.firstNameEditText);
        lastName=findViewById(R.id.lastNameEditText);
        phoneNumber=findViewById(R.id.phoneNumberEditText);





        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("id",43);
                DBHelper mydb = new DBHelper(UpdateData.this);
             int rowsAffected =   mydb.updateData(id,firstName.getText().toString().trim(),
                        lastName.getText().toString().trim(),
                        Integer.parseInt(phoneNumber.getText().toString().trim())
                        );
            if(rowsAffected > 0){
                Toast.makeText(UpdateData.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                }else {
                Toast.makeText(UpdateData.this, "Data not found", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
}