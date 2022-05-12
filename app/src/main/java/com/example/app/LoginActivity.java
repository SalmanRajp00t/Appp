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

import com.example.app.database.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    //EditText name;
    Button signIn;
    TextView signIn_register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.txt_login_email);
        password=(EditText) findViewById(R.id.txt_login_password);
        //name=(EditText) findViewById(R.id.txt_reg_fullName);

        //    DBHelper helper=new DBHelper(this);

        auth=FirebaseAuth.getInstance();

        ProgressDialog dialog=new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Login");
        dialog.setMessage("Login to your account.");

        signIn=(Button) findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String Email=email.getText().toString();
                String Password=password.getText().toString();

                auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login is Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            //View root=getLayoutInflater().inflate(R.layout.activity_registration,null);
                            //String Name=name.getText().toString();
                         //   intent.putExtra("email",Email);
                         //   startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                /*
                if (Email.equals("") || Password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill all Field.", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean validate=helper.checkEmailAndPassword(Email,Password);
                    if (validate==true){
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login is Successfully.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Login is not Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
                 */
            }
        });

        signIn_register=(TextView) findViewById(R.id.txt_sign_in_register);
        signIn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}