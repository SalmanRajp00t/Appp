package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,password;
    Button register;
    TextView register_sign_in;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=(EditText) findViewById(R.id.txt_reg_fullName);
        email=(EditText) findViewById(R.id.txt_reg_email);
        password=(EditText)findViewById(R.id.txt_reg_password);

        //     DBHelper helper=new DBHelper(this);

        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        ProgressDialog dialog=new ProgressDialog(RegistrationActivity.this);
        dialog.setTitle("Creating Account");
        dialog.setMessage("We're creating your account.");

        register=(Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(Name, Email, Password);

                            String id = task.getResult().getUser().getUid();

                            database.getReference().child("User").child(id).setValue(user);

                            Toast.makeText(RegistrationActivity.this, "Account is Created.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                /*
                if (Name.equals("") || Email.equals("") || Password.equals("")){
                    Toast.makeText(RegistrationActivity.this, "Please fill all Field.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean i=helper.checkEmail(Email);
                    if (i==false){
                        boolean checkInsert=helper.insertUserData(Name,Email,Password);
                        if (checkInsert==true){
                            Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegistrationActivity.this, "Account is created.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegistrationActivity.this, "Your Account have been not Created.", Toast.LENGTH_SHORT);
                        }
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, "Please choose another Email.", Toast.LENGTH_SHORT).show();
                    }
                }
                 */
            }
        });

        register_sign_in = (TextView) findViewById(R.id.txt_register_sign_in);
        register_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}