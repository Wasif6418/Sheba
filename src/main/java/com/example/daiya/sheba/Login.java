package com.example.daiya.sheba;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.PriorityQueue;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton,signUpButton;
    private EditText Pass,Email;

    private String emailchk,passwordchk,name;
    private int found=0;
    Cursor data;
    MyDatabaseHelper myDatabaseHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=findViewById(R.id.loginButtonId);
        signUpButton=findViewById(R.id.signUpButtonId);
        Pass=findViewById(R.id.password11Id);
        Email=findViewById(R.id.Email11Id);








        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String password=Pass.getText().toString();
        String email=Email.getText().toString();
        if(v.getId()==R.id.loginButtonId)
        {
            myDatabaseHelper=new MyDatabaseHelper(this);
            data = myDatabaseHelper.getDatasofUser();
            if(data.getCount()==0)
            {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                while ((data.moveToNext()))
                {
                    emailchk=data.getString(1);
                    passwordchk=data.getString(3);
                    if(email.equalsIgnoreCase(emailchk) && password.equals(passwordchk))
                    {
                        name=data.getString(2);
                        found=1;
                        break;
                    }
                }
            }


            if(found==1)
            {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Incorrect Email or Password  ", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.signUpButtonId)
        {
            Intent intent=new Intent(Login.this,SignUp.class);
            startActivity(intent);
        }
    }
}
