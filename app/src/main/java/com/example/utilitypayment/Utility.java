package com.example.utilitypayment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Utility extends AppCompatActivity {
    Button regButton ,loginButton,viewButton,deleteButton,updateButton;
    EditText phone_number,password;
    TextView listTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        viewButton = findViewById(R.id.view_button);
        listTxt = findViewById(R.id.listTxt);
//        deleteButton = findViewById(R.id.delete_button);
//        updateButton = findViewById(R.id.updateButton_button);

        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Utility.this, Registration.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String phone_num = phone_number.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(phone_num.isEmpty() ){
                    Toast.makeText(Utility.this, "Please enter phone number", Toast.LENGTH_SHORT).show();

                }else if(pass.isEmpty()){
                    Toast.makeText(Utility.this, "Please enter password", Toast.LENGTH_SHORT).show();

                }
                else{
                    DBHelper mydb = new DBHelper(Utility.this);
                    boolean loginSuccess = mydb.login(phone_num,pass);
                    if(loginSuccess){
                        Toast.makeText(Utility.this, "Login successful", Toast.LENGTH_SHORT).show();
                        phone_number.setText("");
                        password.setText("");
                        Intent intent = new Intent(Utility.this, DashBoard.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Utility.this, "Invalid phone number or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listTxt.getText().toString().isEmpty()){
                    DBHelper mydb = new DBHelper(Utility.this);
                    List list=mydb.getAllData();
                    StringBuilder builder = new StringBuilder();
                    for(int i=0;i<list.size();i++){
                        DBHelper.MyData data = (DBHelper.MyData) list.get(i);
                        builder.append((
                                data.id + " " +
                                        data.f_name + " " +
                                        data.l_name + " " +
                                        data.p_number + " " +
                                        data.password ).toString()+"\n");
                    }
                    listTxt.setText(builder.toString());
                }else {
                    listTxt.setText("");
                }
            }
        });
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = Integer.parseInt(phone_number.getText().toString().trim());
//                Intent intent = new Intent(Utility.this, UpdateData.class);
//                intent.putExtra("id",id);
//                startActivity(intent);
//            }
//        });

//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBHelper mydb = new DBHelper(Utility.this);
//                String username = phone_number.getText().toString().trim();
//                int id = Integer.parseInt(phone_number.getText().toString().trim());
//                int rowsAffected = mydb.deleteData(id);
//                if(rowsAffected > 0){
//                    Toast.makeText(Utility.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(Utility.this, "Data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
}
}


