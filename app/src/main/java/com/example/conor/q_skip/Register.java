package com.example.conor.q_skip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {


    private EditText etName;
    private EditText etAge;
    private EditText etMobile;
    private EditText etUserName;
    private EditText etPassword;
    private Button bRegister;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etMobile = (EditText) findViewById(R.id.etMobile);
        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //create a child object in the database
                //assign values to the child objects

                String name = etName.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String username = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                HashMap<String, String> datamap = new HashMap<String, String>();

                datamap.put("Name", name);
                datamap.put("Age", age);
                datamap.put("Mobile", mobile);
                datamap.put("UserName", username);
                datamap.put("Password", password);

                mDatabase.push().setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(Register.this, "Registration Sucessful!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Register.this, "Error: Registration Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                // mDatabase.push().setValue(name);
                // mDatabase.child("Age").setValue(age);
                // mDatabase.child("Mobile").setValue(mobile);
                // mDatabase.child("UserName").setValue(username);
                // mDatabase.child("Password").setValue(password);

                //function allows redirect back to login screen on click as well as data being sent to the database

                Intent registerIntent = new Intent(Register.this, MainActivity.class);
                Register.this.startActivity(registerIntent);

            }
        });
    }
}
